package com.example.axiomaticatest.Service;

import com.example.axiomaticatest.DAO.ApplicationDAO;
import com.example.axiomaticatest.DAO.ClientDAO;
import com.example.axiomaticatest.DAO.LoanContractDAO;
import com.example.axiomaticatest.DTO.LoanContractDTO;
import com.example.axiomaticatest.Entity.Application;
import com.example.axiomaticatest.Entity.Client;
import com.example.axiomaticatest.Entity.LoanContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanContractService {
    private final LoanContractDAO loanContractDAO;
    private final ClientDAO clientDAO;
    private final ApplicationDAO applicationDAO;

    public LoanContractService(LoanContractDAO loanContractDAO, ClientDAO clientDAO, ApplicationDAO applicationDAO) {
        this.loanContractDAO = loanContractDAO;
        this.clientDAO = clientDAO;
        this.applicationDAO = applicationDAO;
    }

    public Long createLoanContract(Long applicationId){
        return this.loanContractDAO.saveLoanContract(new LoanContract(applicationId));
    }

    public LoanContractDTO createLoanContractDTO(Long applicationId){
        LoanContract loanContract = this.loanContractDAO.getLoanContractById(this.createLoanContract(applicationId));
        Application application = this.applicationDAO.getApplicationById(loanContract.getApplicationId());
        Client client = this.clientDAO.getClientById(application.getClientId());
        LoanContractDTO loanContractDTO = new LoanContractDTO();
        loanContractDTO.setPeriodMonth(application.getPeriodMonth());
        loanContractDTO.setDesirableSum(application.getDesirableSum());
        loanContractDTO.setApprovedSum(application.getApprovedSum());
        loanContractDTO.setStatusApplication(application.getStatusApplication());
        loanContractDTO.setFullname(client.getFullname());
        loanContractDTO.setPassport(client.getPassport());
        loanContractDTO.setAddress(client.getAddress());
        loanContractDTO.setPhone(client.getPhone());
        loanContractDTO.setEmploymentInfo(client.getEmploymentInfo());
        loanContractDTO.setSignDate(loanContract.getSignDate());
        loanContractDTO.setStatusContract(loanContract.getStatus());
        return loanContractDTO;
    }

    public void updateLoanContract(Long loanContractId,Long statusSign){
        this.loanContractDAO.updateLoanContract(loanContractId,statusSign);
    }

    public List<LoanContract> getAllLoanContracts(){
        return this.loanContractDAO.getAllLoanContract();
    }
}
