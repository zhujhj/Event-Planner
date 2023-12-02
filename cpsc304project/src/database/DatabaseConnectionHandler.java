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
// import ca.ubc.cs304.model.GuestModel;
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

	private Connection connection = null;
	private DatabaseConnectionHandler dbHandler = null;

	public DatabaseConnectionHandler() {
		try {
			// Load the Oracle JDBC driver
			// Note that the path could change for new drivers
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			connection = DriverManager.getConnection(ORACLE_URL, "ora_zhujason", "a98960727");
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

	public void deleteGuest(String guestName, int ticketNumber) {
		try {
			String query = "DELETE FROM guest WHERE guest_name = ? AND ticket_number = ?";
			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
			ps.setString(1, guestName);
			ps.setInt(2, ticketNumber);

			 int rowCount = ps.executeUpdate();
			 if (rowCount == 0) {
			 	System.out.println(WARNING_TAG + " Guest " + guestName + " does not exist!");
			 }

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteVenue(String venueName) {
		try {
			String query = "DELETE FROM venue WHERE venue_name = ?";
			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
			ps.setString(1, venueName);

			int rowCount = ps.executeUpdate();
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

	public void deleteEvent(int eventID) {
		try {
			String query = "DELETE FROM event WHERE event_id = ?";
			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
			ps.setInt(1, eventID);

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Event " + eventID + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertGuest(GuestModel model) {

		try {
			String q = "INSERT INTO EVENT VALUES ('10',1,'your mom','yo mama')";
			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(q), q, false);

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}

		try {
			String query = "INSERT INTO GUEST VALUES (?,?,?,?)";
			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
			ps.setString(1, model.getName());
			ps.setString(2, model.getEmail());
			ps.setInt(3, model.getPhoneNumber());
			ps.setInt(4, model.getTicketNumber());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertVenue(VenueModel model) {

		try {
			String q = "INSERT INTO EVENT VALUES ('10',2,'event 2','event 2')";
			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(q), q, false);

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}

		try {
			String query = "INSERT INTO VENUE VALUES (?,?,?,?)";
			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
			ps.setString(1, model.getName());
			ps.setString(2, model.getAddress());
			ps.setInt(3, model.getCapacity());
			ps.setInt(4, model.getId());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertEvent(EventModel model) {

		try {
			String query = "INSERT INTO EVENT VALUES (?,?,?,?)";
			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
			ps.setString(1, model.getTime());
			ps.setInt(2, model.getId());
			ps.setString(3, model.getName());
			ps.setString(4, model.getDescription());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
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
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

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

			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		System.out.println(venue.getName());
		System.out.println(venue.getAddress());
		System.out.println(venue.getCapacity());
		System.out.println(venue.getId());

		return venue;
	}

	public VenueModel projectVenue(String venueName, List<String> columns) {

        VenueModel model = null;
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

			List<VenueModel> venues = new ArrayList<>();

			List<String> venueNames = new ArrayList<>();
			List<String> venueAddresses = new ArrayList<>();
			List<Integer> venueCapacities = new ArrayList<>();
			List<Integer> venueIDs = new ArrayList<>();

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

//			do {
//				VenueModel venue = new VenueModel(
//						rs.getString("venue_name"),
//						rs.getString("venue_address"),
//						rs.getInt("venue_capacity"),
//						rs.getInt("event_id")
//				);
//				venues.add(venue);
//			} while (rs.next());

//			for (VenueModel v : venues) {
//				System.out.println(v.getName());
//				System.out.println(v.getAddress());
//				System.out.println(v.getCapacity());
//				System.out.println(v.getId());
//			}
//			System.out.println(venues.size());
			for (int i = 0; i < 3; i++) {
				if (!venueNames.isEmpty()) {
					System.out.println(venueNames.get(i));
				}
				if (!venueAddresses.isEmpty()) {
					System.out.println(venueAddresses.get(i));
				}
				if (!venueCapacities.isEmpty()) {
					System.out.println(venueCapacities.get(i));
				}
				if (!venueIDs.isEmpty()) {
					System.out.println(venueIDs.get(i));
				}
			}

//            if (rs.next()) {
//                for (String column : columns) {
//                    switch (column) {
//                        case "venue_name":
//                            name = rs.getString("venue_name");
//                            break;
//                        case "venue_address":
//                            address = rs.getString("venue_address");
//                            break;
//                        case "venue_capacity":
//                            capacity = rs.getInt("venue_capacity");
//                            break;
//                        case "event_id":
//                            eventId = rs.getInt("event_id");
//                            break;
//                    }
//                }
//
//                // Close resources
//                rs.close();
//                ps.close();
//            }
			rs.close();
			ps.close();
            model = new VenueModel(name, address, capacity, eventId);
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			e.printStackTrace();
        }
        return model;
    }

	public void joinVenueAndEvent() {
		try {
			String query = "SELECT VENUE.venue_name, VENUE.venue_address, VENUE.venue_capacity, EVENT.event_name " +
					"FROM VENUE " +
					"JOIN EVENT ON VENUE.event_id = EVENT.event_id";

			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String venueName = rs.getString("venue_name");
				String venueAddress = rs.getString("venue_address");
				int venueCapacity = rs.getInt("venue_capacity");
				String eventName = rs.getString("event_name");

				System.out.println("Venue: " + venueName + ", Address: " + venueAddress +
						", Capacity: " + venueCapacity + ", Event: " + eventName);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public Map<Integer, Integer> aggregateVenueTotalEventMaxAttendance() {
		Map<Integer, Integer> ret = new HashMap<>();

		try {
			String query = "SELECT event_id, SUM(venue_capacity) AS total_capacity " +
					"FROM VENUE " +
					"GROUP BY event_id";

			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int eventId = rs.getInt("event_id");
				int totalCapacity = rs.getInt("total_capacity");

				System.out.println("Event ID: " + eventId + ", Total Capacity: " + totalCapacity);
				ret.put(eventId, totalCapacity);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return ret;
	}

	// Finds all events where the total capacity from all venues is greater than the specified amount
	public Map<Integer, Integer> aggregateVenueCapacityByEventHaving(int minTotalCapacity) {
		Map<Integer, Integer> aggregatedDataMap = new HashMap<>();

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
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		System.out.println(aggregatedDataMap);
		return aggregatedDataMap;
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
		}
		System.out.println(result);
		return result;
	}

	// Returns average event capacity for each different event
	public Map<Integer, Double> calculateAverageCapacityPerEvent() {
		Map<Integer, Double> result = new HashMap<>();

		try {
			// total capacity for each event
			String totalCapacityQuery = "SELECT event_id, SUM(venue_capacity) AS total_capacity " +
					"FROM VENUE " +
					"GROUP BY event_id";

			// number of venues for each event
			String venueCountQuery = "SELECT event_id, COUNT(venue_name) AS venue_count " +
					"FROM VENUE " +
					"GROUP BY event_id";

			// average capacity per event
			String averageCapacityQuery = "SELECT tc.event_id, " +
					"COALESCE(tc.total_capacity / NULLIF(vc.venue_count, 0), 0) AS average_capacity " +
					"FROM (" + totalCapacityQuery + ") tc " +
					"JOIN (" + venueCountQuery + ") vc ON tc.event_id = vc.event_id";

			PreparedStatement avgCapacityPs = connection.prepareStatement(averageCapacityQuery);
			ResultSet avgCapacityRs = avgCapacityPs.executeQuery();

			while (avgCapacityRs.next()) {
				int eventId = avgCapacityRs.getInt("event_id");
				double averageCapacity = avgCapacityRs.getDouble("average_capacity");
				result.put(eventId, averageCapacity);
			}

			avgCapacityRs.close();
			avgCapacityPs.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result;
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
//			String query = "CREATE TABLE TEST (guest_name varchar(20), guest_email varchar(20) not null, guest_phone_number INTEGER, guest_ticket_number INTEGER, PRIMARY KEY (guest_name, guest_ticket_number))";
			String query = "CREATE TABLE Sponsor (\n" +
					"                         sponsor_name VARCHAR2(255),\n" +
					"                         amount INTEGER,\n" +
					"                         request VARCHAR2(255),\n" +
					"                         phone_number INTEGER,\n" +
					"                         email VARCHAR2(255),\n" +
					"                         PRIMARY KEY (sponsor_name, email)\n" +
					")";
			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("EXCEPTION " + e.getMessage());
		}

//		try {
//			DatabaseMetaData metaData = connection.getMetaData();
//			ResultSet resultSet = metaData.getTables(null, null, "HI", null);
//			if (resultSet.next()) {
//				System.out.println("Table exists.");
//			} else {
//				System.out.println("Table  does not exist.");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		try {
			DatabaseMetaData metaData = connection.getMetaData();
			ResultSet resultSet = metaData.getTables(null, "ORA_ZHUJASON", "GUEST", null);
			if (resultSet.next()) {
				System.out.println("Table 'HI' exists in schema 'ORA_ZHUJASON'.");
			} else {
				System.out.println("Table 'HI' does not exist in schema 'ORA_ZHUJASON'.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

	public int getRowCount() {
		return 0;
	}

	public boolean getDuplicate() {
		return true;
	}
}
