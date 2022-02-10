package com.example.axiomaticatest.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ApplicationDTO {
    private String fullname;
    private String passport;
    private String address;
    private String phone;
    private String employmentInfo;
    private long desirableSum;
}
