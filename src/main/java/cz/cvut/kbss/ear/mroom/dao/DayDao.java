package cz.cvut.kbss.ear.mroom.dao;

import cz.cvut.kbss.ear.mroom.model.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.time.LocalDate;

@Repository
public class DayDao extends BaseDao<Day> {

    @Autowired
    protected DayDao() {
        super(Day.class);
    }

    @Transactional
    public Day findByDate(LocalDate date) {
        try {
            return em.createNamedQuery("Day.findByDate", Day.class).setParameter("date", date).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    @Transactional
    public void deleteDayByDate(LocalDate date){
        remove(findByDate(date));
    }

}
