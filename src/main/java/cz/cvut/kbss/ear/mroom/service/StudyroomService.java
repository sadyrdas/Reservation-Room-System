package cz.cvut.kbss.ear.mroom.service;


import cz.cvut.kbss.ear.mroom.dao.StudyRoomDao;
import cz.cvut.kbss.ear.mroom.model.StudyRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Boolean createStudyroom(Integer capacity, Integer price, Boolean isAvailable) {

        Boolean ret = false;

        if (capacity == 0 || price == 0) {
            return ret;
        } else {
            //
            studyRoomDao.createRoom(new StudyRoom(capacity, price, isAvailable));
        }

        return true;
    }


    @Transactional
    public Boolean updateAvailableRoom(int id, boolean change){
        Boolean result = false;
        if(id == 0) {
            return result;
        }else {
            studyRoomDao.updateAvailableRoom(id, change);
        }
        return true;


    }



}
