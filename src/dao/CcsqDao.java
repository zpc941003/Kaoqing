package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Ccsq;

public class CcsqDao {
	private Connection connection;
	private PreparedStatement infoQuery;
	private ResultSet results;

	//查找记录条数
	public int findCount(String name,int offset,int limit){
		int rowCount=0;
		try{connection=DBcon.getConnection();
			String sql="SELECT * FROM Ccsq where name='"+name+"' ORDER BY time DESC";
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
			String sql="SELECT * FROM Ccsq where name='"+name+"' AND time BETWEEN '"+date1+"' AND '"+date2+"' ORDER BY time DESC";
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
	public List<Ccsq> findAll(String name,int offset,int limit){
		List<Ccsq> list=new ArrayList<Ccsq>();
		try{connection=DBcon.getConnection();
			String sql="SELECT * FROM ccsq where name='"+name+"' ORDER BY time DESC LIMIT "+offset+","+limit+"";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			while(results.next()){
				Ccsq kj = new Ccsq();
				kj.setId(results.getInt("id"));
				kj.setTime(results.getString("time"));
				kj.setPlace(results.getString("place"));
				kj.setFriend(results.getString("friend"));
				kj.setTransport(results.getString("transport"));
				kj.setName(results.getString("name"));
				kj.setPeriod(results.getString("period"));
				kj.setState(results.getString("state"));
				list.add(kj);
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
	
	public List<Ccsq> findSelected(String name,String date1,String date2,int offset,int limit){
		List<Ccsq> list=new ArrayList<Ccsq>();
		try{connection=DBcon.getConnection();
			String sql="SELECT * FROM ccsq where name='"+name+"' AND time BETWEEN '"+date1+"' AND '"+date2+"' ORDER BY time DESC LIMIT "+offset+","+limit+"";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			while(results.next()){
				Ccsq kj = new Ccsq();
				kj.setId(results.getInt("id"));
				kj.setTime(results.getString("time"));
				kj.setPlace(results.getString("place"));
				kj.setFriend(results.getString("friend"));
				kj.setTransport(results.getString("transport"));
				kj.setName(results.getString("name"));
				kj.setPeriod(results.getString("period"));
				kj.setState(results.getString("state"));
				list.add(kj);
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
	public int add(Ccsq kj){
		int result=0;
		try{
			connection=DBcon.getConnection();
			String sql="insert into ccsq(time,period,name,friend,place,transport,state)values(?,?,?,?,?,?,?)";
			infoQuery = connection.prepareStatement(sql);
			infoQuery.setString(1,kj.getTime());
			infoQuery.setString(2,kj.getPeriod());
			infoQuery.setString(3,kj.getName());
			infoQuery.setString(4,kj.getFriend());
			infoQuery.setString(5,kj.getPlace());
			infoQuery.setString(6,kj.getTransport());
			infoQuery.setString(7,kj.getState());
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
			String sql = "delete from ccsq where id='" + id + "'";
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
	
	public int update(Ccsq kj) {//修改
		int result = 0;
		try {
			connection = DBcon.getConnection();
			String sql = "update Ccsq set time=?,period=?,";
			sql += "name=?,friend=?,place=?,transport=?,state=?where id=?";
			infoQuery = connection.prepareStatement(sql);
			infoQuery.setString(1, kj.getTime());
			infoQuery.setString(2, kj.getPeriod());
			infoQuery.setString(3, kj.getName());
			infoQuery.setString(4, kj.getFriend());
			infoQuery.setString(5, kj.getPlace());
			infoQuery.setString(6, kj.getTransport());
			infoQuery.setString(7, kj.getState());
			infoQuery.setInt(8, kj.getId());
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
