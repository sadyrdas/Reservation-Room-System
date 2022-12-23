package cz.cvut.kbss.ear.mroom.model;


import cz.cvut.kbss.ear.mroom.exception.NotEnoughMoney;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
        @NamedQuery(name = "User.deleteByEmail", query = "DELETE FROM User u where u.email = :email"),
        @NamedQuery(name = "User.updateByEmail", query = "update User set email = email where User.email = :email")
})
public class User extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private String email;

    @Basic(optional = false)
    @Column(nullable = false)
    private String first_name;

    @Basic(optional = false)
    @Column(nullable = false)
    private String last_name;

    @Basic(optional = false)
    @Column(nullable = false)
    private String password;

    @Basic(optional = false)
    @Column(nullable = false)
    private double money;





    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )

    private List<UserRole> roles;


    public User() {
    }

    public User(String email, String first_name, String last_name, String password, List<UserRole> roles) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.money = 0.0;
        this.roles = roles;
    }

    public User(String email, String first_name, String last_name, String password, UserRole userRole) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.money = 0.0;
    }



    public void withdrawMoney(Double drawMoney) {
        if (this.money - drawMoney < 0) {
            throw new NotEnoughMoney("Trying to withdraw amount of money that larger than user's current money.");
        } else {
            this.money -= drawMoney;
        }
    }

    public void putUpMoney(Double drawMoney) {
        this.money += drawMoney;
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
    public boolean isAdmin(){
        return roles.stream().anyMatch(r -> r.getName().equals("ROLE_ADMIN"));
    }

    public boolean isStudent(){
        return roles.stream().anyMatch(r -> r.getName().equals("ROLE_STUDENT"));

    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

}
