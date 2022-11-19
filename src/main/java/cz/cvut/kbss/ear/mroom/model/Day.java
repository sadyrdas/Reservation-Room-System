package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Day extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.MERGE)
    private Week week;




    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    @OneToMany
    private List<Slot> AmountOfSlot;

    public Day(Week week, String name, List<Slot> amountOfSlot) {
        this.week = week;
        this.name = name;
        AmountOfSlot = amountOfSlot;
    }

    public Day() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
