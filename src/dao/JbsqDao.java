package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Jbsq;

public class JbsqDao {
	private Connection connection;
	private PreparedStatement infoQuery;
	private ResultSet results;

	//查找记录条数
	public int findCount(String name,int offset,int limit){
		int rowCount=0;
		try{connection=DBcon.getConnection();
			String sql="SELECT * FROM jbsq where name='"+name+"' ORDER BY time DESC";
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
			String sql="SELECT * FROM jbsq where name='"+name+"' AND time BETWEEN '"+date1+"' AND '"+date2+"' ORDER BY time DESC";
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
	public List<Jbsq> findAll(String name,int offset,int limit){
		List<Jbsq> list=new ArrayList<Jbsq>();
		try{connection=DBcon.getConnection();
			String sql="SELECT * FROM jbsq where name='"+name+"' ORDER BY time DESC LIMIT "+offset+","+limit+"";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			while(results.next()){
				Jbsq js = new Jbsq();
				js.setId(results.getInt("id"));
				js.setTime(results.getString("time"));
				js.setJbtime(results.getString("jbtime"));
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
	
	public List<Jbsq> findSelected(String name,String date1,String date2,int offset,int limit){
		List<Jbsq> list=new ArrayList<Jbsq>();
		try{connection=DBcon.getConnection();
			String sql="SELECT * FROM jbsq where name='"+name+"' AND time BETWEEN '"+date1+"' AND '"+date2+"' ORDER BY time DESC LIMIT "+offset+","+limit+"";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			while(results.next()){
				Jbsq js = new Jbsq();
				js.setId(results.getInt("id"));
				js.setTime(results.getString("time"));
				js.setJbtime(results.getString("jbtime"));
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
	public int add(Jbsq js){
		int result=0;
		try{
			connection=DBcon.getConnection();
			String sql="insert into jbsq(time,jbtime,period,name,state)values(?,?,?,?,?)";
			infoQuery = connection.prepareStatement(sql);
			infoQuery.setString(1,js.getTime());
			infoQuery.setString(2,js.getJbtime());
			infoQuery.setString(3,js.getPeriod());
			infoQuery.setString(4,js.getName());
			infoQuery.setString(5,js.getState());
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
			String sql = "delete from jbsq where id='" + id + "'";
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
	
	public int update(Jbsq js) {//修改
		int result = 0;
		try {
			connection = DBcon.getConnection();
			String sql = "update jbsq set time=?,jbtime=?,";
			sql += "period=?,state=?where id=?";
			infoQuery = connection.prepareStatement(sql);
			infoQuery.setString(1, js.getTime());
			infoQuery.setString(2, js.getJbtime());
			infoQuery.setString(3, js.getPeriod());
			infoQuery.setString(4, js.getState());
			infoQuery.setInt(5, js.getId());
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

