package cz.cvut.kbss.ear.mroom.service;

import cz.cvut.kbss.ear.mroom.Environment.Generator;
import cz.cvut.kbss.ear.mroom.MeetingRoomReservationApplication;
import cz.cvut.kbss.ear.mroom.dao.ReservationDateDao;
import cz.cvut.kbss.ear.mroom.model.ReservationDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@DataJpaTest
@ComponentScan(basePackageClasses = MeetingRoomReservationApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
public class ReservationDateServiceTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private ReservationDateService reservationDateService;

    @Autowired
    private ReservationDateDao reservationDateDao;

    @Test
    public void createDayTest() {
        ReservationDate reservationDate = Generator.generateDay();
        reservationDateService.createDay(reservationDate);
        assertTrue(reservationDateDao.exists(reservationDate.getId()));
    }

    @Test
    public void findDayByLocalDateTest() {
        ReservationDate reservationDate = Generator.generateDay();
        em.persist(reservationDate);

        assertEquals(reservationDate.getId(), reservationDateService.findDayByDate(reservationDate.getPosting_date()).getId());
    }


}
