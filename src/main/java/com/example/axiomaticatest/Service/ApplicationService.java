package com.example.axiomaticatest.Service;

import com.example.axiomaticatest.DAO.ApplicationDAO;
import com.example.axiomaticatest.DAO.ClientDAO;
import com.example.axiomaticatest.DTO.ApplicationDTO;
import com.example.axiomaticatest.Entity.Application;
import com.example.axiomaticatest.Entity.Client;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ApplicationService {
    private final ApplicationDTO applicationDTO;
    private final ClientDAO clientDAO;
    private final ApplicationDAO applicationDAO;

    public ApplicationService(ApplicationDTO applicationDTO, ClientDAO clientDAO, ApplicationDAO applicationDAO) {
        this.applicationDTO = applicationDTO;
        this.clientDAO = clientDAO;
        this.applicationDAO = applicationDAO;
    }

    public List<Application> getAllApplications(){
        return this.applicationDAO.getAllApplication();
    }
    public Application consideApplication(ApplicationDTO applicationDTO){
        Long clientId = this.saveClient(applicationDTO);
        return createApplication(applicationDTO,clientId);
    }

    public Long saveClient(ApplicationDTO applicationDTO){
        Client client = new Client(applicationDTO);
        Long clientId = this.clientDAO.saveClient(client);

        return clientId;
    }

    public Application createApplication(ApplicationDTO applicationDTO, Long clientId){
        Random rnd =new Random();
        Application application = new Application();

        application.setClientId(clientId);
        application.setDesirableSum(applicationDTO.getDesirableSum());

        if(rnd.nextBoolean()) {
            application.setStatusApplication(1);
            application.setPeriodMonth(rnd.nextInt(12)+1);
            application.setApprovedSum((long) (applicationDTO.getDesirableSum()*(rnd.nextDouble()+0.5)));
        }
        else {
            application.setStatusApplication(0);
            application.setPeriodMonth(0);
            application.setApprovedSum(0);
        }
            this.applicationDAO.saveApplication(application);
            return application;

    }
}
