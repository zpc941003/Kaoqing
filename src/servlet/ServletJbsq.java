package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Jbsq;
import bean.Json;

import com.google.gson.Gson;

import dao.JbsqDao;


public class ServletJbsq extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ServletJbsq() {
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
		JbsqDao jd=new JbsqDao();
		String count=""+jd.findCount(name,offset,limit);
		List<Jbsq> list=new ArrayList<Jbsq>();
		list=jd.findAll(name,offset,limit);
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
		case "add":
			Jbsq js=new Jbsq();
			js.setTime(request.getParameter("time"));
			js.setJbtime(request.getParameter("jbtime"));
			js.setName(request.getParameter("name"));
			js.setPeriod(request.getParameter("period"));
			js.setState(request.getParameter("state"));
			JbsqDao jd=new JbsqDao();
			int result=jd.add(js);
			System.out.println(result);
			if(result==1){
				out.print("{\"msg\":\"success\"}");
			}else{
				out.print("{\"msg\":\"error\"}");
			}
			break;
		case "delete":
			String id=request.getParameter("id");
			int id1=Integer.parseInt(id);
			JbsqDao jd1=new JbsqDao();
			int result1=jd1.delete(id1);
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
			Jbsq js1=new Jbsq();
			js1.setId(Integer.parseInt(request.getParameter("id")));
			js1.setTime(request.getParameter("time"));
			js1.setJbtime(request.getParameter("jbtime"));
			js1.setName(request.getParameter("name"));
			js1.setPeriod(request.getParameter("period"));
			js1.setState(request.getParameter("state"));
			JbsqDao jd2=new JbsqDao();
			int result2=jd2.update(js1);
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
