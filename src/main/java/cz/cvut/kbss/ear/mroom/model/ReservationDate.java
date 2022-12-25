package cz.cvut.kbss.ear.mroom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "reservationdate")
@NamedQueries({
        @NamedQuery(name = "Day.findByDate", query = "SELECT d FROM ReservationDate d WHERE d.posting_date = :date")
})
public class ReservationDate extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private LocalDate posting_date;


    @OneToMany(mappedBy = "reservationDay")
    @JsonBackReference
    private Set<Slot> slotSet;


    public ReservationDate(LocalDate posting_date) {
        this.posting_date = posting_date;
    }

    public ReservationDate() {
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
