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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/studyroom")
public class StudyroomController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
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
        studyroomService.createStudyroom(studyRoom.getCapacity(), studyRoom.getPrice(), studyRoom.isAvailable());
        LOG.info("Studyroom {} successfully added.", studyRoom);
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "changeStatus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateStatusRoom(@RequestBody StudyRoom studyRoom) {
        studyroomService.updateAvailableRoom(studyRoom.getId(), studyRoom.isAvailable());
        LOG.info("RoomStatus {} successfully updated.", studyRoom);
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }

}
