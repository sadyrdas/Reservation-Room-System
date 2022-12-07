package cz.cvut.kbss.ear.mroom.dao;

import cz.cvut.kbss.ear.mroom.model.Slot;
import cz.cvut.kbss.ear.mroom.model.StudyRoom;
import cz.cvut.kbss.ear.mroom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Objects;

@Repository
public class SlotDao extends BaseDao<Slot>{


    @Autowired
    public SlotDao() {
        super(Slot.class);
    }

    @Transactional
    public Slot getSlotByUser(User email) {
        try {
            return em.createNamedQuery("Slot.findByUserEmail", Slot.class).setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public Boolean createNewSlot(Slot slot) {
        Objects.requireNonNull(slot);
        try {
            em.persist(slot);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Transactional
    public Slot findByPrice(Double price) {
        try {
            return em.createNamedQuery("Slot.findPriceById", Slot.class).setParameter("price", price)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

//    @Transactional
//    public Slot getSlotById(Integer studyroom_id) {
//        try {
//            return em.createNamedQuery("Slot.findByStudyroomID", Slot.class).setParameter("studyroom_id", studyroom_id)
//                    .getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
//    }





}
