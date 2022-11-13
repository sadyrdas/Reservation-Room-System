package cz.cvut.kbss.ear.mroom;

import cz.cvut.kbss.ear.mroom.dao.PaymentDao;
import cz.cvut.kbss.ear.mroom.dao.UserDao;
import cz.cvut.kbss.ear.mroom.model.Payment;
import cz.cvut.kbss.ear.mroom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@RestController
public class MeetingRoomReservationApplication {

    private final UserDao userDao;
    private final PaymentDao paymentDAo;

    @Autowired
    public MeetingRoomReservationApplication(UserDao userDao, PaymentDao paymentDAo) {
        this.userDao = userDao;
        this.paymentDAo = paymentDAo;
    }

    public static void main(String[] args) {
        SpringApplication.run(MeetingRoomReservationApplication.class, args);
    }

    @GetMapping
    public List<User> hello() {
//        User user = userDao.findByUsername("user1@test.test");
        return userDao.getAllUsers();

//        return List.of(user.getEmail(), user.getFirstName());
//        return List.of("Test!");
    }

    @GetMapping("allPayments")
    public List<Payment> hello2(){
        return paymentDAo.getAllPayments();
    }

}
