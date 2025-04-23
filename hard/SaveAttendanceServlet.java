import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/SaveAttendanceServlet")
public class SaveAttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_portal", "root", "your_password")) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM students");

            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO attendance (student_id, date, status) VALUES (?, CURDATE(), ?)");

            while (rs.next()) {
                int id = rs.getInt("id");
                String status = request.getParameter("status_" + id);
                ps.setInt(1, id);
                ps.setString(2, status);
                ps.executeUpdate();
            }

            out.println("<h2>Attendance saved successfully!</h2>");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error saving attendance.</h3>");
        }
    }
}
