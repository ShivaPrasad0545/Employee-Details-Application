<%@page import="com.employee.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee details</title>
</head>
<body>
<% 
ArrayList<Employee> arrayList=new ArrayList<Employee>();

if (request.getSession().getAttribute("employeeList")!=null)
{
	arrayList=(ArrayList)request.getSession().getAttribute("employeeList");
}
%>
<table border="1">
	<tr>
	<th>EMPID</th>
	<th>EMPNAME</th>
	<th>EMP EmailID</th>
	<th>Salary</th>
	</tr>
<% for(Object o: arrayList){
	Employee emp=(Employee)o;
	%>
	
	<tr><td><%=emp.getEmployeeID()%></td>
		<td><%=emp.getEmployeeName()%></td>
		<td><%=emp.getEmailID()%></td>
		<td><%=emp.getSalary()%></td>
	</tr>
	
<%} %>
</table>

</body>
</html>