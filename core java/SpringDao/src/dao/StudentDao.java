package dao;

import java.awt.List;

import model.Student;

public interface StudentDao {
	public int save(Student std)throws Exception;
	public  boolean update(Student std)throws Exception;
	public  boolean delete(Student std)throws Exception;
	public Student findByPrimaryKey(int id)throws Exception;
	
	
	
	
	

}
	