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
@WebServlet("/QuoteRequestDAO")
public class QuoteRequestDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public QuoteRequestDAO(){}
	
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
		        String sql = "SELECT * FROM QuoteRequest WHERE RequestID = ?";
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
    
    public List<QuoteRequest> listAllRequests() throws SQLException {
        List<QuoteRequest> listRequests = new ArrayList<>();
        String sql = "SELECT * FROM QuoteRequest";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int requestID = resultSet.getInt("RequestID");
            int clientID = resultSet.getInt("ClientID");
            String requestDate = resultSet.getString("RequestDate");
            
            String note = resultSet.getString("Note");
            String size = resultSet.getString("Size");
            double height = resultSet.getDouble("Height");
            String location = resultSet.getString("Location");
            double proximityToHouse = resultSet.getDouble("ProximityToHouse");
            int numberOfTrees = resultSet.getInt("NumberOfTrees");
            String statusString = resultSet.getString("Status");
            QuoteRequest.Status status = QuoteRequest.Status.valueOf(statusString);                 	
                                  

            QuoteRequest request = new QuoteRequest(requestID, clientID, requestDate,numberOfTrees , note, size, height, location, proximityToHouse, status);
            listRequests.add(request);
        }

        resultSet.close();
        disconnect();
        return listRequests;
    }

    public List<QuoteRequest> getAgreedQuotesForOneTree() throws SQLException {
        List<QuoteRequest> agreedQuotes = new ArrayList<>();
        connect_func(); // Connect to the database

        try {
            String sql = "SELECT * " +
                         "FROM QuoteRequest " +
                         "WHERE Status = 'Accepted' AND NumberOfTrees = 1";

            preparedStatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int requestID = resultSet.getInt("RequestID");
                int clientID = resultSet.getInt("ClientID");
                String requestDate = resultSet.getString("RequestDate");
                int numberOfTrees = resultSet.getInt("NumberOfTrees");
                String note = resultSet.getString("Note");
                String size = resultSet.getString("Size");
                double height = resultSet.getDouble("Height");
                String location = resultSet.getString("Location");
                float proximityToHouse = resultSet.getFloat("ProximityToHouse");
                String statusString = resultSet.getString("Status");
                QuoteRequest.Status status = QuoteRequest.Status.valueOf(statusString);  

                QuoteRequest quote = new QuoteRequest(requestID, clientID, requestDate, numberOfTrees,
                        note, size, height, location, proximityToHouse, status);

                agreedQuotes.add(quote);
            }
        } finally {
            disconnect(); // Close the database connection
        }

        return agreedQuotes;
    }


    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public List<QuoteRequest> getHighestTreesCutByContractor() throws SQLException {
        List<QuoteRequest> highestTrees = new ArrayList<>();
        connect_func(); // Connect to the database

        try {
            String sql =
           " SELECT QR.RequestID, QR.ClientID, QR.RequestDate, QR.NumberOfTrees, QR.Size, QR.Height " +
           " FROM QuoteRequest QR " +
           " JOIN OrderOfWork OW ON QR.RequestID = OW.RequestID " +
           " WHERE OW.ContractorID = 1 " +
           " ORDER BY QR.Height DESC " +
           " LIMIT 1 ";

            preparedStatement = connect.prepareStatement(sql);
            

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int requestID = resultSet.getInt("RequestID");
                int clientID = resultSet.getInt("ClientID");
                String requestDate = resultSet.getString("RequestDate");
                int numberOfTrees = resultSet.getInt("NumberOfTrees");
                String size = resultSet.getString("Size");
                double height = resultSet.getDouble("Height");
               

                QuoteRequest highestTree = new QuoteRequest(requestID, clientID, requestDate, numberOfTrees, size, height);
                highestTrees.add(highestTree);
            }
        } finally {
            disconnect(); // Close the database connection
        }

        return highestTrees;
    }


    public void insert(QuoteRequest request) throws SQLException {
        connect_func("root", "pass1234");
        String sql = "INSERT INTO QuoteRequest (ClientID, RequestDate, NumberOfTrees, Status, Note, Size, Height, Location, ProximityToHouse) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        
        preparedStatement.setInt(1, request.getClientID());
        preparedStatement.setString(2, request.getRequestDate());
        preparedStatement.setInt(3, request.getNumberOfTrees()); // Assuming there is a getNumberOfTrees() method in QuoteRequest
        preparedStatement.setString(4, request.getStatus().name()); // Assuming getStatus() returns an enum value
        preparedStatement.setString(5, request.getNote());
        preparedStatement.setString(6, request.getSize());
        preparedStatement.setDouble(7, request.getHeight());
        preparedStatement.setString(8, request.getLocation());
        preparedStatement.setDouble(9, request.getProximityToHouse());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }


    public boolean delete(int RequestID) throws SQLException {
        String sql = "DELETE FROM QuoteRequest WHERE RequestID = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, RequestID);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;
    }

     
    public boolean updateQuoteRequest(QuoteRequest quoteRequest) throws SQLException {
        String sql = "UPDATE QuoteRequest SET ClientID=?, RequestDate=?, NumberOfTrees=?, Status=?, Note=?, Size=?, Height=?, Location=?, ProximityToHouse=? WHERE RequestID=?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteRequest.getClientID());       
        preparedStatement.setString(2, quoteRequest.getRequestDate());
        preparedStatement.setInt(3, quoteRequest.getNumberOfTrees()); 
        preparedStatement.setString(4, quoteRequest.getStatus().name()); 
        preparedStatement.setString(5, quoteRequest.getNote());
        preparedStatement.setString(6, quoteRequest.getSize());
        preparedStatement.setDouble(7, quoteRequest.getHeight());
        preparedStatement.setString(8, quoteRequest.getLocation());
        preparedStatement.setDouble(9, quoteRequest.getProximityToHouse());
        preparedStatement.setInt(10, quoteRequest.getRequestID());

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();
        return rowUpdated;
    }


    
    public QuoteRequest getQuoteRequest(int requestID) throws SQLException {
        QuoteRequest quoteRequest = null;
        String sql = "SELECT * FROM QuoteRequest WHERE RequestID = ?";

        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, requestID);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int clientID = resultSet.getInt("ClientID");            
            String requestDate = resultSet.getString("RequestDate");
            int numberOfTrees = resultSet.getInt("NumberOfTrees");                     

            String note = resultSet.getString("Note");
            String size = resultSet.getString("Size");
            double height = resultSet.getDouble("Height");
            String location = resultSet.getString("Location");
            double proximityToHouse = resultSet.getDouble("ProximityToHouse");
            String statusString = resultSet.getString("Status"); 

            quoteRequest = new QuoteRequest(requestID, clientID, requestDate, numberOfTrees, note, size, height, location, proximityToHouse, QuoteRequest.Status.valueOf(statusString));
        }

        resultSet.close();
        preparedStatement.close();
        disconnect(); // Close the connection after use

        return quoteRequest;
    }


    
    public boolean checkRequestID(int RequestID) throws SQLException {
        boolean checks = false;
        String sql = "SELECT * FROM QuoteRequest WHERE RequestID = ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, RequestID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            checks = true;
        }

        resultSet.close();
        preparedStatement.close();
        return checks;
    }

    
    
    
    
    public boolean isValid(int RequestID, int ClientID) throws SQLException {
        String sql = "SELECT * FROM QuoteRequest WHERE RequestID = ? AND ClientID = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, RequestID);
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
            "drop table if exists QuoteRequest;",
            "CREATE TABLE if not exists QuoteRequest ( " +
            "RequestID INT AUTO_INCREMENT PRIMARY KEY, " +
            "ClientID INT NOT NULL, " +
            "FOREIGN KEY (ClientID) REFERENCES Client (ClientID) ON DELETE CASCADE, " +
            "RequestDate DATE," +
            "NumberOfTrees INT," +  // Add the new column
            "Status ENUM('Pending', 'Open', 'Accepted') DEFAULT 'Pending'," +
            "Note VARCHAR(200)," +
            "Size VARCHAR(10)," +
            "Height DECIMAL(3, 1)," +
            "Location VARCHAR(100)," +
            "ProximityToHouse FLOAT(20)," +
            "UNIQUE (RequestID)" +
            ");"
        };

        String[] TUPLES = {
        	    "(1, '2022-11-10', 3, 'Need somefjj', 'Large', 12.5, 'backyard', 5.3, 'Pending')," +
        	    "(2, '2022-11-12', 2, 'tata', 'Medium', 9.2, 'frontyard', 2.7, 'Pending')," +
        	    "(3, '2022-12-10', 1, 'brat', 'Small', 7.8, 'frontyard', 1.5, 'Pending')," +
        	    "(4, '2022-12-01', 4, 'sestra', 'Medium', 8.6, 'backyard', 3.2, 'Pending')," +
        	    "(5, '2023-08-12', 5, 'baka', 'Large', 11.4, 'backyard', 4.7, 'Pending')," +
        	    "(6, '2023-06-02', 2, 'ides', 'Small', 6.9, 'frontyard', 1.8, 'Pending')," +
        	    "(7, '2023-06-04', 1, 'lolas', 'Large', 10.1, 'frontyard', 6.5, 'Pending')," +
        	    "(8, '2023-03-12', 4, 'samo ne tu', 'Medium', 9.8, 'frontyard', 2.1, 'Pending')," +
        	    "(9, '2023-03-20', 5, 'Lily Road', 'Large', 12.0, 'backyard', 5.7, 'Pending');"
        	};


        // Loop to execute the SQL statements
        for (int i = 0; i < INITIAL.length; i++)
            statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)
            statement.execute("INSERT INTO QuoteRequest (ClientID, RequestDate, NumberOfTrees, Note, Size, Height, Location, ProximityToHouse, Status) VALUES " + TUPLES[i]);
        disconnect();
    }

    
    
    
    
    
	
	

}
