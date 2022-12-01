package cz.cvut.kbss.ear.mroom;

import cz.cvut.kbss.ear.mroom.dao.StudyRoomDao;
import cz.cvut.kbss.ear.mroom.dao.UserDao;
import cz.cvut.kbss.ear.mroom.model.StudyRoom;
import cz.cvut.kbss.ear.mroom.model.User;
import cz.cvut.kbss.ear.mroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@RestController
public class MeetingRoomReservationApplication {

    private final UserDao userDao;
    private final StudyRoomDao studyRoomDao;

    @Autowired
    public MeetingRoomReservationApplication(UserDao userDao, StudyRoomDao studyRoomDao) {
        this.userDao = userDao;
        this.studyRoomDao = studyRoomDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(MeetingRoomReservationApplication.class, args);
    }




    @GetMapping("allRooms")
    public List<StudyRoom> hello3(){
        return studyRoomDao.findAll();
    }

    @Transactional
    @GetMapping("availableRooms")
    public Boolean hello2(){
        return studyRoomDao.createRoom(new StudyRoom(11,4200,1,true));
    }


    @Transactional
    @GetMapping("all2323")
    public List<StudyRoom> hello4() {
        return studyRoomDao.getAvailableRooms();
    }


    @GetMapping
    public List<User> hello() {
        UserService userService = new UserService(userDao);
        userService.updateUserEmailByEmail("test7@test.test", "test25@test.test");
        return userDao.getAllUsers();
    }


}
