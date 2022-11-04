package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.Entity;

@Entity
public class Admin extends User {
    public Admin() {
    }

    public Admin(String email, String firstName, String lastName, String password) {
        super(email, firstName, lastName, password);
    }
}
