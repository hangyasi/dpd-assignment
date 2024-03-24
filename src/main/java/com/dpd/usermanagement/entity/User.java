package com.dpd.usermanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private LocalDate birthdate;

    private String birthPlace;

    private String mothersName;

    private String socialSecurityNumber;

    private String taxIdentificationNumber;

    private String emailAddress;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PhoneNumber> phoneNumbers;

    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        phoneNumbers.add(phoneNumber);
        phoneNumber.setUser(this);
    }

    public void removePhoneNumber(PhoneNumber phoneNumber) {
        phoneNumbers.remove(phoneNumber);
        phoneNumber.setUser(null);
    }
}
