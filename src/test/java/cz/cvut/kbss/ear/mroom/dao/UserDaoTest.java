package cz.cvut.kbss.ear.mroom.dao;

import cz.cvut.kbss.ear.mroom.Environment.Generator;
import cz.cvut.kbss.ear.mroom.MeetingRoomReservationApplication;
import cz.cvut.kbss.ear.mroom.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ComponentScan(basePackageClasses = MeetingRoomReservationApplication.class)
@RunWith(SpringRunner.class)
public class UserDaoTest {
    
    @Autowired
    private TestEntityManager em;

    @Autowired
    private UserDao userDao;

    @Test
    public void findUserByEmailTest() {
        final User user = Generator.generateUser();
        em.persist(user);

        final User result = userDao.findByEmail(user.getEmail());
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
    }

    @Test
    public void findNotExistentUserTest() {
        final User user = Generator.generateUser();
//        em.persist(user);

        final User result = userDao.findByEmail(user.getEmail());
        assertNull(result);
    }

    @Test
    public void findAllUsersTest() {
        final List<User> users = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            final User user = Generator.generateUser();
            users.add(user);
            em.persist(user);
        }

        assertEquals(users.size(), userDao.getAllUsers().size());
    }



    @Test
    public void deleteUserByEmailTest() {
        final User u = Generator.generateUser();
        em.persist(u);
        userDao.deleteUserByEmail(userDao.findByEmail(u.getEmail()).getEmail());
        assertNull(userDao.findByEmail(u.getEmail()));
    }

    @Test
    public void updateUserByEmailTest() {
        final User u = Generator.generateUser();
        em.persist(u);
        String newEmail = Generator.generateUserEmail();
        userDao.updateUserByEmail(u.getEmail(), newEmail);
        assertEquals(u.getEmail(), newEmail);
    }

    @Test
    public void putUpMoney() {
        final User u = Generator.generateUser();
        em.persist(u);
        userDao.putUpMoney(u, 1000.0);
        assertEquals(1000.0, userDao.findByEmail(u.getEmail()).getMoney());
    }

    @Test
    public void withDrawMoneyTest() {
        final User u = Generator.generateUser();
        em.persist(u);
        userDao.putUpMoney(u, 1000.0);
        userDao.withdrawMoney(u, 333.0);
        assertEquals(667.0, userDao.findByEmail(u.getEmail()).getMoney());
    }

}
