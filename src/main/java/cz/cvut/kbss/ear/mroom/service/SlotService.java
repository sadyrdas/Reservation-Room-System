package cz.cvut.kbss.ear.mroom.service;

import cz.cvut.kbss.ear.mroom.dao.SlotDao;
import cz.cvut.kbss.ear.mroom.dao.StudyRoomDao;
import cz.cvut.kbss.ear.mroom.model.ReservationDate;
import cz.cvut.kbss.ear.mroom.model.Slot;
import cz.cvut.kbss.ear.mroom.model.StudyRoom;
import cz.cvut.kbss.ear.mroom.model.User;
import cz.cvut.kbss.ear.mroom.rest.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class SlotService {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private final SlotDao slotDao;
    private final StudyRoomDao studyRoomDao;

    @Autowired
    public SlotService(SlotDao slotDao, StudyRoomDao studyRoomDao) {
        this.slotDao = slotDao;
        this.studyRoomDao = studyRoomDao;
    }

    @Transactional
    public Boolean createSlot(String start, String finish, Double price, Boolean paid, User user, ReservationDate day, StudyRoom studyRoom) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(finish);
        Objects.requireNonNull(price);
        Objects.requireNonNull(day);
        Objects.requireNonNull(studyRoom);

        Boolean ret = false;

        if (start.isEmpty() || finish.isEmpty()) {
            return ret;
        } else {
            slotDao.createNewSlot(new Slot(start, finish, price, paid, user, day, studyRoom));
        }

        return true;
    }
    @Transactional
    public void changePaidStatus(Slot slot, Boolean paid){
        slot.setPaid(paid);
        slotDao.update(slot);
    }

    @Transactional
    public void changeRoom(Slot slot, StudyRoom studyroom_id){
        slot.setStudyroom_id(studyroom_id);
        slotDao.update(slot);
    }

    @Transactional
    public void changeDay(Slot slot, ReservationDate day) {
        slot.setReservationDay(day);
        slotDao.update(slot);
    }

    @Transactional
    public void setSlotOwner(Slot slot, User owner) {
        slot.setUser(owner);
        slotDao.update(slot);
    }

    @Transactional
    public Slot findSlotById(Integer id) {
        return slotDao.find(id);
    }

    @Transactional
    public void deleteSlotById(Integer id) {
        if (findSlotById(id) != null) {
            slotDao.remove(findSlotById(id));
            LOG.info("Slot with id {} was deleted.", id);
            return;
        }
        LOG.error("Cannot find slot by id {}. So it can not be deleted.", id);
    }

    @Transactional
    public List<Slot> findAllSlots() {
        return slotDao.findAll();
    }

    @Transactional
    public List<Slot> findAllSlotsByUserId(User user) {
        return slotDao.findAllSlotsByUserEmail(user);
    }

    @Transactional
    public Slot getSlotById(Integer id) {
        return slotDao.find(id);
    }
}
