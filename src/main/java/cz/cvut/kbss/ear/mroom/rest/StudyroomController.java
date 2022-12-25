package cz.cvut.kbss.ear.mroom.rest;


import cz.cvut.kbss.ear.mroom.dao.StudyRoomDao;
import cz.cvut.kbss.ear.mroom.model.StudyRoom;
import cz.cvut.kbss.ear.mroom.rest.util.RestUtil;
import cz.cvut.kbss.ear.mroom.service.StudyroomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studyroom")
public class StudyroomController {

    private static final Logger LOG = LoggerFactory.getLogger(StudyroomController.class);
    private final StudyRoomDao studyRoomDao;
    private final StudyroomService studyroomService;

    @Autowired
    public StudyroomController(StudyRoomDao studyRoomDao, StudyroomService studyroomService) {
        this.studyRoomDao = studyRoomDao;
        this.studyroomService = studyroomService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "createRoom", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createRoom(@RequestBody StudyRoom studyRoom) {
        studyroomService.createStudyroom(studyRoom.getCapacity(), studyRoom.getPrice());
        LOG.info("Studyroom with id {} successfully added.", studyRoom.getId());
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/changeStudyRoomStatus/{id}", consumes = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<Void> updateStatusRoom(@PathVariable Integer id, @RequestBody String status) {
        studyroomService.updateAvailableRoom(id, Boolean.parseBoolean(status));
        LOG.info("RoomStatus with id {} successfully updated.", id);
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/getAllAvailableRooms/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudyRoom> getAllAvailableRooms(@PathVariable Boolean status){
        LOG.info("Got all rooms with status : {}", status);
        return studyroomService.getRoomByStatus(status);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_STUDENT')")
    @GetMapping(
            value = "getStudyRoom/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public StudyRoom getStudyRoomById(@PathVariable Integer id) {
        LOG.info("Get studyroom with id: " + id);
        return studyroomService.findStudyRoomById(id);
    }
}
