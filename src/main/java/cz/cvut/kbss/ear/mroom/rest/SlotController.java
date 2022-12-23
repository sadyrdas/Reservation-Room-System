package cz.cvut.kbss.ear.mroom.rest;

import cz.cvut.kbss.ear.mroom.model.Slot;
import cz.cvut.kbss.ear.mroom.model.User;
import cz.cvut.kbss.ear.mroom.rest.util.RestUtil;
import cz.cvut.kbss.ear.mroom.service.SlotService;
import cz.cvut.kbss.ear.mroom.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/slots")
public class SlotController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private final SlotService slotService;

    @Autowired
    public SlotController(UserService userService, SlotService slotService) {
        this.userService = userService;
        this.slotService = slotService;
    }

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
}
