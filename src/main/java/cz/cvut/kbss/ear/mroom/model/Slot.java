package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;

@Entity
public class Slot extends AbstractEntity {


    @Basic(optional = false)
    @Column(nullable = false)
    private Boolean isAvailable;

    @Basic(optional = false)
    @Column(updatable = false)
    private final int num_of_day;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Day day;

    @Basic(optional = false)
    @Column(nullable = false)
    private String start;

    @Basic(optional = false)
    @Column(nullable = false)
    private String finish;

    public Slot(int num_of_day, Day day) {
        this.num_of_day = num_of_day;
        this.day = day;
    }

    public Slot() {
        this.num_of_day = -1;
    }
}
