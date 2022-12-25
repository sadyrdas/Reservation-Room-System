package cz.cvut.kbss.ear.mroom.service;

import cz.cvut.kbss.ear.mroom.dao.ReservationDateDao;
import cz.cvut.kbss.ear.mroom.model.ReservationDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ReservationDateService {
    private final ReservationDateDao reservationDateDao;

    @Autowired
    public ReservationDateService(ReservationDateDao reservationDateDao) {
        this.reservationDateDao = reservationDateDao;
    }

    @Transactional
    public ReservationDate findDayById(Integer id) {
        return reservationDateDao.find(id);
    }

    @Transactional
    public ReservationDate findDayByDate(LocalDate localDate) {
        return reservationDateDao.findByDate(localDate);
    }

    @Transactional
    public void createDay(ReservationDate day) {
        reservationDateDao.persist(day);
    }

    @Transactional
    public void deleteDay(LocalDate date){
        reservationDateDao.deleteDayByDate(date);
    }
}
