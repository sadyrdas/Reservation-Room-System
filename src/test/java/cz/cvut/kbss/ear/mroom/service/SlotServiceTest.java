package cz.cvut.kbss.ear.mroom.service;

import cz.cvut.kbss.ear.mroom.Environment.Generator;
import cz.cvut.kbss.ear.mroom.MeetingRoomReservationApplication;
import cz.cvut.kbss.ear.mroom.dao.UserDao;
import cz.cvut.kbss.ear.mroom.model.ReservationDate;
import cz.cvut.kbss.ear.mroom.model.Slot;
import cz.cvut.kbss.ear.mroom.model.StudyRoom;
import cz.cvut.kbss.ear.mroom.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static cz.cvut.kbss.ear.mroom.Environment.Generator.generateDay;
import static cz.cvut.kbss.ear.mroom.Environment.Generator.generateStudyRoom;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ComponentScan(basePackageClasses = MeetingRoomReservationApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
public class SlotServiceTest {
    @Autowired
    private TestEntityManager em;

    @Autowired
    private SlotService slotService;

    @Autowired
    private UserDao userDao;

    @Test
    public void createSlotTest() {
        final User user = Generator.generateUser();
        final Slot slot = Generator.generateSlot();
        final ReservationDate reservationDate = generateDay();
        final StudyRoom studyRoom = generateStudyRoom();
        slot.setReservationDay(reservationDate);
        slot.setStudyroom_id(studyRoom);
        slot.setUser(user);

        em.persist(user);
        em.persist(reservationDate);
        em.persist(studyRoom);

        slotService.createSlot(slot.getStart(), slot.getFinish(), slot.getPrice(),
                slot.isPaid(), user, reservationDate, studyRoom);

        assertEquals(1, slotService.findAllSlotsByUserId(user).size());
        assertEquals(slot.getPrice(), slotService.findAllSlotsByUserId(user).get(0).getPrice());
        assertEquals(slot.getStudyroom_id(),
                slotService.findAllSlotsByUserId(user).get(0).getStudyroom_id());
        assertEquals(slot.getStart(), slotService.findAllSlotsByUserId(user).get(0).getStart());
    }

    @Test
    public void setSlotOwnerTest() {

        final User user = Generator.generateUser();
        final Slot slot = Generator.generateSlot();
        final ReservationDate reservationDate = generateDay();
        final StudyRoom studyRoom = generateStudyRoom();
        slot.setReservationDay(reservationDate);
        slot.setStudyroom_id(studyRoom);
        slot.setUser(user);

        em.persist(user);
        em.persist(reservationDate);
        em.persist(studyRoom);
        em.persist(slot);

        final User user2 = Generator.generateUser();
        em.persist(user2);

        slotService.setSlotOwner(slot, user2);

        assertEquals(slotService.findAllSlotsByUserId(user2).get(0).getUser(), userDao.findByEmail(user2.getEmail()));
    }

    @Test
    public void deleteSlotById() {

        final User user = Generator.generateUser();
        final Slot slot = Generator.generateSlot();
        final ReservationDate reservationDate = generateDay();
        final StudyRoom studyRoom = generateStudyRoom();
        slot.setReservationDay(reservationDate);
        slot.setStudyroom_id(studyRoom);
        slot.setUser(user);

        em.persist(user);
        em.persist(reservationDate);
        em.persist(studyRoom);
        em.persist(slot);

        assertEquals(1, slotService.findAllSlots().size());
        slotService.deleteSlotById(slotService.findSlotById(slot.getId()).getId());
        assertEquals(0, slotService.findAllSlots().size());
    }

    @Test
    public void changePaidStatusTest() {
        final User user = Generator.generateUser();
        final Slot slot = Generator.generateSlot();
        final ReservationDate reservationDate = generateDay();
        final StudyRoom studyRoom = generateStudyRoom();
        slot.setReservationDay(reservationDate);
        slot.setStudyroom_id(studyRoom);
        slot.setUser(user);

        em.persist(user);
        em.persist(reservationDate);
        em.persist(studyRoom);
        em.persist(slot);

        slotService.changePaidStatus(slot, true);
        assertTrue(slotService.findSlotById(slot.getId()).isPaid());
    }

    @Test
    public void changeDayTest() {
        final User user = Generator.generateUser();
        final Slot slot = Generator.generateSlot();
        final ReservationDate reservationDate = generateDay();
        final StudyRoom studyRoom = generateStudyRoom();
        slot.setReservationDay(reservationDate);
        slot.setStudyroom_id(studyRoom);
        slot.setUser(user);

        em.persist(user);
        em.persist(reservationDate);
        em.persist(studyRoom);
        em.persist(slot);

        final ReservationDate reservationDate1 = generateDay();
        em.persist(reservationDate1);
        assertEquals(slotService.findSlotById(slot.getId()).getReservationDay(), reservationDate);
        slotService.changeDay(slot, reservationDate1);
        assertEquals(slotService.findSlotById(slot.getId()).getReservationDay(), reservationDate1);
    }

    @Test
    public void changeRoomTest() {

        final User user = Generator.generateUser();
        final Slot slot = Generator.generateSlot();
        final ReservationDate reservationDate = generateDay();
        final StudyRoom studyRoom = generateStudyRoom();
        slot.setReservationDay(reservationDate);
        slot.setStudyroom_id(studyRoom);
        slot.setUser(user);

        em.persist(user);
        em.persist(reservationDate);
        em.persist(studyRoom);
        em.persist(slot);

        final StudyRoom studyRoom1 = generateStudyRoom();
        em.persist(studyRoom1);

        assertEquals(slotService.findSlotById(slot.getId()).getStudyroom_id(), studyRoom);
        slotService.changeRoom(slot, studyRoom1);
        assertEquals(slotService.findSlotById(slot.getId()).getStudyroom_id(), studyRoom1);
    }

    @Test
    public void findAllSlotsByUserID() {
        final User user = Generator.generateUser();
        final ReservationDate reservationDate = generateDay();
        final StudyRoom studyRoom = generateStudyRoom();

        em.persist(user);
        em.persist(reservationDate);
        em.persist(studyRoom);

        for(int i = 0; i < 5; i ++) {
            final Slot slot = Generator.generateSlot();
            slot.setReservationDay(reservationDate);
            slot.setStudyroom_id(studyRoom);
            slot.setUser(user);
            em.persist(slot);
        }



        assertEquals(5, slotService.findAllSlotsByUserId(user).size());
    }

    @Test
    public void findSlotByIdTest() {

        final User user = Generator.generateUser();
        final Slot slot = Generator.generateSlot();
        final ReservationDate reservationDate = generateDay();
        final StudyRoom studyRoom = generateStudyRoom();
        slot.setReservationDay(reservationDate);
        slot.setStudyroom_id(studyRoom);
        slot.setUser(user);

        em.persist(user);
        em.persist(reservationDate);
        em.persist(studyRoom);
        em.persist(slot);

        assertEquals(slot.getId(), slotService.findSlotById(slot.getId()).getId());
    }
}
