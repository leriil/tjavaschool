package com.tsystems.tshop.domain;

import com.tsystems.tshop.domain.util.LocalDateConverter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="user")
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

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_address",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> addresses = new HashSet<>();


    @Column(name = "login",nullable = false, unique = true, updatable = false)
    private String login;


    @Column(name = "password", unique = true,nullable = false)
    private String password;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "surname",nullable = false)
    private String surname;

    @Column(name = "birth_date")
    @Convert(converter = LocalDateConverter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

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
//            System.out.println(privileges);
        }
        final List<GrantedAuthority> authorities = privileges.stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
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

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", roles=" + roles +
                ", addresses=" + addresses +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                '}';
    }
}
