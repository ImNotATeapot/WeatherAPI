import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NewUserQuery")
public class NewUserQuery extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usr = request.getParameter("username");
		String pw = request.getParameter("password");
		String confirm = request.getParameter("confirm");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Profile?user=root&password=patriya7");
			
			ps = conn.prepareStatement("SELECT * FROM User WHERE username=?"); 
			ps.setString(1, usr);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				System.out.println(id + " - " + username + " " + password);//for debugging
				request.setAttribute("case",1); // username already exists
				
				try {
					if(rs != null) {rs.close();}
					if(ps != null) {ps.close();}
					if(conn!=null) {conn.close();}
				} catch (SQLException sqle) {
					System.out.println("sqle closing stuff: " + sqle.getMessage());
				}
				
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Register.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("cnfe: " + cnfe.getMessage());
		}
		
		conn = null;
		ps = null;
		rs = null;
		
		if(!pw.equals(confirm)) {
			request.setAttribute("case",2); //passwords do not match
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Register.jsp");
			rd.forward(request, response);
		}
		
		try {
			request.setAttribute("case",3); //successful 
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Profile?user=root&password=patriya7");
		
			ps = conn.prepareStatement("INSERT INTO User (username, password) VALUES (?, ?)"); 
			ps.setString(1, usr);
			ps.setString(2, pw);
			
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("cnfe: " + cnfe.getMessage());
		} finally { //code that is executed regardless of whether or not an exception happens or is handled

			try {
				if(rs != null) {rs.close();}
				if(ps != null) {ps.close();}
				if(conn!=null) {conn.close();}
			} catch (SQLException sqle) {
				System.out.println("sqle closing stuff: " + sqle.getMessage());
			}
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Home.jsp");
			rd.forward(request, response);
		}
		
	}
}
