package com.hospital.Api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
