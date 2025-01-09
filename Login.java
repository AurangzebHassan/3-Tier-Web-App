import java.sql.*;

import java.util.*;

import java.io.*;

import jakarta.servlet.*;

import jakarta.servlet.http.*;



public class Login extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String uname = req.getParameter("username");
		
		String passwd = req.getParameter("password");
	
		
		
		DAO dao = new DAO();
		
		Connection con = dao.getConnection();
		
		
		
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		
		
		String search_query = "SELECT * FROM my_second_web_app_users " + 
					
							  "WHERE ((username = ?) AND (password = ?))";
		
		
		try(PreparedStatement ps = con.prepareStatement(search_query))
		{
			ps.setString(1, uname);
			
			ps.setString(2, passwd);

			

			ResultSet rs = ps.executeQuery();
			
			
			
			if(rs.next())
			{
				out.println("<html>");
            
				out.println("<head>");
            
				out.println("<title>Login Greeting</title>");
				
				out.println("</head>");
            
				//out.println("<body style=\'display:flex; flex-direction:column; align-items:center; justify-content:center; background-color:#404041; color:#E6C17A;\'>");
            
				//out.println("<h1 style=\'margin-bottom:30px; margin-top:215px;\'> LOGIN SUCCESSFUL! <br> </h1>");
				
				//out.println("<h1> WELCOME, " + uname +  "! </h1>");
				
				out.println("<body style=\'display:flex; flex-direction:column; align-items:center; justify-content:center; background-color:#404041; color:#E6C17A;\'>");
            
				out.println("<h1 style=\'margin-bottom:24px; margin-top:215px;\'> LOGIN SUCCESSFUL! <br> </h1>");

				out.println("<div style=\'display:flex;\'><h3 style=\'margin-top:30px; margin-right:10px;\'> WELCOME, </h3>");
				
				out.println("<h1 style=\'margin-right: 10px; font-size: 50px; position: relative; bottom: 30px;\'>" + " " + uname + "</h1></div>");
				
				out.println("</body>");
            
				out.println("</html>");
			}
			
			
			
			else
			{
				out.println("<html>");
            
				out.println("<head>");
            
				out.println("<title>Login Greeting</title>");
				
				out.println("</head>");
            
				out.println("<body style=\'display:flex; flex-direction:column; align-items:center; justify-content:center; background-color:#404041; color:#E6C17A;\'>");
            
				out.println("<h1 style=\'margin-bottom:24px; margin-top:215px;\'> LOGIN UNSUCCESSFUL! <br> </h1>");

				out.println("<div style=\'display:flex;\'><h1 style=\'margin-right: 10px; font-size: 50px; position: relative; bottom: 30px;\'>" + uname + " " + "</h1>");
				
				out.println("<h3 style=\'margin-top:30px;\'> IS NOT SIGNED UP! </h3></div>");
            
				out.println("</body>");
            
				out.println("</html>");
			}
			
			out.close();
		}
		
		
		
		catch(SQLException sqle)
		{
			System.out.println("SQL ERROR OCCURED: " + sqle.getMessage());
		}
		
		
		
		finally
		{
			dao.closeConnection();
		}
	}
}
	