package com.example.axiomaticatest.Configuration;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
public class HibernateConfig {

    private static SessionFactory hibernateFactory;
    private Session mysqlSession;


    @Autowired
    public HibernateConfig(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        if (hibernateFactory == null) {
            hibernateFactory = factory.unwrap(SessionFactory.class);
            this.mysqlSession = hibernateFactory.openSession();
        }
    }

    private SessionFactory getHibernateFactory() {
        return hibernateFactory;
    }

    public Session getMysqlSession() {

        try {
            this.mysqlSession = this.getHibernateFactory().getCurrentSession();
        } catch (HibernateException e) {
            this.mysqlSession = this.getHibernateFactory().openSession();
        }

        return mysqlSession;
    }
}
