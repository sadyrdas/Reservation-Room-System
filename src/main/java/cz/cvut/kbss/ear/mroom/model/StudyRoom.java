package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class StudyRoom extends AbstractEntity{
    @Basic(optional = false)
    private int capacity;
    @Basic(optional = false)
    private double price;
    @Basic(optional = false)
    private String reservation;

    public StudyRoom(int capacity, double price, String reservation) {
        this.capacity = capacity;
        this.price = price;
        this.reservation = reservation;
    }

    public StudyRoom() {

    }
}
