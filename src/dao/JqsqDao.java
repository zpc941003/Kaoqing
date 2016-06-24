package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Jqsq;
import bean.Kqjl;

public class JqsqDao {
	private Connection connection;
	private PreparedStatement infoQuery;
	private ResultSet results;

	//查找记录条数
	public int findCount(String name,int offset,int limit){
		int rowCount=0;
		try{connection=DBcon.getConnection();
			String sql="SELECT * FROM jqsq where name='"+name+"' ORDER BY starttime DESC";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			if(results.next()){
				results.last(); // 将光标移动到最后一行     
				rowCount = results.getRow(); // 得到当前行号，即结果集记录数 
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
		return rowCount;
	}
	
	public int findSelectedCount(String name,String date1,String date2,int offset,int limit){
		int rowCount=0;
		try{connection=DBcon.getConnection();
			String sql="SELECT * FROM jqsq where name='"+name+"' AND starttime BETWEEN '"+date1+"' AND '"+date2+"' ORDER BY starttime DESC";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			if(results.next()){
				results.last(); // 将光标移动到最后一行     
				rowCount = results.getRow(); // 得到当前行号，即结果集记录数 
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
		return rowCount;
	}
	//查找全部
	public List<Jqsq> findAll(String name,int offset,int limit){
		List<Jqsq> list=new ArrayList<Jqsq>();
		try{connection=DBcon.getConnection();
			String sql="SELECT * FROM jqsq where name='"+name+"' ORDER BY starttime DESC LIMIT "+offset+","+limit+"";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			while(results.next()){
				Jqsq js = new Jqsq();
				js.setId(results.getInt("id"));
				js.setStarttime(results.getString("starttime"));
				js.setEndtime(results.getString("endtime"));
				js.setCategory(results.getString("category"));
				js.setName(results.getString("name"));
				js.setPeriod(results.getString("period"));
				js.setState(results.getString("state"));
				list.add(js);
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
	
	public List<Jqsq> findSelected(String name,String date1,String date2,int offset,int limit){
		List<Jqsq> list=new ArrayList<Jqsq>();
		try{connection=DBcon.getConnection();
			String sql="SELECT * FROM jqsq where name='"+name+"' AND starttime BETWEEN '"+date1+"' AND '"+date2+"' ORDER BY starttime DESC LIMIT "+offset+","+limit+"";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			while(results.next()){
				Jqsq js = new Jqsq();
				js.setId(results.getInt("id"));
				js.setStarttime(results.getString("starttime"));
				js.setEndtime(results.getString("endtime"));
				js.setCategory(results.getString("category"));
				js.setName(results.getString("name"));
				js.setPeriod(results.getString("period"));
				js.setState(results.getString("state"));
				list.add(js);
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
	
	//插入
	public int add(Jqsq js){
		int result=0;
		try{
			connection=DBcon.getConnection();
			String sql="insert into jqsq(starttime,endtime,period,name,category,state)values(?,?,?,?,?,?)";
			infoQuery = connection.prepareStatement(sql);
			infoQuery.setString(1,js.getStarttime());
			infoQuery.setString(2,js.getEndtime());
			infoQuery.setString(3,js.getPeriod());
			infoQuery.setString(4,js.getName());
			infoQuery.setString(5,js.getCategory());
			infoQuery.setString(6,js.getState());
			result = infoQuery.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBcon.closeResultSet(results);
			DBcon.closeStatement(infoQuery);
			DBcon.closeConnection(connection);			
		}
		return result;
	}
	
	public int delete(int id) {//删除记录
		int result = 0;
		try {
			connection = DBcon.getConnection();
			String sql = "delete from jqsq where id='" + id + "'";
			infoQuery = connection.prepareStatement(sql);
			result = infoQuery.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(infoQuery);
			DBcon.closeConnection(connection);

		}
		return result;
	}
	
	public int update(Jqsq js) {//修改
		int result = 0;
		try {
			connection = DBcon.getConnection();
			String sql = "update jqsq set starttime=?,endtime=?,";
			sql += "period=?,category=?,state=?where id=?";
			infoQuery = connection.prepareStatement(sql);
			infoQuery.setString(1, js.getStarttime());
			infoQuery.setString(2, js.getEndtime());
			infoQuery.setString(3, js.getPeriod());
			infoQuery.setString(4, js.getCategory());
			infoQuery.setString(5, js.getState());
			infoQuery.setInt(6, js.getId());
			result = infoQuery.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}
		// 释放资源
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(infoQuery);
			DBcon.closeConnection(connection);

		}
		return result;

	}
}

