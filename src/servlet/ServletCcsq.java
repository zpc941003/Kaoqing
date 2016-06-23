package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Json;
import bean.Ccsq;

import com.google.gson.Gson;

import dao.CcsqDao;
import dao.SjzdDao;

public class ServletCcsq extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ServletCcsq() {
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
		String name=(String) request.getSession().getAttribute("username");
		int limit =Integer.parseInt(request.getParameter("limit"));
		int offset=Integer.parseInt(request.getParameter("offset"));
		CcsqDao kd=new CcsqDao();
		String count=""+kd.findCount(name,offset,limit);
		List<Ccsq> list=new ArrayList<Ccsq>();
		list=kd.findAll(name,offset,limit);
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
		case "getpeople":
			SjzdDao sd=new SjzdDao();
			List list=new ArrayList();
			list=sd.findAll();
			Gson gson=new Gson();
			out.print(gson.toJson(list));
			break;
		case "add":
			Ccsq kj=new Ccsq();
			kj.setTime(request.getParameter("time"));
			kj.setPlace(request.getParameter("place"));
			kj.setName(request.getParameter("name"));
			kj.setPeriod(request.getParameter("period"));
			kj.setState(request.getParameter("state"));
			kj.setFriend(request.getParameter("friend"));
			kj.setTransport(request.getParameter("transport"));
			CcsqDao kd=new CcsqDao();
			int result=kd.add(kj);
			System.out.println(result);
			if(result==1){
				out.print("{\"msg\":\"success\"}");
			}else{
				out.print("{\"msg\":\"error\"}");
			}
			break;
		case "delete":
			String id=request.getParameter("id");
			System.out.println(id);
			int id1=Integer.parseInt(id);
			CcsqDao kd1=new CcsqDao();
			int result1=kd1.delete(id1);
			if(result1==1){
				out.print("{\"msg\":\"success\"}");
			}else{
				out.print("{\"msg\":\"error\"}");
			}
			break;
		case "exit":
			request.getSession().setAttribute("username", null);
			out.print("{\"msg\":\"success\"}");
			break;
		case "alter":
			Ccsq kj1=new Ccsq();
			kj1.setId(Integer.parseInt(request.getParameter("id")));
			kj1.setPlace(request.getParameter("place"));
			kj1.setFriend(request.getParameter("friend"));
			kj1.setTransport(request.getParameter("transport"));
			kj1.setTime(request.getParameter("time"));
			kj1.setName(request.getParameter("name"));
			kj1.setPeriod(request.getParameter("period"));
			kj1.setState(request.getParameter("state"));
			CcsqDao kd2=new CcsqDao();
			int result2=kd2.update(kj1);
			System.out.println(result2);
			if(result2==1){
				out.print("{\"msg\":\"success\"}");
			}else{
				out.print("{\"msg\":\"error\"}");
			}
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
