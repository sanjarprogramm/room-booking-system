package uz.qbg.appbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "ism bush bulmasin")
    private String firstName;

    @NotBlank(message = "familya bush bulmasin")
    private String lastName;

    @NotBlank(message = "addres bulishi shart")
    private String address;

    @Column(unique = true)
    private String phoneNumber;


}
