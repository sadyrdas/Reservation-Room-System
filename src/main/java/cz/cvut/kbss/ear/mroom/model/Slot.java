package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "slot")
@NamedQueries(
        @NamedQuery(name = "Slot.findByUserEmail", query = "SELECT s FROM Slot s WHERE s.user = :email")
)
public class Slot extends AbstractEntity {
//
//
//    @Basic(optional = false)
//    @Column(nullable = false)
//    private Boolean isAvailable;

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

    @OneToOne
    private Day day;

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer studyroom_id;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;

    @ManyToMany(mappedBy = "slots")
    private List<StudyRoom> rooms = new ArrayList<>();


    public Slot(String start, String finish,  Double price, boolean paid, User user, Day day, StudyRoom studyRoom) {
        this.start = start;
        this.finish = finish;
        this.paid = paid;
        this.price = price;
        this.user = user;
        this.day = day;
        this.studyroom_id = studyRoom.getId();
    }


    
    

    public Slot() {

    }
//
//    public Boolean getAvailable() {
//        return isAvailable;
//    }
//
//    public void setAvailable(Boolean available) {
//        isAvailable = available;
//    }

    public String getStart() {
        return start;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Integer getStudyroom_id() {
        return studyroom_id;
    }

    public void setStudyroom_id(Integer studyroom_id) {
        this.studyroom_id = studyroom_id;
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

    public List<StudyRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<StudyRoom> rooms) {
        this.rooms = rooms;
    }
}
