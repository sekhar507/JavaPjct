package org.hibernate;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  
public class StoreData {  
public static void main(String[] args) {  
      
    //creating configuration object  
    Configuration cfg=new Configuration();  
    cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  
      
    //creating seession factory object  
    SessionFactory factory=cfg.buildSessionFactory();  
      
    //creating session object  
    Session session=factory.openSession();  
      
    //creating transaction object  
    Transaction t=session.beginTransaction();  
          
    Employee e1=new Employee();  
   // Inserting The Values into Table.
    e1.setId(507);  
    e1.setFirstName("hema");  
    e1.setLastName("sekhar");
    
    e1.setId(209);
    e1.setFirstName("gayathri");
    e1.setLastName("devi");
    
    e1.setId(558);  
    e1.setFirstName("roopini");  
    e1.setLastName("velamati");
    
    session.persist(e1);//persisting the object  
      
    t.commit();//transaction is committed  
    session.close();  
      
    System.out.println("successfully saved");  
      
}  
}