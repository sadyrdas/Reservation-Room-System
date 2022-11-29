package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="roles")
public class UserRole extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private String name;


    public UserRole(){}

    public UserRole(String name) {this.name = name;}
}
