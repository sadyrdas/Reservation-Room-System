package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Day extends AbstractEntity {

    @ManyToOne
    @JoinColumn
    private Week week;

    @OneToMany(mappedBy = "day", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Slot> slots = new ArrayList<>();
}
