package cz.cvut.kbss.ear.mroom.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "studyroom")
@NamedQueries({
        @NamedQuery(name = "Studyroom.findAllAvailableRooms", query = "SELECT st FROM StudyRoom st where st.isAvailable = true  "),
        @NamedQuery(name = "Studyroom.findById", query = "SELECT st from StudyRoom st where st.id = :id")
})
public class StudyRoom extends AbstractEntity{
    @Basic(optional = false)
    @Column(nullable = false)
    private int capacity;



    @Basic(optional = false)
    @Column(nullable = false)
    private Integer reservation;


    @Basic(optional = false)
    @Column(nullable = false)
    private boolean isAvailable;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "studyroom_slot",
            joinColumns = @JoinColumn(name = "studyroom_id"),
            inverseJoinColumns = @JoinColumn(name = "slot_id")
    )
    private List<Slot> slots = new ArrayList<>();





    public StudyRoom(int capacity, Integer reservation, boolean isAvailable) {
        this.capacity = capacity;
        this.reservation = reservation;
        this.isAvailable = isAvailable;
    }

    public StudyRoom() {
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Integer getReservation() {
        return reservation;
    }

    public void setReservation(Integer reservation) {
        this.reservation = reservation;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public boolean isAvailable() {
        return true;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
