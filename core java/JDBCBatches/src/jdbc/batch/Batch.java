package jdbc.batch;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Batch {
	public static void main(String args[]) {
		Connection conn=null;
		Statement stmt=null;
		try{
			Class.forName("com.mysql.jdbc.driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root", "root");
			System.out.println("Connection successfully");
		}
		catch(Exception e)
		{
			System.out.println(e + ":Class not found");
		}
		finally{
			if(conn!=null){
				try{
					System.out.println("connection closed");
					conn.close();
				}catch(SQLException e)
				{
					System.out.println("Exception in finally if1");
				}
				
			}
			if(stmt!=null){
				try{
					System.out.println("stmt closed");
					stmt.close();
				}catch(SQLException e)
				{
					System.out.println("Exception in finally if2");
				}
				
			}
		}
		

	}
}
