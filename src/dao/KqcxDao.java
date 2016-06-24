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
			sql+="(SELECT name,CAST(count(name) AS CHAR(10)) AS kq FROM kqjl WHERE time BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) b ON a.username=b.name LEFT JOIN ";
			sql+="(SELECT name,CAST(count(name) AS CHAR(10)) AS jq FROM jqsq WHERE starttime BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) c ON a.username=c.name LEFT JOIN ";
			sql+="(SELECT name,CAST(count(name) AS CHAR(10)) AS cc FROM ccsq WHERE time BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) d ON a.username=d.name LEFT JOIN ";
			sql+="(SELECT name,CAST(count(name) AS CHAR(10)) AS jb FROM jbsq WHERE time BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) e ON a.username=e.name LEFT JOIN ";
			sql+="(SELECT name,CAST(count(name) AS CHAR(10)) AS tx FROM txsq WHERE time BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) f ON a.username=f.name;";
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
		sql+="(SELECT name,CAST(count(name) AS CHAR(10)) AS kq FROM kqjl WHERE time BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) b ON a.username=b.name LEFT JOIN ";
		sql+="(SELECT name,CAST(count(name) AS CHAR(10)) AS jq FROM jqsq WHERE starttime BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) c ON a.username=c.name LEFT JOIN ";
		sql+="(SELECT name,CAST(count(name) AS CHAR(10)) AS cc FROM ccsq WHERE time BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) d ON a.username=d.name LEFT JOIN ";
		sql+="(SELECT name,CAST(count(name) AS CHAR(10)) AS jb FROM jbsq WHERE time BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) e ON a.username=e.name LEFT JOIN ";
		sql+="(SELECT name,CAST(count(name) AS CHAR(10)) AS tx FROM txsq WHERE time BETWEEN '"+date1+"' AND '"+date2+"' GROUP BY name) f ON a.username=f.name;";
			infoQuery=connection.prepareStatement(sql);
			results=infoQuery.executeQuery();
			while(results.next()){
				Kqcx kj = new Kqcx();
				String username=results.getString("username");
				kj.setUsername(results.getString("username"));
				String kq=results.getString("kq");
				String jq=results.getString("jq");
				String cc=results.getString("cc");
				String jb=results.getString("jb");
				String tx=results.getString("tx");
				kj.setKq("<a title='"+username+"' href='javascript:void(0)' class='kq' onclick='dokq(this)' data-toggle='modal' data-target='#myModal1'>"+kq+"</a>");
				kj.setJq("<a title='"+username+"' href='javascript:void(0)' class='jq' onclick='dojq(this)' data-toggle='modal' data-target='#myModal2'>"+jq+"</a>");
				kj.setCc("<a title='"+username+"' href='javascript:void(0)' class='cc' onclick='docc(this)' data-toggle='modal' data-target='#myModal3'>"+cc+"</a>");
				kj.setJb("<a title='"+username+"' href='javascript:void(0)' class='jb' onclick='dojb(this)' data-toggle='modal' data-target='#myModal4'>"+jb+"</a>");
				kj.setTx("<a title='"+username+"' href='javascript:void(0)' class='tx' onclick='dotx(this)' data-toggle='modal' data-target='#myModal5'>"+tx+"</a>");
				System.out.println(results.getString("username")+kj.getKq()+results.getString("jq")+results.getString("cc")+results.getString("jb")+results.getString("tx"));
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
