package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="roles")
@NamedQueries({
        @NamedQuery(name = "UserRole.findByRoleName", query = "SELECT ur FROM UserRole ur WHERE ur.name = :name")
}
)
public class UserRole extends AbstractEntity {
    @Basic(optional = false)
    @Column(nullable = false)
    private String name;


    public UserRole(){}

    public UserRole(String name) {this.name = name;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
