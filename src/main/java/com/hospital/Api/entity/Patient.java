package com.hospital.Api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty
    @Size(min=3,message = "Patient name must be have minimum 3 digit")
    private String name;

    @NotEmpty
    @Size(min=2,message = "Patient name must be have minimum 3 digit")
    private String city;
    @Email(message = "Email is not valid")
    private String email;
    @NotEmpty
    @Size(min =10 ,message = "Patient contact number must be have 10 digit")
    private String phoneNumber;
    @NotEmpty
    private String symptom;
}
