import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "your_password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        String all = request.getParameter("all");

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)) {
            PreparedStatement ps;
            if (id != null && !id.isEmpty()) {
                ps = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
                ps.setInt(1, Integer.parseInt(id));
            } else {
                ps = conn.prepareStatement("SELECT * FROM employees");
            }

            ResultSet rs = ps.executeQuery();
            request.setAttribute("result", rs);
            RequestDispatcher rd = request.getRequestDispatcher("employee.jsp");
            rd.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database connection error.");
        }
    }
}
