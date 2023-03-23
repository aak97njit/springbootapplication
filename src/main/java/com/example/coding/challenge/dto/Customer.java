package com.example.coding.challenge.dto;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@ToString
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, updatable = false)
    String id;

    @Column(name = "firstname", length = 100)
    String firstName;

    @Column(name = "lastname", length = 100)
    String lastName;

    @Column(name = "cellNumber", length = 10)
    String cellNumber;

    @Column(name = "emailId", length = 320)
    String emailId;
}
