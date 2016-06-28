package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EmployeeDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection connection;
	Statement statement;
	@Override
	public void init() throws ServletException {
		
			 String serverName = "localhost";
	            String portNumber = "3306";
	            String sid = "userdb";
	            String dbUrl = "jdbc:mysql://" + serverName + "/" + sid;
	            try {
	            Class.forName("com.mysql.jdbc.Driver");
	            // set the url, username and password for the database
	            connection = DriverManager.getConnection(dbUrl, "system", "system"
	            		+ "");
		}
	            
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int employeeID=Integer.parseInt(request.getParameter("empid"));
		String employeeName=request.getParameter("ename");
		String email=request.getParameter("email");
		double salary=Double.parseDouble(request.getParameter("salary"));
		
		PrintWriter out=response.getWriter();
		
		
		String query="insert into employee values ("+employeeID+",'"+employeeName+"','"+email+"',"+salary+")";
		System.out.println(query);
		
		try {
			statement=connection.createStatement();
			int result=statement.executeUpdate(query);
			RequestDispatcher dispatcher;
			
			
			if(result>0){
				String selectQuery="select * from employee";
				statement=connection.createStatement();
				ResultSet resultSet=statement.executeQuery(selectQuery);
				
				ArrayList<Employee> employeeList=new ArrayList<Employee>();
				Employee employee =null;
				
				 while (resultSet.next())
			      {
			      
			        employee = new Employee();
			 		employee.setEmployeeName(resultSet.getString("employeeName"));
			 	    employee.setEmailID(resultSet.getString("emailid"));
			 		employee.setSalary(Double.parseDouble(resultSet.getString("salary")));
			 		employeeList.add(employee);
			      }
				 request.getSession().setAttribute("employeeList", employeeList);
			        RequestDispatcher view = request.getRequestDispatcher("/display.jsp");
			          view.forward(request, response);
			}
			else{
//				out.println("row insereted unsuccessfully");
				dispatcher=request.getRequestDispatcher("/failure.html");
			dispatcher.include(request, response);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
