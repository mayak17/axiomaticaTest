package com.example.axiomaticatest.Entity;

import com.example.axiomaticatest.DTO.ApplicationDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="client")
@Data
@Entity
@NoArgsConstructor
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String fullname;
  private String passport;
  private String address;
  private String phone;
  @Column(name = "employment_info")
  private String employmentInfo;

  public Client(ApplicationDTO applicationDTO){
    this.setPhone(applicationDTO.getPhone());
    this.setAddress(applicationDTO.getAddress());
    this.setPassport(applicationDTO.getPassport());
    this.setEmploymentInfo(applicationDTO.getEmploymentInfo());
    this.setFullname(applicationDTO.getFullname());
  }
}
