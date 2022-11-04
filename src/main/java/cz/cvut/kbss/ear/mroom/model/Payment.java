package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Payments")
public class Payment extends AbstractEntity {
    @Basic
    @Column(nullable = false)
    private String paymentMethod;

    @Basic
    @Column(nullable = false)
    private Boolean status;

    public Payment(String paymentMethod, Boolean status) {
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public Payment() {

    }
}
