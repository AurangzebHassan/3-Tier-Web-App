import java.sql.*;

import jakarta.servlet.*;

import jakarta.servlet.http.*;



public class DAO 
{
    private Connection con;



    public DAO() 
	{
        try 
		{
            Class.forName("com.mysql.jdbc.Driver");


            String url = "jdbc:mysql://127.0.0.1:3306/mysql";
			
            String username = "root";
			
            String password = "root";



            this.con = DriverManager.getConnection(url, username, password);
        }


		
		catch (ClassNotFoundException cnfe) 
		{
            System.out.println("JDBC DRIVER NOT FOUND: " + cnfe.getMessage());
        } 
		
		
		
		catch (SQLException sqle) 
		{
            System.out.println("CONNECTION CREATION FAILURE: " + sqle.getMessage());
        }
    }






    public Connection getConnection() 
	{
        return (this.con);
    }
	
	
	
	
	
	
    public void closeConnection() 
	{
        if (this.con != null) 
		{
            try 
			{
                this.con.close();
            } 
			
			
			
			catch (SQLException sqle) 
			{
                System.out.println("CONNECTION CLOSURE FAILURE: " + sqle.getMessage());
            }
        }
    }
}

