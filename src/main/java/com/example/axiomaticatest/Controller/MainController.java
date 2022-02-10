package com.example.axiomaticatest.Controller;

import com.example.axiomaticatest.DTO.ApplicationDTO;
import com.example.axiomaticatest.DTO.LoanContractDTO;
import com.example.axiomaticatest.Entity.Application;
import com.example.axiomaticatest.Entity.Client;
import com.example.axiomaticatest.Entity.LoanContract;
import com.example.axiomaticatest.Service.ApplicationService;
import com.example.axiomaticatest.Service.ClientService;
import com.example.axiomaticatest.Service.LoanContractService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MainController {
    final private ClientService clientService;
    final private ApplicationService applicationService;
    private final LoanContractService loanContractService;

    public MainController(ClientService clientService, ApplicationService applicationService, LoanContractService loanContractService) {
        this.clientService = clientService;
        this.applicationService = applicationService;
        this.loanContractService = loanContractService;
    }

    @GetMapping("/client-search")
    public ResponseEntity<Client> searchClient(@RequestParam(required = false) String phone,
                                                   @RequestParam(required = false) String fullname,
                                                   @RequestParam(required = false) String passport){
        return ResponseEntity.ok(this.clientService.searchClient(phone,fullname,passport));
    }

    @GetMapping("/all-clients")
    public ResponseEntity<List<Client>> allClient(){
        return ResponseEntity.ok(this.clientService.findAllClient());
    }

    @GetMapping("/all-loan-contracts")
    public ResponseEntity<List<LoanContract>> allLoanContract(){
        return ResponseEntity.ok(this.loanContractService.getAllLoanContracts());
    }
    @PostMapping("/add-application")
    public ResponseEntity<Application> addApplication(@RequestBody ApplicationDTO applicationDTO){
        try {
            Application application = this.applicationService.consideApplication(applicationDTO);
            return new ResponseEntity(application, HttpStatus.CREATED);
        }
        catch(HibernateException e){
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }
    @PostMapping("/create-loan-contract")
    public ResponseEntity<LoanContractDTO> createLoanContract(@RequestParam Long applicationId){
        try {
            LoanContractDTO loanContract = this.loanContractService.createLoanContractDTO(applicationId);
            return new ResponseEntity(loanContract, HttpStatus.CREATED);
        }
        catch(HibernateException e){
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }
    @PostMapping("/update-loan-contract")
    public ResponseEntity updateLoanContract(@RequestParam Long loanContractId,
                                               @RequestParam Long statusSign){
        try {
            this.loanContractService.updateLoanContract(loanContractId,statusSign);
            return ResponseEntity.status(202).build();
        }
        catch(HibernateException e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }
    @GetMapping("/all-applications")
    public ResponseEntity<List<Application>> getAllApplication(){
        return ResponseEntity.ok(this.applicationService.getAllApplications());
    }
}
