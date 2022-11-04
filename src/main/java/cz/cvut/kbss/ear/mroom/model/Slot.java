package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;

@Entity
public class Slot extends AbstractEntity {


    @Column(nullable = false)
    private Boolean isAvailable;

    @ManyToOne
    @JoinColumn
    @Column(nullable = false)
    private Day day;

}
