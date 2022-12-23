package cz.cvut.kbss.ear.mroom.dao;

import cz.cvut.kbss.ear.mroom.model.UserRole;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserRoleDao extends BaseDao<UserRole>{

    protected UserRoleDao() {
        super(UserRole.class);
    }

    @Transactional
    public UserRole getRoleIdByRoleName(String name) {
        try {
            return em.createNamedQuery("UserRole.findByRoleName", UserRole.class).setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public List<UserRole> getAllRoles(int id) {
        try {
            return em.createNamedQuery("UserRole.getById", UserRole.class).setParameter("id", id)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }


    }
}
