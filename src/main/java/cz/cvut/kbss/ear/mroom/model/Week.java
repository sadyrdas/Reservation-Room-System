package cz.cvut.kbss.ear.mroom.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table
public class Week extends AbstractEntity{



//    @OneToMany(mappedBy = "week", orphanRemoval = true, cascade = CascadeType.ALL)
//    @MapKeyEnumerated(EnumType.STRING)
//    private Map<DaysOfWeek, Day> days;

    @Basic(optional = false)
    @Column(nullable = false)
    private int numberOfWeek;

    public Week() {
    }

    public Week(int numberOfWeek){
        this.numberOfWeek = numberOfWeek;

    }

}
