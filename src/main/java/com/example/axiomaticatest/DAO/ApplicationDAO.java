package com.example.axiomaticatest.DAO;

import com.example.axiomaticatest.Configuration.HibernateConfig;
import com.example.axiomaticatest.Entity.Application;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationDAO {
    private final HibernateConfig hibernateConfig;

    public ApplicationDAO(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    public void saveApplication(Application application){

        Session session = this.hibernateConfig.getMysqlSession();
        Transaction transaction = session.beginTransaction();
        session.save(application);
        transaction.commit();
        session.close();
    }

    public List<Application> getAllApplication(){
        return this.hibernateConfig.getMysqlSession().createQuery("from Application").list();
    }

    public Application getApplicationById(Long applicationId){
        return (Application) this.hibernateConfig.getMysqlSession()
                .createQuery("from Application where id=: param1")
                .setParameter("param1",applicationId)
                .getSingleResult();
    }

}
