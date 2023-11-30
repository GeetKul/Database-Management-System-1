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
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/OrderOfWorkDAO")
public class OrderOfWorkDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public OrderOfWorkDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
	 protected void connect_func() throws SQLException {
	        //uses default connection to the database
	        if (connect == null || connect.isClosed()) {
	            try {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	            } catch (ClassNotFoundException e) {
	                throw new SQLException(e);
	            }
	            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Project1?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=john1234");
	            System.out.println(connect);
	        }
	    }
	 	
		    
	 public boolean database_login(String email, String password) throws SQLException {
		    try {
		        connect_func("root", "pass1234");
		        String sql = "SELECT * FROM OrderOfWork WHERE OrderID = ?";
		        preparedStatement = connect.prepareStatement(sql);
		        preparedStatement.setString(1, email);
		        ResultSet rs = preparedStatement.executeQuery();
		        return rs.next();
		    } catch (SQLException e) {
		        e.printStackTrace(); // Log the exception for debugging purposes
		        return false;
		    } finally {
		        disconnect();
		    }
		}

    
  //connect to the database
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
                    .getConnection("jdbc:mysql://127.0.0.1:3306/project1?"
                            + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
 // Method to retrieve a list of orders
    public List<OrderOfWork> listAllOrders() throws SQLException {
        List<OrderOfWork> listOrders = new ArrayList<>();
        String sql = "SELECT * FROM OrderOfWork";
        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int orderID = resultSet.getInt("OrderID");
                int requestID = resultSet.getInt("RequestID");
                int clientID = resultSet.getInt("ClientID");
                String startDate = resultSet.getString("StartDate");
                String endDate = resultSet.getString("EndDate");
                int numberOfTreesCut = resultSet.getInt("NumberOfTreesCut");
                String dateOfCut = resultSet.getString("DateOfCut");
                String contractorID = resultSet.getString("ContractorID");

                String statusString = resultSet.getString("Status");
                OrderOfWork.Status status = null;
                if (statusString != null) {
                    try {
                        // Convert to proper enum format (e.g., "In Progress" to InProgress)
                        String enumFormat = statusString.replace(" ", "");
                        status = OrderOfWork.Status.valueOf(enumFormat);
                    } catch (IllegalArgumentException e) {
                        // Log or handle the exception as needed
                        e.printStackTrace();
                    }
                }
                System.out.println("Converted Status: " + status);

                OrderOfWork order = new OrderOfWork(orderID, requestID, clientID, startDate, endDate, status, numberOfTreesCut, dateOfCut, contractorID);
                listOrders.add(order);
            }
        } finally {
            disconnect();
        }

        return listOrders;
    }

    



    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(OrderOfWork order) throws SQLException {
        connect_func();
        String sql = "INSERT INTO OrderOfWork (RequestID, ClientID, StartDate, EndDate, Status, NumberOfTreesCut, DateOfCut, ContractorID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, order.getRequestID());
        preparedStatement.setInt(2, order.getClientID());
        preparedStatement.setString(3, order.getStartDate());
        preparedStatement.setString(4, order.getEndDate());
        preparedStatement.setString(5, order.getStatus().name());
        preparedStatement.setInt(6, order.getNumberOfTreesCut());
        preparedStatement.setString(7, order.getDateOfCut());
        preparedStatement.setString(8, order.getContractorID());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }

    // Method to delete an order by RequestID
    public boolean delete(int OrderID) throws SQLException {
        String sql = "DELETE FROM OrderOfWork WHERE OrderID = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, OrderID);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();
        return rowDeleted;
    }
     
    public boolean updateOrderOfWork(OrderOfWork order) throws SQLException {
    	String sql = "UPDATE OrderOfWork SET ClientID=?, StartDate=?, EndDate=?, Status=?, NumberOfTreesCut=?, DateOfCut=?, ContractorID=? WHERE OrderID=?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, order.getClientID());
        preparedStatement.setString(2, order.getStartDate());
        preparedStatement.setString(3, order.getEndDate());
        preparedStatement.setString(4, order.getStatus().name()); // Assuming Status is an enum
        preparedStatement.setInt(5, order.getNumberOfTreesCut());
        preparedStatement.setString(6, order.getDateOfCut());
        preparedStatement.setString(7, order.getContractorID());
        preparedStatement.setInt(8, order.getOrderID());

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();
        return rowUpdated;
    }

    
    public OrderOfWork getOrderOfWork(int orderID) throws SQLException {
        OrderOfWork order = null;
        String sql = "SELECT * FROM OrderOfWork WHERE OrderID = ?";

        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, orderID);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
        	int requestID = resultSet.getInt("RequestID");
            int clientID = resultSet.getInt("ClientID");
            String startDate = resultSet.getString("StartDate");
            String endDate = resultSet.getString("EndDate");

            // Assuming Status is an enum
           
            String statusString = resultSet.getString("Status");
            OrderOfWork.Status status = OrderOfWork.Status.valueOf(statusString);

            int numberOfTreesCut = resultSet.getInt("NumberOfTreesCut");
            String dateOfCut = resultSet.getString("DateOfCut");
            String contractorID = resultSet.getString("ContractorID");

            order = new OrderOfWork(orderID, requestID, clientID, startDate, endDate, status, numberOfTreesCut, dateOfCut, contractorID);
        }

        resultSet.close();
        preparedStatement.close();
        disconnect(); // Close the connection after use

        return order;
    }


    
    public boolean checkOrderID(int OrderID) throws SQLException {
        boolean checks = false;
        String sql = "SELECT * FROM OrderOfWork WHERE OrderID = ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, OrderID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            checks = true;
        }

        resultSet.close();
        preparedStatement.close();
        return checks;
    }

    
    
    
    
    public boolean isValid(int OrderID, int ClientID) throws SQLException {
        String sql = "SELECT * FROM OrderOfWork WHERE OrderID = ? AND ClientID = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, OrderID);
        preparedStatement.setInt(2, ClientID);

        ResultSet resultSet = preparedStatement.executeQuery();

        boolean isValid = resultSet.next(); // Check if a matching record is found

        resultSet.close();
        preparedStatement.close();
        return isValid;
    }

    
    public void init() throws SQLException, FileNotFoundException, IOException {
        connect_func();
        statement = (Statement) connect.createStatement();

        String[] INITIAL = {
            "drop database if exists project1;",
            "create database Project1;",
            "use Project1;",
            "drop table if exists OrderOfWork;",
            "CREATE TABLE if not exists OrderOfWork ( " +
                "OrderID INT AUTO_INCREMENT PRIMARY KEY, " +
                "RequestID INT NOT NULL, " +
                "ClientID INT NOT NULL, " +
                "StartDate DATE," +
                "EndDate DATE," +
                "Status ENUM('Started', 'In Progress', 'Pending', 'Completed')," +
                "NumberOfTreesCut INT," +
                "DateOfCut DATE," +
                "ContractorID VARCHAR(255)," +
                "FOREIGN KEY (RequestID) REFERENCES QuoteRequest (RequestID) ON DELETE CASCADE, " +
                "FOREIGN KEY (ClientID) REFERENCES Client (ClientID) ON DELETE CASCADE, " +
                "PRIMARY KEY (OrderID)" +
            ");",
        };

        String[] TUPLES = {
            "(1, 1, '2022-11-10', '2022-11-15', 'Started', 10, '2022-11-12', '1')," +
            "(2, 2, '2022-11-12', '2022-11-20', 'In Progress', 15, '2022-11-18', '1')," +
            "(3, 3, '2022-12-10', '2022-12-15', 'Completed', 20, '2022-12-12', '1')," +
            "(4, 4, '2022-12-01', '2022-12-10', 'Pending', 8, '2022-12-05', '1')," +
            "(5, 5, '2023-08-12', '2023-08-20', 'Started', 12, '2023-08-15', '1')," +
            "(6, 6, '2023-06-02', '2023-06-10', 'In Progress', 18, '2023-06-05', '1')," +
            "(7, 7, '2023-06-04', '2023-06-12', 'Completed', 25, '2023-06-08', '1')," +
            "(8, 8, '2023-03-12', '2023-03-20', 'Pending', 14, '2023-03-15', '1')," +
            "(9, 9, '2023-03-20', '2023-03-28', 'Started', 22, '2023-03-25', '1');"
        };

        // Loop to execute the SQL statements
        for (int i = 0; i < INITIAL.length; i++)
            statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)
            statement.execute("INSERT INTO OrderOfWork (RequestID, ClientID, StartDate, EndDate, Status, NumberOfTreesCut, DateOfCut, ContractorID) VALUES " + TUPLES[i]);
        disconnect();
    }
	

}
