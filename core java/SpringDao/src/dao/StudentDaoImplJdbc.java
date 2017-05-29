package dao;

import java.sql.Connection;

import javax.activation.DataSource;

import com.mysql.jdbc.PreparedStatement;

import model.Student;

public class StudentDaoImplJdbc implements StudentDao {
	private DataSource ds;

	@Override
	public int save(Student std) throws Exception {
		Connection conn=	ds.getConnection();
		PreparedStatement ps= conn.prepareStatement("insert into student values(?,?,?,?)");
		ps.setInt(1,getId());
		return 0;
	}

	@Override
	public boolean update(Student std) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Student std) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Student findByPrimaryKey(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
