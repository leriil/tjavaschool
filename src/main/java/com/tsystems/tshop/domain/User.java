package com.tsystems.tshop.domain;

import com.tsystems.tshop.domain.util.LocalDateConverter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="user")
@SqlResultSetMapping(
        name="userTopMapping",
        classes = @ConstructorResult(
                targetClass = UserTop.class,
                columns = {
                        @ColumnResult(name = "userId",type = Long.class),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "surname"),
                        @ColumnResult(name = "email"),
                        @ColumnResult(name = "moneySpent", type = BigDecimal.class)
                }))
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
    })
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

   @ManyToOne
   @JoinColumn(name="address_id")
    private Address address = new Address();


    @Column(name = "login",nullable = false, unique = true, updatable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]{5,15}$", message = "Your username contains invalid characters or is too short.")
    private String login;


    @Column(name = "password", unique = true,nullable = false)
    private String password;

    @Column(name="confirm_password", nullable = false)
    private String confirmPassword;

    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Your name contains invalid characters.")
    @Column(name = "name",nullable = false)
    private String name;

    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Your surname contains invalid characters.")
    @Column(name = "surname",nullable = false)
    private String surname;

    @Past(message = "It has to be a past date.")
    @Column(name = "birth_date")
    @Convert(converter = LocalDateConverter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotBlank(message = "example: hello@yandex.ru")
    @Column(name = "email", nullable = false, unique = true,updatable = false)
    private String email;

    public User(){
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;

    }

    public User(String login, String password, String name, String surname, String email) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public User(String login, String password, List<GrantedAuthority> roles) {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles=this.getRoles();
        List<String>privileges=new ArrayList<>();
        for (Role role:roles) {
            privileges.add(role.getName());
        }
        return privileges.stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", roles=" + roles +
                ", address=" + address +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                '}';
    }
}
