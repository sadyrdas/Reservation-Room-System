package cz.cvut.kbss.ear.mroom.service;

import cz.cvut.kbss.ear.mroom.dao.SlotDao;
import cz.cvut.kbss.ear.mroom.dao.UserDao;
import cz.cvut.kbss.ear.mroom.model.Day;
import cz.cvut.kbss.ear.mroom.model.Slot;
import cz.cvut.kbss.ear.mroom.model.StudyRoom;
import cz.cvut.kbss.ear.mroom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.StyledEditorKit;
import java.util.Objects;

@Service
public class SlotService {
    private final SlotDao slotDao;

    @Autowired
    public SlotService(SlotDao slotDao) {
        this.slotDao = slotDao;
    }

    @Transactional
    public Boolean createSlot(String start, String finish, Double price, Boolean paid, User user, Day day, StudyRoom studyRoom) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(finish);
        Objects.requireNonNull(price);
        Objects.requireNonNull(user);
        Objects.requireNonNull(day);
        Objects.requireNonNull(studyRoom);

        Boolean ret = false;

        if (start.isEmpty() || finish.isEmpty() || user.getEmail().isEmpty()) {
            return ret;
        } else if (slotDao.findByPrice(price) != null) {
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
    public void changeRoom(Slot slot, int studyroom_id){
        slot.setStudyroom_id(studyroom_id);
        slotDao.update(slot);
    }

    @Transactional
    public void setSlotOwner(Slot slot, User owner) {
        slot.setUser(owner);
        slotDao.update(slot);
    }


}
