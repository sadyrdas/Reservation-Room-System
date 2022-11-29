package cz.cvut.kbss.ear.mroom.model;


import javax.persistence.*;


@Entity
@Table
public class StudyRoom extends AbstractEntity{
    @Basic(optional = false)
    @Column(nullable = false)
    private int capacity;


    @Basic(optional = false)
    @Column(nullable = false)
    private double price;


    @Basic(optional = false)
    @Column(nullable = false)
    private Integer reservation;


    @OneToOne()
    private User user;


    public StudyRoom(int capacity, double price, Integer reservation) {
        this.capacity = capacity;
        this.price = price;
        this.reservation = reservation;
    }

    public StudyRoom() {
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getReservation() {
        return reservation;
    }

    public void setReservation(Integer reservation) {
        this.reservation = reservation;
    }

    public Integer getUser() {
        return user.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
