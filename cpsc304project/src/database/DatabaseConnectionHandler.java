package database;

import model.GuestModel;
import model.VenueModel;
import util.PrintablePreparedStatement;

import java.sql.*;
import java.util.ArrayList;

//import ca.ubc.cs304.model.BranchModel;
//import ca.ubc.cs304.model.GuestModel;
//import ca.ubc.cs304.util.PrintablePreparedStatement;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
	// Use this version of the ORACLE_URL if you are running the code off of the server
//	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
	// Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";

	private static int rowCount = 0;

	private Connection connection = null;
	private DatabaseConnectionHandler dbHandler = null;

	private static boolean duplicate = false;

	private static boolean eventExists = false;


	public DatabaseConnectionHandler() {
		try {
			// Load the Oracle JDBC driver
			// Note that the path could change for new drivers
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			connection = DriverManager.getConnection(ORACLE_URL, "ora_mcui03", "a16721946");
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public void deleteBranch(int branchId) {
//		try {
//			String query = "DELETE FROM branch WHERE branch_id = ?";
//			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
//			ps.setInt(1, branchId);

//			int rowCount = ps.executeUpdate();
//			if (rowCount == 0) {
//				System.out.println(WARNING_TAG + " Branch " + branchId + " does not exist!");
//			}

//			connection.commit();
//
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			rollbackConnection();
//		}
	}

	public void updateVenue(String name, String address, int capacity, int eventId) {
		try {
			String query = "UPDATE VENUE SET venue_name=?, venue_address=?, venue_capacity=?, event_id=? WHERE venue_name=?";
			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);

			ps.setString(1, name);
			ps.setString(2, address);
			ps.setInt(3, capacity);
			ps.setInt(4, eventId);
			ps.setString(5, name);



			ps.executeUpdate();
			eventExists = true;
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			eventExists = false;
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public boolean getEventExists() {
		return eventExists;
	}

	public void deleteVenue(String venueName) {
		try {
			String query = "DELETE FROM venue WHERE venue_name = ?";
			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
			ps.setString(1, venueName);

			 rowCount = ps.executeUpdate();
			 if (rowCount == 0) {
			 	System.out.println(WARNING_TAG + " Venue " + venueName + " does not exist!");
			 }


			connection.commit();

			ps.close();



		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public int getRowCount() {
		return rowCount;
	}

	public void insertVenue(VenueModel model) {
		try {
			String query = "INSERT INTO VENUE VALUES (?,?,?,?)";
			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
			ps.setString(1, model.getName());
			ps.setString(2, model.getAddress());
			ps.setInt(3, model.getCapacity());
			ps.setInt(4, model.getId());


			ps.executeUpdate();
			connection.commit();
			duplicate = false;
//			eventExists = true;

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			eventExists = false;
			duplicate = true;
			rollbackConnection();
		}
	}

	public boolean getDuplicate() {
		return duplicate;
	}

//	public BranchModel[] getBranchInfo() {
//		ArrayList<BranchModel> result = new ArrayList<BranchModel>();
//
//		try {
//			String query = "SELECT * FROM branch";
//			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
//			ResultSet rs = ps.executeQuery();
//
//			while(rs.next()) {
//				BranchModel model = new BranchModel(rs.getString("branch_addr"),
//						rs.getString("branch_city"),
//						rs.getInt("branch_id"),
//						rs.getString("branch_name"),
//						rs.getInt("branch_phone"));
//				result.add(model);
//			}
//
//			rs.close();
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		return result.toArray(new BranchModel[result.size()]);
//	}

	public void updateBranch(int id, String name) {
//		try {
//			String query = "UPDATE branch SET branch_name = ? WHERE branch_id = ?";
//			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
//			ps.setString(1, name);
//			ps.setInt(2, id);
//
//			int rowCount = ps.executeUpdate();
//			if (rowCount == 0) {
//				System.out.println(WARNING_TAG + " Branch " + id + " does not exist!");
//			}
//
//			connection.commit();
//
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			rollbackConnection();
//		}
	}

	public void updateGuest(String name, int ticketNumber, int phoneNumber) {
		try {
			String query = "UPDATE guest SET phone_number = ? WHERE name = ? AND ticket_number = ?";
			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
			ps.setInt(1, phoneNumber);
			ps.setString(2, name);
			ps.setInt(3, ticketNumber);

			// int rowCount = ps.executeUpdate();
			// if (rowCount == 0) {
			// 	System.out.println(WARNING_TAG + " Branch " + id + " does not exist!");
			// }

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	private void rollbackConnection() {
		try  {
			connection.rollback();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public void databaseSetup() {
//		dropGuestTableIfExists();

		try {
			String query = "CREATE TABLE VENUE (venue_name varchar2(20) not null, venue_address varchar2(20), venue_capacity INTEGER, venue_id INTEGER, PRIMARY KEY (venue_name))";
			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("EXCEPTION " + e.getMessage());
		}

		GuestModel guest1 = new GuestModel("Jason", "zhujason4@gmail.com", 444, 1234567);

		GuestModel guest2 = new GuestModel("Emma", "emmazhan03@gmail.com", 2, 2234567);

	}

	private void dropGuestTableIfExists() {
		try {
			String query = "select table_name from user_tables";
			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				if(rs.getString(1).toLowerCase().equals("branch")) {
					ps.execute("DROP TABLE guest");
					break;
				}
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
}
