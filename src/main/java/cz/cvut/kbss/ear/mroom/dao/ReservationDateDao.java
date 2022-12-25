package cz.cvut.kbss.ear.mroom.dao;

import cz.cvut.kbss.ear.mroom.model.ReservationDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.time.LocalDate;

@Repository
public class ReservationDateDao extends BaseDao<ReservationDate> {

    @Autowired
    protected ReservationDateDao() {
        super(ReservationDate.class);
    }

    @Transactional
    public ReservationDate findByDate(LocalDate date) {
        try {
            return em.createNamedQuery("Day.findByDate", ReservationDate.class).setParameter("date", date).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    @Transactional
    public void deleteDayByDate(LocalDate date){
        remove(findByDate(date));
    }

    @Transactional
    public void updateDateOfDayById(int id, LocalDate newDate ) {
        ReservationDate day = find(id);
        day.setPosting_date(newDate);
    }

}
