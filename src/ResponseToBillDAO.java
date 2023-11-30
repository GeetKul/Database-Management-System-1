import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;

import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class Connect
 */

@WebServlet("/ResponseToBillDAO")
public class ResponseToBillDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public ResponseToBillDAO() {
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	protected void connect_func() throws SQLException {
		// uses default connection to the database
		if (connect == null || connect.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			connect = (Connection) DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/Project1?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=john1234");
			System.out.println(connect);
		}
	}

	public boolean database_login(String email, String password) throws SQLException {
		try {
			connect_func("root", "pass1234");
			String sql = "select * from ResponseToBill where ResponseToBillID = ?";
			preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println("failed login");
			return false;
		}
	}

	// connect to the database
	public void connect_func(String username, String password) throws SQLException {
		if (connect == null || connect.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project1?"
					+ "useSSL=false&user=" + username + "&password=" + password);
			System.out.println(connect);
		}
	}

	public List<ResponseToBill> listAllResponseToBills() throws SQLException {
		List<ResponseToBill> listResponseToBills = new ArrayList<ResponseToBill>();
		String sql = "SELECT * FROM ResponseToBill";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int responseToBillID = resultSet.getInt("ResponseToBillID");
			int billID = resultSet.getInt("BillID");
			int clientID = resultSet.getInt("ClientID");
			String responseDate = resultSet.getString("ResponseDate");
			String note = resultSet.getString("Note");

			// Assuming Status is stored as a String in the database
			String paymentStatusString = resultSet.getString("PaymentStatus");
			ResponseToBill.PaymentStatus paymentStatus = ResponseToBill.PaymentStatus.valueOf(paymentStatusString);

			ResponseToBill responseToBills = new ResponseToBill(responseToBillID, billID, clientID, responseDate, note,
					paymentStatus);
			listResponseToBills.add(responseToBills);
		}

		resultSet.close();
		disconnect();
		return listResponseToBills;
	}

	private void disconnect() {
		// TODO Auto-generated method stub

	}

	public void insert(ResponseToBill responseToBill) throws SQLException {
		connect_func("root", "pass1234");
		String sql = "INSERT INTO ResponseToBill ( BillID, ClientID, ResponseDate, Note, PaymentStatus) VALUES ( ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);

		
		preparedStatement.setInt(1, responseToBill.getBillID());
		preparedStatement.setInt(2, responseToBill.getClientID());
		preparedStatement.setString(3, responseToBill.getResponseDate());
		preparedStatement.setString(4, responseToBill.getNote());
		preparedStatement.setString(5, responseToBill.getPaymentStatus().toString());

		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	public boolean deleteById(int ResponseToBillID) throws SQLException {
		String sql = "DELETE FROM ResponseToBill WHERE ResponseToBillID = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, ResponseToBillID);

		boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean update(ResponseToBill responseToBill) throws SQLException {
		String sql = "UPDATE ResponseToBill SET ResponseToBillID=?, BillID=?, ClientID=?, ResponseDate=?, Note=?, PaymentStatus=? WHERE ResponseToBillID=?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);

		preparedStatement.setInt(1, responseToBill.getResponseToBillID());
		preparedStatement.setInt(2, responseToBill.getBillID());
		preparedStatement.setInt(3, responseToBill.getClientID());
		preparedStatement.setString(4, responseToBill.getResponseDate());
		preparedStatement.setString(5, responseToBill.getNote());
		preparedStatement.setString(6, responseToBill.getPaymentStatus().toString());

		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		disconnect();
		return rowUpdated;
	}

	public ResponseToBill getResponseToBill(int ResponseToBillID) throws SQLException {
		ResponseToBill responseToBills = null;
		String sql = "SELECT * FROM ResponseToBill WHERE ResponseToBillID = ?";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, ResponseToBillID);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			int responseToBillID = resultSet.getInt("ResponseToBillID");
			int billID = resultSet.getInt("BillID");
			int clientID = resultSet.getInt("ClientID");
			String responseDate = resultSet.getString("ResponseDate");
			String note = resultSet.getString("Note");

			// Assuming Status is stored as a String in the database
			String paymentStatusString = resultSet.getString("PaymentStatus");
			ResponseToBill.PaymentStatus paymentStatus = ResponseToBill.PaymentStatus.valueOf(paymentStatusString);

			responseToBills = new ResponseToBill(responseToBillID, billID, clientID, responseDate, note,
					ResponseToBill.PaymentStatus.valueOf(paymentStatusString));

		}

		resultSet.close();
		preparedStatement.close();
		disconnect();

		return responseToBills;
	}

	public boolean checkBillID(int ResponseToBillID) throws SQLException {
		boolean checks = false;
		String sql = "SELECT * FROM ResponseToBill WHERE ResponseToBillID = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, ResponseToBillID);
		ResultSet resultSet = preparedStatement.executeQuery();

		System.out.println(checks);

		if (resultSet.next()) {
			checks = true;
		}

		System.out.println(checks);
		return checks;
	}

	public boolean isValid(int BillID, int ResponseToBillID) throws SQLException {
		String sql = "SELECT * FROM ResponseToBill WHERE BillID = ? AND ResponseToBillID = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, BillID);
		preparedStatement.setInt(2, ResponseToBillID);

		ResultSet resultSet = preparedStatement.executeQuery();
		boolean isValid = resultSet.next(); // Check if a matching record is found

		resultSet.close();
		preparedStatement.close();
		disconnect();

		return isValid;
	}

	public void init() throws SQLException, FileNotFoundException, IOException {
	    connect_func();
	    statement = (Statement) connect.createStatement();

	    String[] INITIAL = {
	        "drop database if exists project1; ",
	        "create database Project1; ",
	        "use Project1; ",
	        "drop table if exists ResponseToBill; ",
	        "CREATE TABLE if not exists ResponseToBill ( "
	                + "ResponseToBillID INT AUTO_INCREMENT PRIMARY KEY, "
	                + "BillID INT, "
	                + "ClientID INT, "
	                + "ResponseDate DATE,"
	                + "Note VARCHAR(50), "
	                + "PaymentStatus ENUM('Paid', 'Unpaid'),"
	                + "FOREIGN KEY (BillID) REFERENCES Bills(BillID) ON DELETE CASCADE,"
	                + "FOREIGN KEY (ClientID) REFERENCES Client(ClientID) ON DELETE CASCADE"
	                + "); "
	    };

	    // Insert initial data
	    String[] TUPLES = {
	        "INSERT INTO ResponseToBill ( BillID, ClientID, ResponseDate, Note, PaymentStatus) "
	                + "VALUES (1,1,'2023-04-29','installment 2', 'Unpaid'),"
	        		+ "( 2, 2,'2023-06-19','will pay soon', 'Paid'), "
	                + "( 3, 3,'2023-05-13','will pay soon', 'Unpaid'), "
	                + "( 4, 4,'2023-06-18','will pay soon', 'Paid'), "
	                + "( 5, 5,'2023-07-17','pending payment', 'Unpaid'), "
	                + "( 6, 6,'2023-08-29','paid in advance', 'Paid'), "
	                + "( 7, 7,'2023-09-12','payment due', 'Unpaid'), "
	                + "( 8, 8,'2023-10-24','completed payment', 'Paid'), "
	                + "( 9, 9,'2023-11-09','late payment', 'Unpaid'), "
	                + "(10,10,'2023-12-29','early payment', 'Paid'), "
	                + "(11,11,'2023-01-29','partial payment', 'Unpaid'), "
	                + "(12,12,'2023-06-02','full payment', 'Paid'), "
	                + "(13,13,'2023-03-29','installment 1', 'Unpaid'); "
	               
	    };

	    // Execute statements

	    // For loop to execute these SQL statements
	    for (int i = 0; i < INITIAL.length; i++) {
	        statement.execute(INITIAL[i]);
	    }

	    for (int i = 0; i < TUPLES.length; i++) {
	        statement.execute(TUPLES[i]);
	    }

	    disconnect();
	}
}
