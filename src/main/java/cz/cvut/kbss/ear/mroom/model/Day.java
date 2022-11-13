package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Day extends AbstractEntity {

//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(nullable = false)
//    private Week week;

    @OrderBy(value = "num_of_day")
    @OneToMany(mappedBy = "day", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Slot> slots = new ArrayList<>();
}
