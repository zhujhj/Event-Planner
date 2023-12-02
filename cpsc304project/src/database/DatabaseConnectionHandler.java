package database;

import model.EventModel;
import model.GuestModel;
import model.VenueModel;
import util.PrintablePreparedStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static boolean selectable = true;

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

	// returns the average number of venues used by all events
	public int averageVenueCapacity() {
		int result = -1;

		try {
			// count the number of venues for each event
			String countQuery = "SELECT event_id, COUNT(venue_name) AS venue_count " +
					"FROM VENUE " +
					"GROUP BY event_id";

			PreparedStatement countPs = connection.prepareStatement(countQuery);
			ResultSet countRs = countPs.executeQuery();

//			 get the average venue count
			String avgQuery = "SELECT AVG(venue_count) AS avg_venue_count " +
					"FROM (" + countQuery + ") AS event_venue_count";

//			String avgQuery = countQuery;

			PreparedStatement avgPs = connection.prepareStatement(avgQuery);
			ResultSet avgRs = avgPs.executeQuery();

			if (avgRs.next()) {
				result = avgRs.getInt("avg_venue_count");
			}

			countRs.close();
			countPs.close();
			avgRs.close();
			avgPs.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}


//	public List<List> projectVenue(List<String> columns) {
//
//		List<List> columnLists = null;
//		try {
//			String columnsString = columns.toString().substring(1, columns.toString().length() - 1);
//			System.out.println(columnsString);
//			String query = "SELECT " + columnsString + " FROM VENUE";
////			String query = "SELECT * FROM VENUE";
//			System.out.println(query);
//			PreparedStatement ps = connection.prepareStatement(query);
////            ps.setString(1, venueName);
//
//			ResultSet rs = ps.executeQuery();
//
//
////			List<VenueModel> venues = new ArrayList<>();
//
//			List<String> venueNames = new ArrayList<>();
//			List<String> venueAddresses = new ArrayList<>();
//			List<Integer> venueCapacities = new ArrayList<>();
//			List<Integer> venueIDs = new ArrayList<>();
//			columnLists = new ArrayList<>();
//
//			do {
//				if (columnsString.contains("venue_name")) {
//					venueNames.add(rs.getString("venue_name"));
//				}
//				if (columnsString.contains("venue_address")) {
//					venueAddresses.add(rs.getString("venue_address"));
//				}
//				if (columnsString.contains("venue_capacity")) {
//					venueCapacities.add(rs.getInt("venue_capacity"));
//				}
//				if (columnsString.contains("event_id")) {
//					venueIDs.add(rs.getInt("event_id"));
//				}
//			} while (rs.next());
//
//			columnLists.add(venueNames);
//			columnLists.add(venueAddresses);
//			columnLists.add(venueCapacities);
//			columnLists.add(venueIDs);
//
//
//			rs.close();
//			ps.close();
////            model = new VenueModel(name, address, capacity, eventId);
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			e.printStackTrace();
//		}
//		return columnLists;
//	}

	public String aggregateVenueTotalEventMaxAttendance() {
		Map<Integer, Integer> ret = new HashMap<>();
		String returnString = "";

		try {
			String query = "SELECT event_id, SUM(venue_capacity) AS total_capacity " +
					"FROM VENUE " +
					"GROUP BY event_id";

			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int eventId = rs.getInt("event_id");
				int totalCapacity = rs.getInt("total_capacity");

				System.out.println("Event ID: " + eventId + ", Total Capacity: " + totalCapacity + "\n");
				returnString = returnString.concat("Event ID: " + eventId + ", Total Capacity: " + totalCapacity + "\n");
				ret.put(eventId, totalCapacity);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return returnString;
	}

	// Finds all events where the total capacity from all venues is greater than the specified amount
	public String aggregateVenueCapacityByEventHaving(int minTotalCapacity) {
		Map<Integer, Integer> aggregatedDataMap = new HashMap<>();
		String returnString = "";

		try {
			String query = "SELECT event_id, SUM(venue_capacity) AS total_capacity " +
					"FROM VENUE " +
					"GROUP BY event_id " +
					"HAVING SUM(venue_capacity) >= ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, minTotalCapacity);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int eventId = rs.getInt("event_id");
				int totalCapacity = rs.getInt("total_capacity");

				aggregatedDataMap.put(eventId, totalCapacity);
				returnString = returnString.concat("Event ID: " + eventId + " , Total Capacity: " + totalCapacity);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		System.out.println(aggregatedDataMap);
		return returnString;
	}

	// Finds the projection
	public List<List> projectVenue(List<String> columns) {

		VenueModel model = null;
		List<List> columnLists = null;
		try {
			String columnsString = columns.toString().substring(1, columns.toString().length() - 1);
			System.out.println(columnsString);
			String query = "SELECT " + columnsString + " FROM VENUE";
//			String query = "SELECT * FROM VENUE";
			System.out.println(query);
			PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, venueName);

			ResultSet rs = ps.executeQuery();

			System.out.println(rs.next());

			String name = null;
			String address = null;
			int capacity = 0;
			int eventId = 0;

//			List<VenueModel> venues = new ArrayList<>();

			List<String> venueNames = new ArrayList<>();
			List<String> venueAddresses = new ArrayList<>();
			List<Integer> venueCapacities = new ArrayList<>();
			List<Integer> venueIDs = new ArrayList<>();
			columnLists = new ArrayList<>();

			do {
				if (columnsString.contains("venue_name")) {
					venueNames.add(rs.getString("venue_name"));
				}
				if (columnsString.contains("venue_address")) {
					venueAddresses.add(rs.getString("venue_address"));
				}
				if (columnsString.contains("venue_capacity")) {
					venueCapacities.add(rs.getInt("venue_capacity"));
				}
				if (columnsString.contains("event_id")) {
					venueIDs.add(rs.getInt("event_id"));
				}
			} while (rs.next());

			columnLists.add(venueNames);
			columnLists.add(venueAddresses);
			columnLists.add(venueCapacities);
			columnLists.add(venueIDs);


			rs.close();
			ps.close();
//            model = new VenueModel(name, address, capacity, eventId);
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println(columnLists);
		return columnLists;
	}


// This is selection
	public VenueModel selectVenue(String venueName) {
		VenueModel venue = null;
		try {
			String query = "SELECT * FROM VENUE WHERE VENUE_NAME=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, venueName);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				venue = new VenueModel(
						rs.getString("venue_name"),
						rs.getString("venue_address"),
						rs.getInt("venue_capacity"),
						rs.getInt("event_id")
				);
			}
			selectable = true;

			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			selectable = false;
			rollbackConnection();

		}


		return venue;
	}

	public boolean getSelectable() {
		return selectable;
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
// this is updating
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

	// this is delete

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
// this is insert
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
