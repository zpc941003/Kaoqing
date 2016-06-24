package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Txsq;

public class TxsqDao {
	private Connection connection;
	private PreparedStatement infoQuery;
	private ResultSet results;

	//���Ҽ�¼����
	public int findCount(String name,int offset,int limit){
		int rowCount=0;
		try{connection=DBcon.getConnection();
			String sql="SELECT * FROM txsq where name='"+name+"' ORDER BY time DESC";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			if(results.next()){
				results.last(); // ������ƶ������һ��     
				rowCount = results.getRow(); // �õ���ǰ�кţ����������¼�� 
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
			String sql="SELECT * FROM txsq where name='"+name+"' AND time BETWEEN '"+date1+"' AND '"+date2+"' ORDER BY time DESC";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			if(results.next()){
				results.last(); // ������ƶ������һ��     
				rowCount = results.getRow(); // �õ���ǰ�кţ����������¼�� 
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
	//����ȫ��
	public List<Txsq> findAll(String name,int offset,int limit){
		List<Txsq> list=new ArrayList<Txsq>();
		try{connection=DBcon.getConnection();
			String sql="SELECT * FROM txsq where name='"+name+"' ORDER BY time DESC LIMIT "+offset+","+limit+"";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			while(results.next()){
				Txsq js = new Txsq();
				js.setId(results.getInt("id"));
				js.setTime(results.getString("time"));
				js.setStarttime(results.getString("starttime"));
				js.setEndtime(results.getString("endtime"));
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
	public List<Txsq> findSelected(String name,String date1,String date2,int offset,int limit){
		List<Txsq> list=new ArrayList<Txsq>();
		try{connection=DBcon.getConnection();
			String sql="SELECT * FROM txsq where name='"+name+"' AND time BETWEEN '"+date1+"' AND '"+date2+"' ORDER BY time DESC LIMIT "+offset+","+limit+"";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			while(results.next()){
				Txsq js = new Txsq();
				js.setId(results.getInt("id"));
				js.setTime(results.getString("time"));
				js.setStarttime(results.getString("starttime"));
				js.setEndtime(results.getString("endtime"));
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
	
	//����
	public int add(Txsq js){
		int result=0;
		try{
			connection=DBcon.getConnection();
			String sql="insert into txsq(time,starttime,endtime,period,name,state)values(?,?,?,?,?,?)";
			infoQuery = connection.prepareStatement(sql);
			infoQuery.setString(1,js.getTime());
			infoQuery.setString(2,js.getStarttime());
			infoQuery.setString(3,js.getEndtime());
			infoQuery.setString(4,js.getPeriod());
			infoQuery.setString(5,js.getName());
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
	
	public int delete(int id) {//ɾ����¼
		int result = 0;
		try {
			connection = DBcon.getConnection();
			String sql = "delete from txsq where id='" + id + "'";
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
	
	public int update(Txsq js) {//�޸�
		int result = 0;
		try {
			connection = DBcon.getConnection();
			String sql = "update txsq set time=?,starttime=?,endtime=?,";
			sql += "period=?,state=?where id=?";
			infoQuery = connection.prepareStatement(sql);
			infoQuery.setString(1, js.getTime());
			infoQuery.setString(2, js.getStarttime());
			infoQuery.setString(3, js.getEndtime());
			infoQuery.setString(4, js.getPeriod());
			infoQuery.setString(5, js.getState());
			infoQuery.setInt(6, js.getId());
			result = infoQuery.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}
		// �ͷ���Դ
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(infoQuery);
			DBcon.closeConnection(connection);

		}
		return result;

	}
}

