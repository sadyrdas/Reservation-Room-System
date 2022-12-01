package cz.cvut.kbss.ear.mroom.dao;

import cz.cvut.kbss.ear.mroom.model.StudyRoom;
import cz.cvut.kbss.ear.mroom.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class StudyRoomDao extends BaseDao<StudyRoom>{
    protected StudyRoomDao() {
        super(StudyRoom.class);
    }


    @Transactional
    public StudyRoom getAvailableRooms(){
        try {
            return em.createNamedQuery("Studyroom.findAllAvailableRooms", StudyRoom.class).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public Boolean createRoom(StudyRoom studyRoom) {
        try {
            em.persist(studyRoom);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Transactional
    public StudyRoom findById(int id) {
        try {
            return em.createNamedQuery("Studyroom.findById", StudyRoom.class).setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void updateAvailableRoom(int id, boolean change){
        StudyRoom studyRoom = findById(id);
        studyRoom.setAvailable(change);
    }


}
