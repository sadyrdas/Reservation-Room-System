package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;

@Entity
public class Slot extends AbstractEntity {


    @Basic(optional = false)
    @Column(nullable = false)
    private Boolean isAvailable;



    private DaysOfWeek num_of_day;


    @Basic(optional = false)
    @Column(nullable = false)
    private String start;

    @Basic(optional = false)
    @Column(nullable = false)
    private String finish;

    @Basic(optional = false)
    @Column(nullable = false)
    private int amountOfSlotForDay;

    public Slot(String start, String finish, Boolean isAvailable, Day nameOfDay, DaysOfWeek num_of_day, int amountOfSlotForDay) {
        this.start = start;
        this.finish = finish;
        this.isAvailable = isAvailable;
        this.num_of_day = num_of_day;
        this.amountOfSlotForDay = amountOfSlotForDay;
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

    public DaysOfWeek getNum_of_day(){
        return num_of_day;
    }

    public void setNum_of_day(DaysOfWeek num_of_day){
        this.num_of_day = num_of_day;

    }
}
