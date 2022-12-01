package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;


@Entity
@Table(name = "day")
@NamedQueries({
        @NamedQuery(name = "Day.findByDate", query = "SELECT d FROM Day d WHERE d.posting_date = :date")
})
public class Day extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private LocalDate posting_date;

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
}
