package cz.cvut.kbss.ear.mroom.dao;

import cz.cvut.kbss.ear.mroom.model.Payment;
import cz.cvut.kbss.ear.mroom.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDao extends BaseDao<Payment> {
    public PaymentDao() {
        super(Payment.class);
    }

    public List<Payment> getAllPayments() {
        return findAll();
    }
}
