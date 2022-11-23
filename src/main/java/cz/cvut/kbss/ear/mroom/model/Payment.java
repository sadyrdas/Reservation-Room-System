package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@NamedQueries({
        @NamedQuery(name = "Payment.findById", query = "SELECT p FROM Payment p WHERE p.id = :id")
})
public class Payment extends AbstractEntity {
    @Basic(optional = false)
    @Column(nullable = false)
    private String paymentMethod;

    @Basic(optional = false)
    @Column(nullable = false)
    private int moneyToPay;

    @Basic(optional = false)
    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name="userid", nullable = false)
    private User user;

    @OneToOne(mappedBy = "payment_id")
    private Slot slot;

    public Payment(String paymentMethod, Boolean status, Integer moneyToPay, User userId) {
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.moneyToPay = moneyToPay;
        this.user = userId;

    }

    public Payment() {

    }


    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getUser() {
        return user.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getMoneyToPay() {
        return moneyToPay;
    }

    public void setMoneyToPay(int moneyToPay) {
        this.moneyToPay = moneyToPay;
    }
}
