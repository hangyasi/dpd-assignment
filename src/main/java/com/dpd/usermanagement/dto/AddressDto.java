package com.dpd.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Integer id;
    private String postalCode;
    private String country;
    private String city;
    private String street;
    private String number;
}
