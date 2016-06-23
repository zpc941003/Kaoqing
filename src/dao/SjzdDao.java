package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Jqsq;
import bean.Kqjl;

public class SjzdDao {
	private Connection connection;
	private PreparedStatement infoQuery;
	private ResultSet results;


	//≤È’“»´≤ø
	public List findAll(){
		List list=new ArrayList();
		try{connection=DBcon.getConnection();
			String sql="SELECT username FROM adminlogin";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			while(results.next()){
				list.add(results.getString("username"));
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
		return list;
	}
}

