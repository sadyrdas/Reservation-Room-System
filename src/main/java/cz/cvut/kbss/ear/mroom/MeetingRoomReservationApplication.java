package cz.cvut.kbss.ear.mroom;

import cz.cvut.kbss.ear.mroom.dao.*;
import cz.cvut.kbss.ear.mroom.model.*;
import cz.cvut.kbss.ear.mroom.service.SlotService;
import cz.cvut.kbss.ear.mroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@RestController
public class MeetingRoomReservationApplication {

    private final UserDao userDao;
    private final StudyRoomDao studyRoomDao;
    private final DayDao dayDao;
    private final SlotDao slotDao;
    private final UserRoleDao userRoleDao;
    private final SlotService slotService;
    private final UserService userService;

    @Autowired
    public MeetingRoomReservationApplication(UserDao userDao, StudyRoomDao studyRoomDao, DayDao dayDao, SlotDao slotDao, UserRoleDao userRoleDao, SlotService slotService, UserService userService) {
        this.userDao = userDao;
        this.studyRoomDao = studyRoomDao;
        this.dayDao = dayDao;
        this.slotDao = slotDao;
        this.userRoleDao = userRoleDao;
        this.slotService = slotService;
        this.userService = userService;
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
        return studyRoomDao.createRoom(new StudyRoom(11,31,true));
    }


    @Transactional
    @GetMapping("all2323")
    public List<StudyRoom> hello4() {
        return studyRoomDao.getAvailableRooms();
    }


    @GetMapping
    public List<User> hello() {
        UserService userService = new UserService(userDao, slotService, slotDao);
        userService.updateUserEmailByEmail("test7@test.test", "test25@test.test");
        return userDao.getAllUsers();
    }

    // We use Transactional here to prevent "non-reliable persist operation"
    @Transactional
    @GetMapping("allDays")
    public List<Day> printAllDays() {
//        dayDao.persist(new Day(LocalDate.of(2001, 3, 27)));
        return dayDao.findAll();
    }

    @Transactional
    @GetMapping("getSlotByUser")
    public Slot printSlotByUser() {
        slotDao.createNewSlot(new Slot("14:00", "16:00",  125.00, false, userDao.findByEmail("test2@test.test"),dayDao.findByDate(LocalDate.of(2022, 1, 22)), studyRoomDao.findById(1) ));
        return slotDao.getSlotByUser(userDao.findByEmail("test2@test.test"));
    }

    @Transactional
    @GetMapping("changeSlot")
    public void changeStatus(){
        slotService.changePaidStatus( slotDao.getSlotByUser(userDao.findByEmail("test3@test.test")), true);
    }

    @Transactional
    @GetMapping("changeRoom")
    public void changeRoom() {
        slotService.changeRoom(slotDao.getSlotByStudyRoom(2), 6);
    }

    @Transactional
    @GetMapping("userPaysForSlots")
    public List<Slot> userPaysForSlots() {
        slotDao.createNewSlot(new Slot("9:00", "10:00",  125.00, false, dayDao.findByDate(LocalDate.of(2022, 1, 22)), studyRoomDao.findById(1) ));
        slotDao.createNewSlot(new Slot("10:00", "11:00",  125.00, false, dayDao.findByDate(LocalDate.of(2022, 1, 22)), studyRoomDao.findById(1) ));
        slotDao.createNewSlot(new Slot("11:00", "12:00",  125.00, false, dayDao.findByDate(LocalDate.of(2022, 1, 22)), studyRoomDao.findById(1) ));
        userService.payForSlot(userDao.findByEmail("test2@test.test"), slotDao.findAll());

        return slotDao.findAll();
    }

}
