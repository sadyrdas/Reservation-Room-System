package cz.cvut.kbss.ear.mroom.rest;

import cz.cvut.kbss.ear.mroom.exception.NotFoundException;
import cz.cvut.kbss.ear.mroom.model.Slot;
import cz.cvut.kbss.ear.mroom.model.User;
import cz.cvut.kbss.ear.mroom.rest.util.RestUtil;
import cz.cvut.kbss.ear.mroom.service.ReservationDateService;
import cz.cvut.kbss.ear.mroom.service.SlotService;
import cz.cvut.kbss.ear.mroom.service.StudyroomService;
import cz.cvut.kbss.ear.mroom.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/slots")
public class SlotController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private final SlotService slotService;
    private final ReservationDateService reservationDateService;
    private final StudyroomService studyroomService;

    @Autowired
    public SlotController(UserService userService, SlotService slotService, ReservationDateService reservationDateService, StudyroomService studyroomService) {
        this.userService = userService;
        this.slotService = slotService;
        this.reservationDateService = reservationDateService;
        this.studyroomService = studyroomService;
    }

    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_CLIENT')")
    @GetMapping(value = "/payForSlot/{userEmail}/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> payForSlot(@PathVariable String userEmail, @PathVariable List<Integer> ids) {
        List<Slot> slots = new ArrayList<>();
        for (Integer id : ids) {
            slots.add(slotService.findSlotById(id));
        }
        User user = userService.findUserByEmail(userEmail);
        userService.payForSlot(user, slots);

        LOG.info("{} payed for {} slots", user, slots.size());
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/createSlot", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createSlot(@RequestBody Map<String, String> slot) {
        LOG.info("{}", slot.toString());
        slotService.createSlot(
                slot.get("start"), slot.get("finish"),
                Double.parseDouble(slot.get("price")), false, null,
                reservationDateService.findDayById(Integer.parseInt(slot.get("day"))),
                studyroomService.findStudyRoomById(Integer.parseInt(slot.get("studyroom_id"))));

        LOG.info("Created slot with!");
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(
            value = "/deleteSlot/{id}")
    public ResponseEntity<Void> deleteSlotById(@PathVariable Integer id) {
        slotService.deleteSlotById(id);
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_STUDENT')")
    @GetMapping(
            value = "/getAllSlots",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Slot> getAllSlots() {
        return slotService.findAllSlots();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(
            value = "/updateDay/{id}"
    )
    public ResponseEntity<Void> updateSlotDayById(@PathVariable Integer id, @RequestBody Map<String, String> date) {
        slotService.changeDay(slotService.findSlotById(id), reservationDateService.findDayByDate(LocalDate.parse(date.get("posting_date"))));
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_STUDENT')")
    @GetMapping(
            value = "getSlot/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Slot> getSlotById(@PathVariable Integer id) {
        Slot slot = slotService.findSlotById(id);

        if (slot == null) {
            throw new NotFoundException("Slot with id: " + id + " doesn't exist");
        }
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/getSlot{id}", id);
        return ResponseEntity.ok().headers(headers).body(slot);
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_STUDENT')")
    @GetMapping(
            value = "cancelReservation/{userEmail}/{ids}"
    )
    public ResponseEntity<Void> cancelReservations(@PathVariable String userEmail, @PathVariable List<Integer> ids) {
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw NotFoundException.create("User", userEmail);
        }

        List<Slot> slots = new ArrayList<>();
        for (Integer id : ids) {
            Slot slot = slotService.findSlotById(id);
            if (slot.getUser() == null || !slot.isPaid()) {
                throw NotFoundException.create("Slots", ids);
            }
            slots.add(slot);
        }

        userService.cancelReservation(slots);

        LOG.info("{} canceled {} slots", user, slots.size());
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}