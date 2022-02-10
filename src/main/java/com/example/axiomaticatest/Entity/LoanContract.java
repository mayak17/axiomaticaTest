package com.example.axiomaticatest.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "loan_contract")
@NoArgsConstructor
public class LoanContract {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "application_id")
  private long applicationId;
  @Column(name = "sign_date")
  private java.sql.Date signDate;
  private long status;

  public LoanContract(Long applicationId){
    this.applicationId = applicationId;
  }
}
