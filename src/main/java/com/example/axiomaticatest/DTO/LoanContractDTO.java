package com.example.axiomaticatest.DTO;

import com.example.axiomaticatest.Entity.Application;
import com.example.axiomaticatest.Entity.Client;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LoanContractDTO {
    private long periodMonth;
    private long desirableSum;
    private long approvedSum;
    private long statusApplication;
    private String fullname;
    private String passport;
    private String address;
    private String phone;
    private String employmentInfo;
    private java.sql.Date signDate;
    private long statusContract;

}
