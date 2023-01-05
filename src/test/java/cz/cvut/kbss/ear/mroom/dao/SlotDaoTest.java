package cz.cvut.kbss.ear.mroom.dao;

import cz.cvut.kbss.ear.mroom.Environment.Generator;
import cz.cvut.kbss.ear.mroom.MeetingRoomReservationApplication;
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
import static org.junit.Assert.*;

@DataJpaTest
@ComponentScan(basePackageClasses = MeetingRoomReservationApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
public class SlotDaoTest {
    @Autowired
    private TestEntityManager em;

    @Autowired
    private SlotDao slotDao;

    @Test
    public void getSlotByUserTest() {
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


        assertEquals(slot, slotDao.getSlotByUser(user));
    }

    @Test
    public void findAllSlotsByUserEmailTest() {
        final User user = Generator.generateUser();
        final ReservationDate reservationDate = generateDay();
        final StudyRoom studyRoom = generateStudyRoom();

        em.persist(user);
        em.persist(reservationDate);
        em.persist(studyRoom);

        for (int i = 0; i < 3; i++) {
            final Slot slot = Generator.generateSlot();
            slot.setReservationDay(reservationDate);
            slot.setStudyroom_id(studyRoom);
            slot.setUser(user);
            em.persist(slot);
        }

        assertEquals(3, slotDao.findAllSlotsByUserEmail(user).size());
    }

    @Test
    public void createSlotTest() {
        final Slot slot = Generator.generateSlot();
        slotDao.createNewSlot(slot);
        assertTrue(slotDao.exists(slot.getId()));
    }

    @Test
    public void findByPriceSlot() {
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

        assertEquals(slot, slotDao.findByPrice(slot.getPrice()));
    }
}
