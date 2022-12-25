package cz.cvut.kbss.ear.mroom.service;

import cz.cvut.kbss.ear.mroom.Environment.Generator;
import cz.cvut.kbss.ear.mroom.MeetingRoomReservationApplication;
import cz.cvut.kbss.ear.mroom.dao.UserDao;
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

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ComponentScan(basePackageClasses = MeetingRoomReservationApplication.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Test
    public void createUserTest() {
        final User user = Generator.generateUser();
        Boolean result = userService.createUser(user.getEmail(), user.getFirst_name(), user.getLast_name(),
                user.getPassword(), user.getRoles());

        assertEquals(true, result);
        assertEquals(user.getEmail(), userDao.findByEmail(user.getEmail()).getEmail());
    }

    @Test
    public void createAlreadyExistentUserTest() {
        final User user = Generator.generateUser();
        Boolean result = userService.createUser(user.getEmail(), user.getFirst_name(), user.getLast_name(),
                user.getPassword(), user.getRoles() );

        assertEquals(true, result);
        // Service return false in case of already exsistent user
        assertFalse(userService.createUser(user.getEmail(), user.getFirst_name(), user.getLast_name(),
                user.getPassword(), user.getRoles()));
    }

    @Test
    public void deleteUserTest() {
        final List<User> users = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            final User user = Generator.generateUser();
            users.add(user);
            em.persist(user);
        }

        final User user = Generator.generateUser();
        users.add(user);
        em.persist(user);

        assertEquals(users.size(), userDao.getAllUsers().size());
        assertEquals(userDao.findByEmail(user.getEmail()).getEmail(), user.getEmail());
        userService.deleteUserByEmail(user.getEmail());
        assertEquals(users.size() - 1, userDao.getAllUsers().size());
        assertNull( userDao.findByEmail(user.getEmail()));
    }

    @Test
    public void tryDeleteNotExistentUserTest() {
        final List<User> users = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            final User user = Generator.generateUser();
            users.add(user);
            em.persist(user);
        }

        assertEquals(users.size(), userDao.getAllUsers().size());
        assertFalse(userService.deleteUserByEmail("notExistent@no.no"));
        assertEquals(users.size(), userDao.getAllUsers().size());
    }

    @Test
    public void updateUserTest() {
        final User user = Generator.generateUser();
        em.persist(user);

        assertEquals(userDao.findByEmail(user.getEmail()).getEmail(), user.getEmail());
        userService.updateUserEmailByEmail(user.getEmail(), "updated@email.com");
        assertEquals("updated@email.com", user.getEmail());
    }
}
