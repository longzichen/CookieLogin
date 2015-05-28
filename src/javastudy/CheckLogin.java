package javastudy;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class CheckLogin
 */
@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("userName");
		String pwd=request.getParameter("pwd");
		@SuppressWarnings("unchecked")
		HashMap<String, String> map=(HashMap<String, String>)request.getSession().getServletContext().getAttribute("USERS");
		String url="login.jsp";
		if( map.containsKey(name))
		{
			String mpwd=map.get(name);
			if(mpwd.equals(pwd))
			{
				request.getSession().setAttribute("NAME", name);
				name=URLEncoder.encode(name,"utf-8");
				Cookie c1=new Cookie("name",name);
				c1.setMaxAge(60*60);
				Cookie c2=new Cookie("pwd",pwd);
				c2.setMaxAge(60*60);
				response.addCookie(c1);
				response.addCookie(c2);
				url="welcome.jsp";
			}
		}
		response.sendRedirect(url);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
