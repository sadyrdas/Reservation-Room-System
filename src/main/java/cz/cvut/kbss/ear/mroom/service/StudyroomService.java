package cz.cvut.kbss.ear.mroom.service;


import cz.cvut.kbss.ear.mroom.dao.StudyRoomDao;
import cz.cvut.kbss.ear.mroom.model.StudyRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class StudyroomService {
    private final StudyroomService studyroomService;
    private final StudyRoomDao studyRoomDao;


    @Lazy
    @Autowired
    public StudyroomService(StudyroomService studyroomService, StudyRoomDao studyRoomDao) {
        this.studyroomService = studyroomService;
        this.studyRoomDao = studyRoomDao;
    }

    @Transactional
    public StudyRoom findStudyRoomById(Integer id) {
        return studyRoomDao.find(id);
    }

    @Transactional
    public Boolean createStudyroom(Integer capacity, Integer price) {

        Boolean ret = false;

        if (capacity == 0 || price == 0) {
            return ret;
        } else {
            //
            studyRoomDao.createRoom(new StudyRoom(capacity, price));
        }

        return true;
    }


    @Transactional
    public List<StudyRoom> getRoomByStatus(Boolean isAvailable){
        return studyRoomDao.getAvailableRooms(isAvailable);
    }


    @Transactional
    public void updateAvailableRoom(int id, Boolean change) {
        studyRoomDao.updateAvailableRoom(id, change);

    }




}
