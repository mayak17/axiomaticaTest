package com.example.axiomaticatest.Service;

import com.example.axiomaticatest.DAO.ClientDAO;
import com.example.axiomaticatest.Entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public Client searchClient(String phone,String fullname,String passport){
        return this.clientDAO.getClientByPhoneOrFullnameOrPassport(phone,fullname,passport);
    }

    public List<Client> findAllClient(){
        return this.clientDAO.getAllClients();
    }

}
