package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Slot extends AbstractEntity {


    @Basic(optional = false)
    @Column(nullable = false)
    private Boolean isAvailable;

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

    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;

    @ManyToMany(mappedBy = "slots")
    private List<StudyRoom> rooms = new ArrayList<>();


    public Slot(String start, String finish, Boolean isAvailable, Double price, boolean paid) {
        this.start = start;
        this.finish = finish;
        this.isAvailable = true;
        this.paid = false;
        this.price = price;
    }


    
    

    public Slot() {

    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getStart() {
        return start;
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

    public String getUser_email() {
        String u = user.getEmail();
        return u;
    }

    public void setUser_email(User user, String email) {
        user.setEmail(email);
    }

    public List<StudyRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<StudyRoom> rooms) {
        this.rooms = rooms;
    }
}
