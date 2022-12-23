package cz.cvut.kbss.ear.mroom.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="roles")
@NamedQueries({
        @NamedQuery(name = "UserRole.findByRoleName", query = "SELECT ur FROM UserRole ur WHERE ur.name = :name"),
        @NamedQuery(name = "UserRole.getById", query = "select ur from UserRole  ur where ur.id = :id")
}
)
public class UserRole extends AbstractEntity {
    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    private Set<User> users;


    public UserRole(){}

    public UserRole(String name) {this.name = name;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
