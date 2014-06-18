package fr.utbm.gl52.database;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author mgrollea
 *
 */
public class HibernateTest {

/**
 * @param args
 */
public static void main(String[] args) {
	
        Session session = HibernateUtil.getSessionFactory().openSession();
 
        session.beginTransaction();

        Bill bill = new Bill(1,2,3);
        session.save(bill);

        session.save(bill);
     
        session.getTransaction().commit();

        Query q = session.createQuery("From Bill"); //$NON-NLS-1$
                
        @SuppressWarnings({"unchecked" })
		List<Bill> resultList = q.list();
        System.out.println("num of bill:" + resultList.size()); //$NON-NLS-1$
        for (Bill next : resultList) {
            System.out.println("next employee: " + next); //$NON-NLS-1$
        }
        
        HibernateUtil.shutdown();

    }
   
}