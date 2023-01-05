package cz.cvut.kbss.ear.mroom.service;

import cz.cvut.kbss.ear.mroom.Environment.Generator;
import cz.cvut.kbss.ear.mroom.MeetingRoomReservationApplication;
import cz.cvut.kbss.ear.mroom.dao.StudyRoomDao;
import cz.cvut.kbss.ear.mroom.model.StudyRoom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ComponentScan(basePackageClasses = MeetingRoomReservationApplication.class)
@RunWith(SpringRunner.class)
public class StudyroomServiceTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private StudyroomService studyroomService;

    @Autowired
    private StudyRoomDao studyRoomDao;

    @Test
    public void findByStatusTest(){
        for (int i = 0; i < 5; i++) {
            final StudyRoom studyRoom = Generator.generateStudyRoom();
            testEntityManager.persist(studyRoom);
        }

        List<StudyRoom> result = studyroomService.getRoomsByStatus(false);
        assertEquals(result.size(), 5);
    }

    @Test
    public void updateAvailableRooms(){
        final StudyRoom studyRoom = Generator.generateStudyRoom();
        testEntityManager.persist(studyRoom);

        studyroomService.updateAvailableRoom(studyRoom.getId(), true);
        assertTrue(studyRoomDao.findById(studyRoom.getId()).isAvailable());
    }

}
