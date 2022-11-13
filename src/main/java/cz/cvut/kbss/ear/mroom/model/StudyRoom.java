package cz.cvut.kbss.ear.mroom.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table
public class StudyRoom extends AbstractEntity{
    @Basic(optional = false)
    private int capacity;
    @Basic(optional = false)
    private double price;
    @Basic(optional = false)
    private String reservation;


    @OneToOne()
    private User user;


    public StudyRoom(int capacity, double price, String reservation) {
        this.capacity = capacity;
        this.price = price;
        this.reservation = reservation;
    }

    public StudyRoom() {

    }
}
