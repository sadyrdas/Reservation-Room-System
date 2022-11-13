package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name ="roles")
public class UserRole extends AbstractEntity {

    @Basic(optional = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public UserRole(){}

    public UserRole(String name) {this.name = name;}
}
