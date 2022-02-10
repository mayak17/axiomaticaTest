package com.example.axiomaticatest.DAO;

import com.example.axiomaticatest.Configuration.HibernateConfig;
import com.example.axiomaticatest.DTO.LoanContractDTO;
import com.example.axiomaticatest.Entity.LoanContract;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
@Transactional
public class LoanContractDAO {
    final private HibernateConfig hibernateConfig;

    public LoanContractDAO(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    public Long saveLoanContract(LoanContract loanContract){
        Session session = this.hibernateConfig.getMysqlSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(loanContract);
        transaction.commit();
        session.close();
        return id;
    }

    public void updateLoanContract(Long loanContractId, Long statusSign){
        Session session = this.hibernateConfig.getMysqlSession();
        Transaction tx = session.beginTransaction();
        Query updateQuery
                = session.createQuery("update LoanContract l set l.signDate=:param1 , l.status=:param2 where l.id=:param3");
        updateQuery.setParameter("param1",new Date());
        updateQuery.setParameter("param2",statusSign);
        updateQuery.setParameter("param3",loanContractId);
        updateQuery.executeUpdate();
        tx.commit();
        session.close();
    }

    public LoanContract getLoanContractById(Long id){
       return (LoanContract) this.hibernateConfig.getMysqlSession()
                .createQuery("from LoanContract where id =: param1")
                .setParameter("param1",id)
                .getSingleResult();
    }

    public List<LoanContract> getAllLoanContract(){
        return this.hibernateConfig.getMysqlSession().createQuery("from LoanContract ").list();
    }
}
