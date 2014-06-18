package fr.utbm.gl52.database;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.utbm.gl52.document.Address;
import fr.utbm.gl52.document.Client;
import fr.utbm.gl52.document.Document;
import fr.utbm.gl52.document.DocumentInfo;
import fr.utbm.gl52.document.DocumentType;
import fr.utbm.gl52.document.Supplier;

/**
 * @author mgrollea
 *
 */
public class HibernateTest {

/**
 * @param args
 */
public static void main(String[] args) {
	
        Session session = HibernateUtil.getSession();
 
        session.beginTransaction();

        File file = new File("ImageTest" + File.separator +"factureOCR_0001.jpg"); //$NON-NLS-1$ //$NON-NLS-2$
        
        System.out.println(file.length());
        
        byte[] imageData = new byte[(int) file.length()];
         
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(imageData);
            fileInputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        Address store = new Address("France", "Paris", "8 rue des petits pois", 95000); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        Address adsup = new Address("France", "Paris", "4 rue des petits pois", 95000); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        Address adcli = new Address("France", "Paris", "3 rue des petits pois", 95000); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        Client cli = new Client(adcli, "Dupond", "Jean"); //$NON-NLS-1$ //$NON-NLS-2$
        Supplier sup = new Supplier("Fifi", adsup, null); //$NON-NLS-1$
        DocumentInfo info = new DocumentInfo(cli, sup, store, new Date(System.currentTimeMillis()));
        DocumentType docType = DocumentType.BILL;
        Document doc = new Document(docType, file , "factureOCR_0001.jpg", info); //$NON-NLS-1$ 
        
        session.saveOrUpdate(doc);
        
        session.getTransaction().commit();


        doc.getAllDocuments(DocumentType.BILL);
        
        System.out.println(doc.getDocument(1));
        
        doc.deleteDocument(1);
        
        HibernateUtil.shutdown();

    }
   
}
