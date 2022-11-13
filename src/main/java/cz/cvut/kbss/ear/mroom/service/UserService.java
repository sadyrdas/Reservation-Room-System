package cz.cvut.kbss.ear.mroom.service;

import cz.cvut.kbss.ear.mroom.dao.UserDao;
import cz.cvut.kbss.ear.mroom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@Service
public class UserService {
    private final UserDao userDao;

//    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
//        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Boolean createUser(String email, String first_name, String last_name, String password) {
        Objects.requireNonNull(email);
        Objects.requireNonNull(first_name);
        Objects.requireNonNull(last_name);
        Objects.requireNonNull(password);

        Boolean ret = false;

        if (email.isEmpty() || first_name.isEmpty() || last_name.isEmpty() || password.isEmpty()) {
            return ret;
        } else if (userDao.findByEmail(email) != null) {
            return ret;
        } else {
            // TODO CHECK LENGTH OF INPUTs
            userDao.createNewUser(new User(email, first_name, last_name, password));
        }

        return ret;
    }

    @Transactional
    public Boolean deleteUserByEmail(String email) {
        Objects.requireNonNull(email);

        Boolean result = false;
        if(email.isEmpty()) {
            return result;
        }else if (userDao.findByEmail(email) == null){
            return result;
        }else {
            userDao.deleteUserByEmail(email);
        }
        return true;
    }

    @Transactional
    public Boolean updateUserByEmail(String oldEmail, String newEmail) {
        Objects.requireNonNull(oldEmail);
        Boolean result = false;
        if(oldEmail.isEmpty()) {
            return result;
        }else if (userDao.findByEmail(oldEmail) == null){
            return result;
        }else {
            userDao.updateUserByEmail(oldEmail,newEmail);
        }
        return true;
    }


}
