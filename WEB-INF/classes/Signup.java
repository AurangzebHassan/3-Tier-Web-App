import java.sql.*;

import java.util.*;

import java.io.*;

import jakarta.servlet.*;

import jakarta.servlet.http.*;



public class Signup extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String uname = req.getParameter("username");
		
		String passwd = req.getParameter("password");
		
		String addr = req.getParameter("address");
		
		String phone_no = req.getParameter("phone");
		
		
		
		DAO dao = new DAO();
		
		Connection con = dao.getConnection();
		
		
		
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		
		
		String user_verification_query = "SELECT * FROM my_second_web_app_users " + 
					
										 "WHERE (username = ?)";
		
		
		try(PreparedStatement ps1 = con.prepareStatement(user_verification_query))
		{
			ps1.setString(1, uname);

			

			ResultSet rs = ps1.executeQuery();
			
			
			
			if(rs.next())
			{
				out.println("<html>");
            
				out.println("<head>");
            
				out.println("<title>Signup Greeting</title>");
				
				out.println("</head>");
            
				out.println("<body style=\'display:flex; flex-direction:column; align-items:center; justify-content:center; background-color:#404041; color:#E6C17A;\'>");
            
				out.println("<h1 style=\'margin-bottom:24px; margin-top:215px;\'> SIGNUP UNSUCCESSFUL! <br> </h1>");

				out.println("<div style=\'display:flex;\'><h1 style=\'margin-right: 10px; font-size: 50px; position: relative; bottom: 30px;\'>" + uname + " " + "</h1>");
				
				out.println("<h3 style=\'margin-top:30px;\'> ALREADY EXISTS! </h3></div>");
            
				out.println("</body>");
            
				out.println("</html>");
			}
			
			
			
			else
			{
				String insert_query = "INSERT INTO my_second_web_app_users" +
		
									  "(username, password, address, phone) VALUES" +
							  
									  "(?, ?, ?, ?)";
		
				
				try(PreparedStatement ps2 = con.prepareStatement(insert_query))
				{
					ps2.setString(1, uname);
					
					ps2.setString(2, passwd);
					
					ps2.setString(3, addr);
					
					ps2.setString(4, phone_no);
					
					
					
					int rows_changed = ps2.executeUpdate();
					
					
					
					if(rows_changed == 1)
					{
						out.println("<html>");
					
						out.println("<head>");
					
						out.println("<title>Signup Greeting</title>");
						
						out.println("</head>");
					
						out.println("<body style=\'display:flex; flex-direction:column; align-items:center; justify-content:center; background-color:#404041; color:#E6C17A;\'>");
					
						out.println("<h1 style=\'margin-bottom:24px; margin-top:215px;\'> SIGNUP SUCCESSFUL! <br> </h1>");

						out.println("<div style=\'display:flex;\'><h1 style=\'margin-right: 10px; font-size: 50px; position: relative; bottom: 30px;\'>" + uname + " " + "</h1>");
						
						out.println("<h3 style=\'margin-top:30px;\'> IS NOW SIGNED UP! </h3></div>");
					
						out.println("</body>");
					
						out.println("</html>");
					}
					
					
					
					else
					{
						out.println("<html>");
					
						out.println("<head>");
					
						out.println("<title>Signup Greeting</title>");
						
						out.println("</head>");
					
						out.println("<body style=\'display:flex; align-items:center; justify-content:center; background-color:#404041; color:#E6C17A;\'>");
					
						out.println("<h1> SIGNUP UNSUCCESSFUL! </h1>");

						out.println("</body>");
					
						out.println("</html>");
					}
					
					out.close();
				}
				
				
				
				catch(SQLException sqle)
				{
					System.out.println("SQL ERROR OCCURED: " + sqle.getMessage());
				}
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
	