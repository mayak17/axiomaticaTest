package com.example.axiomaticatest.Entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="application")
public class Application {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name="period_month")
  private long periodMonth;
  @Column(name = "desirable_sum")
  private long desirableSum;
  @Column(name = "approved_sum")
  private long approvedSum;
  @Column(name = "client_id")
  private long clientId;
  @Column(name = "status_application")
  private long statusApplication;


}
