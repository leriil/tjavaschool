package com.tsystems.tshop.domain;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

//    @OneToMany(mappedBy = "address")
//    private Set<User> users = new HashSet<>();

    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Invalid characters")
    @Column(name = "country")
    private String country;

    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Invalid characters")
    @Column(name = "city")
    private String city;

    @Pattern(regexp = "^[A-Z0-9]+$", message = "Invalid characters")
    @Column(name = "zip_code")
    private String zipCode;

    @Pattern(regexp = "^[A-Z0-9][a-z0-9]+$", message = "Invalid characters")
    @Column(name = "street")
    private String street;

    @Pattern(regexp = "^[A-Z0-9]+$", message = "Invalid characters")
    @Column(name = "house")
    private String house;

    @Pattern(regexp = "^[A-Z0-9]+$", message = "Invalid characters")
    @Column(name = "flat")
    private String flat;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String postalCode) {
        this.zipCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country) &&
                Objects.equals(city, address.city) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(street, address.street) &&
                Objects.equals(house, address.house) &&
                Objects.equals(flat, address.flat);
    }

    @Override
    public int hashCode() {

        return Objects.hash(country, city, zipCode, street, house, flat);
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", flat='" + flat + '\'' +
                '}';
    }
}

