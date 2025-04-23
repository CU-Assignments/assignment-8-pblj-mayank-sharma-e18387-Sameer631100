<%@ page import="java.sql.*" %>
<%
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_portal", "root", "your_password");
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM students");
%>
<!DOCTYPE html>
<html>
<head><title>Attendance Form</title></head>
<body>
    <h2>Mark Attendance</h2>
    <form action="SaveAttendanceServlet" method="post">
        <table border="1">
            <tr><th>ID</th><th>Name</th><th>Status</th></tr>
            <% while(rs.next()) { %>
            <tr>
                <td><%= rs.getInt("id") %></td>
                <td><%= rs.getString("name") %></td>
                <td>
                    <input type="radio" name="status_<%= rs.getInt("id") %>" value="Present" checked>Present
                    <input type="radio" name="status_<%= rs.getInt("id") %>" value="Absent">Absent
                </td>
            </tr>
            <% } %>
        </table>
        <br>
        <input type="submit" value="Submit Attendance">
    </form>
</body>
</html>
