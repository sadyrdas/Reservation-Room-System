package cz.cvut.kbss.ear.mroom.dao;

import cz.cvut.kbss.ear.mroom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDao extends BaseDao<User> {

    @Autowired
    public UserDao() {
        super(User.class);
    }

    public User findByEmail(String email) {
        try {
            return em.createNamedQuery("User.findByEmail", User.class).setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public Boolean createNewUser(User user) {
        try {
            em.persist(user);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Transactional
    public Boolean deleteUserByEmail(String email) {
        try {
            int rowUpdated =  em.createNamedQuery("User.deleteByEmail", User.class)
                    .setParameter("email", email).executeUpdate();

            return rowUpdated != 0;
        } catch (NoResultException e) {
            return null;
        }

    }

    @Transactional
    public void updateUserByEmail(String oldEmail, String newEmail) {
        User user = findByEmail(oldEmail);
        user.setEmail(newEmail);
    }

    public List<User> getAllUsers() {
        return findAll();
    }
}
