package cz.cvut.kbss.ear.mroom.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Week extends AbstractEntity{

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
    private List<Day> days = new ArrayList<>();


}
