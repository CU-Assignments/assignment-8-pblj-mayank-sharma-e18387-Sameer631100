<%@ page import="java.sql.*" %>
<%
    ResultSet rs = (ResultSet) request.getAttribute("result");
%>
<h2>Employee Results</h2>
<table border="1">
    <tr><th>ID</th><th>Name</th><th>Department</th><th>Email</th></tr>
<%
    while (rs != null && rs.next()) {
%>
    <tr>
        <td><%= rs.getInt("id") %></td>
        <td><%= rs.getString("name") %></td>
        <td><%= rs.getString("department") %></td>
        <td><%= rs.getString("email") %></td>
    </tr>
<%
    }
%>
</table>
