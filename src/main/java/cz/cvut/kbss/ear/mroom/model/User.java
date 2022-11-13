package cz.cvut.kbss.ear.mroom.model;


import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
        @NamedQuery(name = "User.deleteByEmail", query = "DELETE FROM User u where u.email = :email"),
//        @NamedQuery(name = "User.updateByEmail", query = "update User set email = email where User.email = :email")
})
public class User extends AbstractEntity {

    @Basic
    @Column(nullable = false, unique = true)
    private String email;

    @Basic
    @Column(nullable = false)
    private String first_name;

    @Basic
    @Column(nullable = false)
    private String last_name;

    @Basic
    @Column(nullable = false)
    private String password;

    @Basic
    @Column(nullable = false)
    private double money;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<UserRole> roles;

//    @OneToMany(mappedBy = "id")
//    private Set<Payment> payment;


    public User() {
    }

    public User(String email, String first_name, String last_name, String password) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.money = 0.0;
    }

    public void erasePassword(){this.password=null;}

    public void encodePassword(PasswordEncoder encoder) {
        this.password = encoder.encode(password);
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword() {
        return password;
    }

    public double getMoney() {
        return money;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirst_name(String firstName) {
        this.first_name = firstName;
    }

    public void setLast_name(String lastName) {
        this.last_name = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
