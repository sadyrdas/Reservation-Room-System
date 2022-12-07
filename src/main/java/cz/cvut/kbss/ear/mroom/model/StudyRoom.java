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
    private int price;



    @Basic(optional = false)
    @Column(nullable = false)
    private boolean isAvailable;






    public StudyRoom(Integer capacity, Integer price, boolean isAvailable) {
        this.capacity = capacity;
        this.isAvailable = isAvailable;
        this.price = price;
    }

    public StudyRoom() {
    }



    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }



    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
