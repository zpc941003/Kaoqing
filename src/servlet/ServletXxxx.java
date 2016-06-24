package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Ccsq;
import bean.Jbsq;
import bean.Jqsq;
import bean.Json;
import bean.Kqjl;
import bean.Txsq;

import com.google.gson.Gson;

import dao.CcsqDao;
import dao.JbsqDao;
import dao.JqsqDao;
import dao.KqjlDao;
import dao.TxsqDao;

public class ServletXxxx extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ServletXxxx() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int limit =Integer.parseInt(request.getParameter("limit"));
		int offset=Integer.parseInt(request.getParameter("offset"));
		String date1=(String) request.getSession().getAttribute("date1");
		String date2=(String) request.getSession().getAttribute("date2");
		String operate=request.getParameter("operate");
		String name1=(String) request.getSession().getAttribute("name1");
		String name2=(String) request.getSession().getAttribute("name2");
		String name3=(String) request.getSession().getAttribute("name3");
		String name4=(String) request.getSession().getAttribute("name4");
		String name5=(String) request.getSession().getAttribute("name5");
		switch (operate) {
		case "kq":
			KqjlDao kd=new KqjlDao();
			String count=""+kd.findSelectedCount(name1,date1,date2,offset,limit);
			List<Kqjl> list=new ArrayList<Kqjl>();
			list=kd.findSelected(name1,date1,date2,offset,limit);
			Json json=new Json();
			json.setTotal(count);
			json.setRows(list);
			Gson gson=new Gson();
			out.print(gson.toJson(json));
			break;
		case "jq":
			JqsqDao jd=new JqsqDao();
			String count1=""+jd.findSelectedCount(name2,date1,date2,offset,limit);
			List<Jqsq> list1=new ArrayList<Jqsq>();
			list1=jd.findSelected(name2,date1,date2,offset,limit);
			Json json1=new Json();
			json1.setTotal(count1);
			json1.setRows(list1);
			Gson gson1=new Gson();
			out.print(gson1.toJson(json1));
			break;
		case "cc":
			CcsqDao cd=new CcsqDao();
			String count2=""+cd.findSelectedCount(name3,date1,date2,offset,limit);
			List<Ccsq> list2=new ArrayList<Ccsq>();
			list2=cd.findSelected(name3,date1,date2,offset,limit);
			Json json2=new Json();
			json2.setTotal(count2);
			json2.setRows(list2);
			Gson gson2=new Gson();
			out.print(gson2.toJson(json2));
			break;
		case "jb":
			JbsqDao jd1=new JbsqDao();
			String count11=""+jd1.findSelectedCount(name4,date1,date2,offset,limit);
			List<Jbsq> list11=new ArrayList<Jbsq>();
			list11=jd1.findSelected(name4,date1,date2,offset,limit);
			Json json11=new Json();
			json11.setTotal(count11);
			json11.setRows(list11);
			Gson gson11=new Gson();
			out.print(gson11.toJson(json11));
			break;
		case "tx":
			TxsqDao td=new TxsqDao();
			String count3=""+td.findSelectedCount(name5,date1,date2,offset,limit);
			List<Txsq> list3=new ArrayList<Txsq>();
			list3=td.findSelected(name5,date1,date2,offset,limit);
			Json json3=new Json();
			json3.setTotal(count3);
			json3.setRows(list3);
			Gson gson3=new Gson();
			out.print(gson3.toJson(json3));
			break;

		default:
			break;
		}
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
