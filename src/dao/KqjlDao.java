package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Kqjl;

public class KqjlDao {
	private Connection connection;
	private PreparedStatement infoQuery;
	private ResultSet results;

	//���Ҽ�¼����
	public int findCount(String name,int offset,int limit){
		int rowCount=0;
		try{connection=DBcon.getConnection();
			String sql="SELECT * FROM kqjl where name='"+name+"' ORDER BY time DESC";
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
	public List<Kqjl> findAll(String name,int offset,int limit){
		List<Kqjl> list=new ArrayList<Kqjl>();
		try{connection=DBcon.getConnection();
			String sql="SELECT * FROM kqjl where name='"+name+"' ORDER BY time DESC LIMIT "+offset+","+limit+"";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			while(results.next()){
				Kqjl kj = new Kqjl();
				kj.setId(results.getInt("id"));
				kj.setTime(results.getString("time"));
				kj.setCategory(results.getString("category"));
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
	
	//����
	public int add(Kqjl kj){
		int result=0;
		try{
			connection=DBcon.getConnection();
			String sql="insert into kqjl(time,category,name,period,state)values(?,?,?,?,?)";
			infoQuery = connection.prepareStatement(sql);
			infoQuery.setString(1,kj.getTime());
			infoQuery.setString(2,kj.getCategory());
			infoQuery.setString(3,kj.getName());
			infoQuery.setString(4,kj.getPeriod());
			infoQuery.setString(5,kj.getState());
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
			String sql = "delete from kqjl where id='" + id + "'";
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
	
	public int update(Kqjl kj) {//�޸�
		int result = 0;
		try {
			connection = DBcon.getConnection();
			String sql = "update kqjl set time=?,category=?,";
			sql += "period=?,state=?where id=?";
			infoQuery = connection.prepareStatement(sql);
			infoQuery.setString(1, kj.getTime());
			infoQuery.setString(2, kj.getCategory());
			infoQuery.setString(3, kj.getPeriod());
			infoQuery.setString(4, kj.getState());
			infoQuery.setInt(5, kj.getId());
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
