package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Kqcx;

public class KqcxDao {
	private Connection connection;
	private PreparedStatement infoQuery;
	private ResultSet results;

	//查找记录条数
	public int findCount(String date1,String date2,int offset,int limit){
		int rowCount=0;
		try{connection=DBcon.getConnection();
			String sql="SELECT username,kq,jq,cc,jb,tx FROM ";
			sql+="(SELECT username FROM adminlogin) a LEFT JOIN ";
			sql+="(SELECT name,count(name) AS kq FROM kqjl WHERE time BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) b ON a.username=b.name LEFT JOIN ";
			sql+="(SELECT name,count(name) AS jq FROM jqsq WHERE starttime BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) c ON a.username=c.name LEFT JOIN ";
			sql+="(SELECT name,count(name) AS cc FROM ccsq WHERE time BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) d ON a.username=d.name LEFT JOIN ";
			sql+="(SELECT name,count(name) AS jb FROM jbsq WHERE time BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) e ON a.username=e.name LEFT JOIN ";
			sql+="(SELECT name,count(name) AS tx FROM txsq WHERE time BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) f ON a.username=f.name;";
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
	public List<Kqcx> findAll(String date1,String date2,int offset,int limit){
		List<Kqcx> list=new ArrayList<Kqcx>();
		try{connection=DBcon.getConnection();
		String sql="SELECT username,kq,jq,cc,jb,tx FROM ";
		sql+="(SELECT username FROM adminlogin) a LEFT JOIN ";
		sql+="(SELECT name,count(name) AS kq FROM kqjl WHERE time BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) b ON a.username=b.name LEFT JOIN ";
		sql+="(SELECT name,count(name) AS jq FROM jqsq WHERE starttime BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) c ON a.username=c.name LEFT JOIN ";
		sql+="(SELECT name,count(name) AS cc FROM ccsq WHERE time BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) d ON a.username=d.name LEFT JOIN ";
		sql+="(SELECT name,count(name) AS jb FROM jbsq WHERE time BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) e ON a.username=e.name LEFT JOIN ";
		sql+="(SELECT name,count(name) AS tx FROM txsq WHERE time BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) f ON a.username=f.name;";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			while(results.next()){
				Kqcx kj = new Kqcx();
				kj.setKq(results.getInt("kq"));
				kj.setJq(results.getInt("jq"));
				kj.setCc(results.getInt("cc"));
				kj.setJb(results.getInt("jb"));
				kj.setTx(results.getInt("tx"));
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
}
