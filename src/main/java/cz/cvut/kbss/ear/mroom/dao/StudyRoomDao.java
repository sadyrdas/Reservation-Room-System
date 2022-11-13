package cz.cvut.kbss.ear.mroom.dao;

import cz.cvut.kbss.ear.mroom.model.StudyRoom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudyRoomDao extends BaseDao<StudyRoom>{
    protected StudyRoomDao() {
        super(StudyRoom.class);
    }

    public List<StudyRoom> getAllRooms() {
        return findAll();

    }
}
