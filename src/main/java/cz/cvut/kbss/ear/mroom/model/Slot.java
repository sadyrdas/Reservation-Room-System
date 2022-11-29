package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;

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
}
