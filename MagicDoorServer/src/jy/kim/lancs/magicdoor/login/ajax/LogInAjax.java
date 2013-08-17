package jy.kim.lancs.magicdoor.login.ajax;

import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login/LogInAjax.ajax")
public class LogInAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInAjax() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GET");
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("POST");
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		System.out.println("PROCESS");
		
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out = response.getWriter();
		con = getConnection();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "SELECT user_name, password FROM tbl_users WHERE user_name=? AND password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				out.println("logged");
			} else {
				out.println("failed");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
