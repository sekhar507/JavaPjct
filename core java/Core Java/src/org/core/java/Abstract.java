package org.core.java;

public class Abstract {
	/*we can not create a instance for the abstract class
	 *we can have abstract methods and normal methods in abstract class
	 *we can not have abstract methods in normal class.
	 *when we are extending abstract classes we must implement abstract methods in the extending class
	 */
	abstract class Animal{
		abstract String bark();
		
	}
	 class Dog extends Animal{
		 String bark()
		{
			return "Bow bow";
		}
	}
	
	 public  void abstractclassExample()
	 
	 {
		Animal dog =new Dog();
		dog.bark();
	 }


}
