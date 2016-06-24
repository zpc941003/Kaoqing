package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Jqsq;
import bean.Json;
import bean.Kqcx;

import com.google.gson.Gson;

import dao.JqsqDao;
import dao.KqcxDao;

public class ServletKqcx extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ServletKqcx() {
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
		String date1=request.getParameter("date1");
		String date2=request.getParameter("date2");
		System.out.println(date1+' '+date2);
		int limit =Integer.parseInt(request.getParameter("limit"));
		int offset=Integer.parseInt(request.getParameter("offset"));
		KqcxDao jd=new KqcxDao();
		String count=""+jd.findCount(date1,date2,offset,limit);
		List<Kqcx> list=new ArrayList<Kqcx>();
		list=jd.findAll(date1,date2,offset,limit);
		request.getSession().setAttribute("date1", date1);
		request.getSession().setAttribute("date2", date2);
		Json json=new Json();
		json.setTotal(count);
		json.setRows(list);
		Gson gson=new Gson();
		out.print(gson.toJson(json));
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

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String operate=request.getParameter("operate");
		switch (operate) {
		case "exit":
			request.getSession().setAttribute("username", null);
			out.print("{\"msg\":\"success\"}");
			break;
		case "kq":
			String name1=request.getParameter("name");
			request.getSession().setAttribute("name1", name1);
			out.print("{\"msg\":\"success\"}");
			break;
		case "jq":
			String name2=request.getParameter("name");
			request.getSession().setAttribute("name2", name2);
			out.print("{\"msg\":\"success\"}");
			break;
		case "cc":
			String name3=request.getParameter("name");
			request.getSession().setAttribute("name3", name3);
			out.print("{\"msg\":\"success\"}");
			break;
		case "jb":
			String name4=request.getParameter("name");
			request.getSession().setAttribute("name4", name4);
			out.print("{\"msg\":\"success\"}");
			break;
		case "tx":
			String name5=request.getParameter("name");
			request.getSession().setAttribute("name5", name5);
			out.print("{\"msg\":\"success\"}");
			break;
		default:
			break;
		}
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
