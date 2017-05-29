package org.spring.core;

public class Employee {  
private int id;  
private String name;  

private String city;
private int zip;
/*public void setCity(String city) {
	this.city = city;
}
  
public void setZip(int zip) {
	this.zip = zip;
}*/

/*Here i have class with name Employee and With ID and Name and
i generated set methods for id and name.*/
public Employee(int id)
{
	this.id = id;
	}  
  
public Employee(String name) 
{
	this.name = name;
	}  
  //This is parameterized constructor
public Employee(int id, String name) 
{  
    this.id = id;  
    this.name = name;  
}  
  // method to print
void show(){  
    System.out.println(name+" Your id is "+id);  
}  
  
}  