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

import bean.Jqsq;
import bean.Json;
import bean.Kqjl;

import com.google.gson.Gson;

import dao.JqsqDao;
import dao.KqjlDao;

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
		String name=(String) request.getSession().getAttribute("name1");
		System.out.println(name);
		switch (operate) {
		case "kq":
			KqjlDao kd=new KqjlDao();
			String count=""+kd.findSelectedCount(name,date1,date2,offset,limit);
			List<Kqjl> list=new ArrayList<Kqjl>();
			list=kd.findSelected(name,date1,date2,offset,limit);
			Json json=new Json();
			json.setTotal(count);
			json.setRows(list);
			Gson gson=new Gson();
			out.print(gson.toJson(json));
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
