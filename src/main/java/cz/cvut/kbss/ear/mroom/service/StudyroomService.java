//package cz.cvut.kbss.ear.mroom.service;
//
//
//import cz.cvut.kbss.ear.mroom.dao.StudyRoomDao;
//import cz.cvut.kbss.ear.mroom.model.Slot;
//import cz.cvut.kbss.ear.mroom.model.StudyRoom;
//import cz.cvut.kbss.ear.mroom.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//public class StudyroomService {
//    private final StudyroomService studyroomService;
//    private final StudyRoomDao studyRoomDao;
//
//
//    @Autowired
//    public StudyroomService(StudyroomService studyroomService, StudyRoomDao studyRoomDao) {
//        this.studyroomService = studyroomService;
//        this.studyRoomDao = studyRoomDao;
//    }
//
//    @Transactional
//    public Boolean createStudyroom(Integer capacity, Integer price, List<Slot> reservation, Boolean isAvailable) {
//
//        Boolean ret = false;
//
//        if (capacity == 0 || price == 0) {
//            return ret;
//        } else {
//            // TODO CHECK LENGTH OF INPUTs
//            studyRoomDao.createRoom(new StudyRoom(capacity, price, reservation, isAvailable));
//        }
//
//        return true;
//    }
//
//
//}
