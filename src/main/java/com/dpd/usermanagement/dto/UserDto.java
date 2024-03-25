package com.dpd.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;
    private String name;
    private LocalDate birthdate;
    private String birthPlace;
    private String mothersName;
    private String socialSecurityNumber;
    private String taxIdentificationNumber;
    private String emailAddress;
    private List<AddressDto> addresses;
    private List<PhoneNumberDto> phoneNumbers;
}
