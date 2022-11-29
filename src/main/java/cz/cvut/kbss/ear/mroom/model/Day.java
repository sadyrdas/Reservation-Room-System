package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table
public class Day extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private Date posting_date;

    public Day(Date posting_date) {
        this.posting_date = posting_date;
    }

    public Day() {
    }


    public Date getPosting_date() {
        return posting_date;
    }

    public void setPosting_date(Date posting_date) {
        this.posting_date = posting_date;
    }
}
