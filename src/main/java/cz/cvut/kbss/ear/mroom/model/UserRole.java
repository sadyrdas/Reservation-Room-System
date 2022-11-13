package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="roles")
public class UserRole extends AbstractEntity {

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    @Column
    private int role_id;


    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public UserRole(){}

    public UserRole(String name) {this.name = name;}
}
