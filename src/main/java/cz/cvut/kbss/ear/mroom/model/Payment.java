package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@NamedQueries({
        @NamedQuery(name = "Payment.findById", query = "SELECT p FROM Payment p WHERE p.id = :id")
})
public class Payment extends AbstractEntity {
    @Basic
    @Column(nullable = false)
    private String paymentMethod;

    @Basic
    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name="userid", nullable = false)
    private User user;

    public Payment(String paymentMethod, Boolean status) {
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public Payment() {

    }
}
