package com.hospital.Api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty
    @Size(min=3,message="Doctor name must be have minimum 3 character")
    private String name;
    @NotEmpty
    @Size(min=3 ,message = "Doctor city must be have 3 digit")
    private String city;
    @NotEmpty
    @Email(message = "Email is not valid")
    private String email;
    @NotEmpty
    @Size(min= 10 ,message ="Phone number must be minimum 10 digit")
    private String phoneNumber;
    @NotEmpty
    private String speciality;
}
