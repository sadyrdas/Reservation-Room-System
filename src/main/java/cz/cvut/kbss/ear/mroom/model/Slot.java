package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;

@Entity
@Table(name = "slot")
@NamedQueries({
        @NamedQuery(name = "Slot.findByUserEmail", query = "SELECT s FROM Slot s WHERE s.user = :user"),
        @NamedQuery(name = "Slot.findPriceById", query = "SELECT s FROM Slot s WHERE s.price = :price"),
        @NamedQuery(name = "Slot.findByStudyroomID", query = "SELECT s From Slot s Where s.studyroom_id = :studyroom_id"),
        @NamedQuery(name = "Slot.findById", query = "SELECT s FROM Slot s WHERE s.id = :id"),


})
public class Slot extends AbstractEntity {


    @Basic(optional = false)
    @Column(nullable = false)
    private Double price;

    @Basic(optional = false)
    @Column(nullable = false)
    private boolean paid;

    @Basic(optional = false)
    @Column(nullable = false)
    private String start;

    @Basic(optional = false)
    @Column(nullable = false)
    private String finish;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "reservationdate", nullable = false)
    private ReservationDate reservationDay;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "studyroom_id", nullable = true)
    private StudyRoom studyroom_id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;








    public Slot(String start, String finish, Double price, boolean paid, User user, ReservationDate reservationDay, StudyRoom studyRoom) {
        this.start = start;
        this.finish = finish;
        this.paid = paid;
        this.price = price;
        this.user = user;
        this.reservationDay = reservationDay;
        this.studyroom_id = studyRoom;
    }

    public Slot(String start, String finish, Double price, boolean paid, ReservationDate reservationDay, StudyRoom studyRoom) {
        this.price = price;
        this.paid = paid;
        this.start = start;
        this.finish = finish;
        this.reservationDay = reservationDay;
        this.studyroom_id = studyRoom;
    }

    public Slot() {

    }

    public String getStart() {
        return start;
    }

    public ReservationDate getReservationDay() {
        return reservationDay;
    }

    public void setReservationDay(ReservationDate reservationDay) {
        this.reservationDay = reservationDay;
    }



    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public StudyRoom getStudyroom_id() {
        return studyroom_id;
    }

    public void setStudyroom_id(StudyRoom studyroom_id) {
        this.studyroom_id = studyroom_id;
    }
}
