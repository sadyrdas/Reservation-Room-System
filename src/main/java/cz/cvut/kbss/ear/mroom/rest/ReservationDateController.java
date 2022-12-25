package cz.cvut.kbss.ear.mroom.rest;

import cz.cvut.kbss.ear.mroom.model.ReservationDate;
import cz.cvut.kbss.ear.mroom.rest.util.RestUtil;
import cz.cvut.kbss.ear.mroom.service.ReservationDateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/days")
public class ReservationDateController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private final ReservationDateService reservationDateService;

    @Autowired
    public ReservationDateController(ReservationDateService reservationDateService) {
        this.reservationDateService = reservationDateService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addDay(@RequestBody ReservationDate day) {
        reservationDateService.createDay(day);
        LOG.info("Day was created with id: {}, with date: {}", day.getId(), day.getPosting_date());
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/deleteDay", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteDay(@RequestBody ReservationDate day) {
        reservationDateService.deleteDay(day.getPosting_date());
        LOG.info("Day {} was deleted", day.getPosting_date());
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
