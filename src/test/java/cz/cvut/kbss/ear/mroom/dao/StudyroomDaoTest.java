package cz.cvut.kbss.ear.mroom.dao;

import cz.cvut.kbss.ear.mroom.Environment.Generator;
import cz.cvut.kbss.ear.mroom.MeetingRoomReservationApplication;
import cz.cvut.kbss.ear.mroom.model.StudyRoom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@ComponentScan(basePackageClasses = MeetingRoomReservationApplication.class)
@RunWith(SpringRunner.class)
public class StudyroomDaoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private StudyRoomDao studyRoomDao;

    @Test
    public void findByIdTest(){
        final StudyRoom studyRoom = Generator.generateStudyRoom();
        testEntityManager.persist(studyRoom);

        final StudyRoom result = studyRoomDao.findById(studyRoom.getId());
        assertNotNull(result);
        assertEquals(studyRoom.getId(), result.getId());
    }

    @Test
    public void updateStatusTest(){
        final StudyRoom studyRoom = Generator.generateStudyRoom();
        testEntityManager.persist(studyRoom);
        studyRoomDao.updateAvailableRoom(studyRoom.getId(), false);
        assertFalse(studyRoomDao.findById(studyRoom.getId()).isAvailable());
    }

    @Test
    public void deleteRoomTest(){
        final StudyRoom studyRoom = Generator.generateStudyRoom();
        testEntityManager.persist(studyRoom);
        studyRoomDao.deleteRoomById(studyRoom.getId());
        assertNull(studyRoomDao.findById(studyRoom.getId()));
    }
}
