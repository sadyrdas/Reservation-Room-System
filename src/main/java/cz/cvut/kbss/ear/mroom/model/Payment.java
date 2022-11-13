package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;

@Entity
@Table(name = "payments")
public class Payment extends AbstractEntity {
    @Basic
    @Column(nullable = false)
    private String paymentMethod;

    @Basic
    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name="id", nullable = false)
    private User user;

    public Payment(String paymentMethod, Boolean status) {
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public Payment() {

    }
}
