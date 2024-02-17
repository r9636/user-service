package com.user.service.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "address_table")
public class Address {
    @Id
    private String addressId;
    private String fullAddress;
    private int zipCode;
    private String city;
    @ManyToOne
    @JoinColumn(name = "userId") //name in Address table will be the name given
    private User user;
}
