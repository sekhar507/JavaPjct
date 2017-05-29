package org.spring.core;

import org.springframework.beans.factory.BeanFactory;  
import org.springframework.beans.factory.xml.XmlBeanFactory;  
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.*;  
  
@SuppressWarnings("deprecation")// it asked me to put according to warnings
public class Test {  
    public static void main(String[] args) {  
    	
    	/*Here i wrote for both applicationContext and Bean Factory*/
          
       /* Resource r=new ClassPathResource("/org/spring/core/web.xml");  
        BeanFactory factory=new XmlBeanFactory(r);  
          Employee s=(Employee) factory.getBean("e"); */ 
        
          ApplicationContext context = new ClassPathXmlApplicationContext("/org/spring/core/web.xml");//here i have to give xml path
          Employee s=(Employee) context.getBean("e");//here i'm calling bean with its name
        s.show();  // simple calling for method
          
    }  
}