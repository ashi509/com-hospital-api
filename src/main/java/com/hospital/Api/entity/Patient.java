package com.hospital.Api.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
    @Size(min=3,message = "Patient name must be have minimum 3 digit")
    private String city;
    @Email(message = "Email is not valid")
    private String email;
    @NotEmpty
    @Size(min =10 ,message = "Patient contact number must be have 10 digit")
    private String phoneNumber;
    @NotEmpty
    private String symptom;
}
