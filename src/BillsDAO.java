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

@WebServlet("/BillsDAO")
public class BillsDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public BillsDAO() {
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
			String sql = "select * from Bills where BillID = ?";
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

	public List<Bills> listAllBills() throws SQLException {
	    List<Bills> listBills = new ArrayList<>();
	    String sql = "SELECT * FROM Bills";
	    connect_func();
	    statement = (Statement) connect.createStatement();
	    ResultSet resultSet = statement.executeQuery(sql);

	    while (resultSet.next()) {
	        int billId = resultSet.getInt("BillID");
	        int orderID = resultSet.getInt("OrderID");
	        int contractorID = resultSet.getInt("ContractorID");
	        String generatedDate = resultSet.getString("GeneratedDate");
	        double amount = resultSet.getDouble("Amount");

	        // Assuming Status is stored as a String in the database
	        String billStatusString = resultSet.getString("BillStatus");
	        Bills.BillStatus billStatus = Bills.BillStatus.valueOf(billStatusString);

	        String paymentStatusString = resultSet.getString("PaymentStatus");
	        Bills.PaymentStatus paymentStatus = Bills.PaymentStatus.valueOf(paymentStatusString);

	        Bills bills = new Bills(billId, amount, generatedDate, billStatus, paymentStatus, orderID, contractorID);
	        listBills.add(bills);
	    }

	    resultSet.close();
	    disconnect();
	    return listBills;
	}

	// In BillDAO.java

	public List<Bills> getOverdueBillsWithDetails() throws SQLException {
	    List<Bills> overdueBills = new ArrayList<>();
	    connect_func(); // Connect to the database

	    try {
	    	String sql = "SELECT B.BillID, B.GeneratedDate, B.BillStatus, B.PaymentStatus, B.OrderID, OW.ClientID " +
	                "FROM Bills B " +
	                "JOIN OrderOfWork OW ON B.OrderID = OW.OrderID " +
	                "WHERE B.PaymentStatus = 'Unpaid' AND DATEDIFF(NOW(), B.GeneratedDate) > 7";



	        preparedStatement = connect.prepareStatement(sql);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int billId = resultSet.getInt("BillID");
	            // Add other fields as needed
	            String generatedDate = resultSet.getString("GeneratedDate");
	            String billStatusString = resultSet.getString("BillStatus");
		        Bills.BillStatus billStatus = Bills.BillStatus.valueOf(billStatusString);

		        String paymentStatusString = resultSet.getString("PaymentStatus");
		        Bills.PaymentStatus paymentStatus = Bills.PaymentStatus.valueOf(paymentStatusString);
	           
	            int orderID = resultSet.getInt("OrderID");
	            int clientID = resultSet.getInt("ClientID");

	            Bills bill = new Bills (billId, generatedDate, billStatus, paymentStatus, orderID, clientID);
	            overdueBills.add(bill);
	        }
	    } finally {
	        disconnect(); // Close the database connection
	    }

	    return overdueBills;
	}

	
	private void disconnect() {
		// TODO Auto-generated method stub

	}

	public void insert(Bills bills) throws SQLException {
		connect_func("root", "pass1234");
		String sql = "INSERT INTO Bills ( BillId, Amount, GeneratedDate, BillStatus, PaymentStatus, OrderID, ContractorID) VALUES (?, ?, ?, ?, ?, ?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);

		preparedStatement.setInt(1, bills.getBillId());
		preparedStatement.setDouble(2, bills.getAmount());
		preparedStatement.setString(3, bills.getGeneratedDate());
		preparedStatement.setString(4, bills.getBillStatus().toString());
		preparedStatement.setString(5, bills.getPaymentStatus().toString());
		preparedStatement.setInt(6, bills.getOrderID());
		preparedStatement.setInt(7, bills.getContractorID());

		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	public boolean deleteById(int BillID) throws SQLException {
		String sql = "DELETE FROM Bills WHERE BillID = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, BillID);

		boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean update(Bills bills) throws SQLException {
		String sql = "UPDATE Bills SET BillID=?, Amount=?, GeneratedDate=?, billStatus=?, paymentStatus=?, OrderID=?,ContractorID=? WHERE BillID=?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);

		preparedStatement.setInt(1, bills.getBillId());
		preparedStatement.setDouble(2, bills.getAmount());
		preparedStatement.setString(3, bills.getGeneratedDate());
		preparedStatement.setString(4, bills.getBillStatus().toString());
		preparedStatement.setString(5, bills.getPaymentStatus().toString());
		preparedStatement.setInt(6, bills.getOrderID());
		preparedStatement.setInt(7, bills.getContractorID());

		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		disconnect();
		return rowUpdated;
	}

	public Bills getBill(int BillID) throws SQLException {
		Bills bills = null;
		String sql = "SELECT * FROM Bills WHERE BillID = ?";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, BillID);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			int billId = resultSet.getInt("BillId");
			int orderID = resultSet.getInt("OrderID");
			int contractorID = resultSet.getInt("ContractorID");
			String generatedDate = resultSet.getString("GeneratedDate");
			double amount = resultSet.getDouble("Amount");

			// Assuming Status is stored as a String in the database
			String billStatusString = resultSet.getString("BillStatus");
			Bills.BillStatus billStatus = Bills.BillStatus.valueOf(billStatusString);

			String paymentStatusString = resultSet.getString("PaymentStatus");
			Bills.PaymentStatus paymentStatus = Bills.PaymentStatus.valueOf(paymentStatusString);

			bills = new Bills(billId, amount, generatedDate, Bills.BillStatus.valueOf(billStatusString),
					Bills.PaymentStatus.valueOf(paymentStatusString), orderID, contractorID);

		}

		resultSet.close();
		preparedStatement.close();
		disconnect();

		return bills;
	}

	public boolean checkBillID(int BillID) throws SQLException {
		boolean checks = false;
		String sql = "SELECT * FROM Bills WHERE BillID = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, BillID);
		ResultSet resultSet = preparedStatement.executeQuery();

		System.out.println(checks);

		if (resultSet.next()) {
			checks = true;
		}

		System.out.println(checks);
		return checks;
	}

	public boolean isValid(int BillID, int contractorID) throws SQLException {
		String sql = "SELECT * FROM Bills WHERE BillID = ? AND ContractorID = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, BillID);
		preparedStatement.setInt(2, contractorID);

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
	            "drop table if exists Bills; ",
	            "CREATE TABLE if not exists Bills ( "
	                    + "BillID INT AUTO_INCREMENT PRIMARY KEY, "
	                    + "Amount DECIMAL(10, 2), "
	                    + "GeneratedDate DATE, "
	                    + "BillStatus ENUM('Issued', 'Pending',  'Paid', 'Cancelled'),"
	                    + "PaymentStatus ENUM('Unpaid', 'Paid', 'Processing', 'Overdue','Failed'), "
	                    + "OrderID INT,"
	                    + "ContractorID INT,"
	                    + "FOREIGN KEY (OrderID) REFERENCES OrderOfWork(OrderID) ON DELETE CASCADE,"
	                    + "FOREIGN KEY (ContractorID) REFERENCES Contractor(ContractorID) ON DELETE CASCADE"
	                    + "); "
	    };

	    // Insert initial data
	    String[] TUPLES = {
	            "INSERT INTO Bills ( Amount, GeneratedDate, BillStatus, PaymentStatus, OrderID,ContractorID) "
	                    + "VALUES ( 1089, '2022-12-29','Issued', 'Unpaid' ,1,1), "
	                    + "    ( 1104, '2022-12-29', 'Issued', 'Overdue', 2, 1),"
	                    + "    ( 1190, '2023-01-15', 'Pending', 'Overdue', 3, 1),"
	                    + "    ( 1200, '2023-01-20', 'Issued', 'Paid', 4, 1),"
	                    + "    ( 985, '2023-02-05', 'Pending', 'Unpaid', 5, 1),"
	                    + "    ( 899, '2023-02-10', 'Issued', 'Paid', 6, 1),"
	                    + "    ( 955, '2023-03-01', 'Issued', 'Unpaid', 7, 1),"
	                    + "    ( 1125, '2023-03-15', 'Pending', 'Unpaid', 8, 1),"
	                    + "    ( 1005, '2023-04-02', 'Issued', 'Paid', 9, 1),"
	                    + "    ( 1098, '2023-05-15', 'Issued', 'Unpaid', 10, 1),"
	                    + "    ( 1082, '2023-06-01', 'Issued', 'Paid', 11, 1),"
	                    + "    ( 1105, '2023-07-10', 'Issued', 'Unpaid', 12, 1),"
	                    + "    ( 1129, '2023-08-05', 'Issued', 'Paid', 13, 1);"
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
