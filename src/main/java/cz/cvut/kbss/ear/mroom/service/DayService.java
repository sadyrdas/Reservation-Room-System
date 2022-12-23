package cz.cvut.kbss.ear.mroom.service;

import cz.cvut.kbss.ear.mroom.dao.DayDao;
import cz.cvut.kbss.ear.mroom.model.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DayService {
    private final DayDao dayDao;

    @Autowired
    public DayService(DayDao dayDao) {
        this.dayDao = dayDao;
    }

    @Transactional
    public Day findDayById(Integer id) {
        return dayDao.find(id);
    }
}
