package com.example.axiomaticatest.DAO;

import com.example.axiomaticatest.Configuration.HibernateConfig;
import com.example.axiomaticatest.Entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientDAO {
    private final HibernateConfig hibernateConfig;

    public ClientDAO(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    public Long saveClient(Client client){
        Session session = this.hibernateConfig.getMysqlSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(client);
        transaction.commit();
        session.close();
        return id;
    }

    public List<Client> getAllClients(){
        return this.hibernateConfig.getMysqlSession().createQuery("from Client").list();
    }

    public Client getClientByPhoneOrFullnameOrPassport(String phone, String fullname, String passport){
        return this.hibernateConfig.getMysqlSession()
                .createQuery("select c from Client c where c.phone=:param1 and c.fullname=:param2 and c.passport=:param3",Client.class)
                .setParameter("param1",phone)
                .setParameter("param2",fullname)
                .setParameter("param3",passport)
                .getSingleResult();
    }


    public Client getClientById(Long clientId){
        return this.hibernateConfig.getMysqlSession()
                .createQuery("select c from Client c where c.id=:param1",Client.class)
                .setParameter("param1",clientId)
                .getSingleResult();
    }
}
