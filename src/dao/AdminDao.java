package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Admin;

public class AdminDao {
	private Connection connection;
	private PreparedStatement infoQuery;
	private ResultSet results;

	//≤È’“
	public boolean findPerson(String name,String password){
		boolean status=false;
		Admin person = new Admin();
		try{connection=DBcon.getConnection();
			String sql="SELECT * FROM adminlogin where username='"+name+"' AND password='"+password+"'";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			if(results.next()){
				status=true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBcon.closeResultSet(results);
			DBcon.closeStatement(infoQuery);
			DBcon.closeConnection(connection);
		}
		return status;
	
	}
}
