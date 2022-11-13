package cz.cvut.kbss.ear.mroom.dao;

import cz.cvut.kbss.ear.mroom.model.Payment;
import cz.cvut.kbss.ear.mroom.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class PaymentDao extends BaseDao<Payment> {
    public PaymentDao() {
        super(Payment.class);
    }

    public List<Payment> getAllPayments() {
        return findAll();
    }

    public Payment findById(int id){
        try {
            return em.createNamedQuery("Payment.findById", Payment.class).setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
