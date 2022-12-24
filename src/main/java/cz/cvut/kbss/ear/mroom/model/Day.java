package cz.cvut.kbss.ear.mroom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "day")
@NamedQueries({
        @NamedQuery(name = "Day.findByDate", query = "SELECT d FROM Day d WHERE d.posting_date = :date")
})
public class Day extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private LocalDate posting_date;


    @OneToMany(mappedBy = "day")
    @JsonBackReference
    private Set<Slot> slotSet;


    public Day(LocalDate posting_date) {
        this.posting_date = posting_date;
    }

    public Day() {
    }


    public LocalDate getPosting_date() {
        return posting_date;
    }

    public void setPosting_date(LocalDate posting_date) {
        this.posting_date = posting_date;
    }

    public Set<Slot> getSlotSet() {
        return slotSet;
    }

    public void setSlotSet(Set<Slot> slotSet) {
        this.slotSet = slotSet;
    }
}
