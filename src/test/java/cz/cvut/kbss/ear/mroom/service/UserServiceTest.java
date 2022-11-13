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


import static org.junit.jupiter.api.Assertions.assertEquals;

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
        System.out.println(user.getEmail());
        Boolean result = userService.createUser(user.getEmail(), user.getFirst_name(), user.getLast_name(),
                user.getPassword());

        assertEquals(true, result);
//        System.out.println(user.getEmail());
        assertEquals(user.getEmail(), userDao.findByEmail(user.getEmail()).getEmail());
    }
}
