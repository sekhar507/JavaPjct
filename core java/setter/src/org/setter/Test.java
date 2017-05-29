package org.setter;

import org.springframework.beans.factory.BeanFactory;  
import org.springframework.beans.factory.xml.XmlBeanFactory;  
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;  
import org.springframework.core.io.Resource;  
  
public class Test {  
public static void main(String[] args) {  
    /*Resource resource=new ClassPathResource("/org/setter/applicationContext.xml");  
    BeanFactory factory=new XmlBeanFactory(resource);*/  
	ApplicationContext context = new ClassPathXmlApplicationContext("/org/setter/applicationContext.xml"); 
      
   /* Student student=(Student)factory.getBean("studentbean"); */ 
	Student student=(Student)context.getBean("studentbean");//calling with  bean name
	
    student.displayInfo();  
  }  
 }