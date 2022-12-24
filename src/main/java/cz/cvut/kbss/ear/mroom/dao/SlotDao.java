package cz.cvut.kbss.ear.mroom.dao;

import cz.cvut.kbss.ear.mroom.model.Slot;
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
    public Slot getSlotByUser(User user) {
        try {
            return em.createNamedQuery("Slot.findByUserEmail", Slot.class).setParameter("user", user)
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

    @Transactional
    public List<Slot> findAllSlotsByUserEmail(User user) {
        try {
            return em.createNamedQuery("Slot.findByUserEmail", Slot.class).setParameter("user", user)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
