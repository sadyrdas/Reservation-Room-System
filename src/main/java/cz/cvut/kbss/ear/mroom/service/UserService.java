package cz.cvut.kbss.ear.mroom.service;

import cz.cvut.kbss.ear.mroom.dao.SlotDao;
import cz.cvut.kbss.ear.mroom.dao.UserDao;
import cz.cvut.kbss.ear.mroom.exception.NotEnoughMoney;
import cz.cvut.kbss.ear.mroom.model.Slot;
import cz.cvut.kbss.ear.mroom.model.User;
import cz.cvut.kbss.ear.mroom.model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Service
public class UserService {
    private final UserDao userDao;
    private final SlotService slotService;
    private final SlotDao slotDao;
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, SlotService slotService, SlotDao slotDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.slotService = slotService;
        this.slotDao = slotDao;
        this.passwordEncoder = passwordEncoder;
    }



    @Transactional
    public Boolean createUser(String email, String first_name, String last_name, String password, List<UserRole> roles) {
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
            User user = new User(email, first_name, last_name, password,  roles);
            user.encodePassword(passwordEncoder);
            userDao.persist(user);
            ret = true;
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
    public Boolean updateUserEmailByEmail(String oldEmail, String newEmail) {
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

    @Transactional
    public void putUpMoney(User user, Double money) {
        userDao.putUpMoney(user, money);
    }

    @Transactional
    public void withdrawMoney(User user, Double money) {
        userDao.withdrawMoney(user, money);
    }

    @Transactional
    public void payForSlot(User user, List<Slot> slots) {
        double totalPrice = slots.size() * 125;


        // If User is Student sale is applied
        if (user.getRoles().get(0).getId() == 1) {
            totalPrice *= 0.5;
            LOG.info("Discount applied for user: " + user.getLast_name());
        }

        if (user.getMoney() >= totalPrice) {
            for (Slot slot : slots) {
                slotService.changePaidStatus(slot, true);
                slotService.setSlotOwner(slot, user);
            }
            userDao.withdrawMoney(user, totalPrice);
            LOG.info("Slot ('s) was/were successfully paid for " + totalPrice);
            LOG.info("User current money: " + user.getMoney());
        } else {
            throw new NotEnoughMoney("User: " + user.getLast_name() + " doesn't have enough money. Current: " + user.getMoney()
                    + ". " + totalPrice);
        }
    }

    @Transactional
    public User findUserByEmail(String userEmail) {
        return userDao.findByEmail(userEmail);
    }

    @Transactional
    public void cancelReservation(List<Slot> slots) {
        for (Slot slot : slots) {
            slotService.changePaidStatus(slot, false);
            slotService.setSlotOwner(slot, null);
        }
    }
}
