package cz.cvut.kbss.ear.mroom.dao;

import cz.cvut.kbss.ear.mroom.Environment.Generator;
import cz.cvut.kbss.ear.mroom.MeetingRoomReservationApplication;
import cz.cvut.kbss.ear.mroom.model.ReservationDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ComponentScan(basePackageClasses = MeetingRoomReservationApplication.class)
@RunWith(SpringRunner.class)
public class ReservationDateDaoTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ReservationDateDao reservationDateDao;

    @Test
    public void findByDateTest(){
        final ReservationDate day = Generator.generateDay();
        testEntityManager.persist(day);

        final ReservationDate result = reservationDateDao.findByDate(day.getPosting_date());
        assertNotNull(result);
        assertEquals(day.getId(), result.getId());
    }


}
