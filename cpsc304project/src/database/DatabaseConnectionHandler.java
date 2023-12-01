package database;

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
			String query = "SELECT * FROM VENUE WHERE Name=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, venueName);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				venue = new VenueModel(
						rs.getString("Name"),
						rs.getString("Address"),
						rs.getInt("Capacity"),
						rs.getInt("eventID")
				);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return venue;
	}

	public VenueModel projectVenue(String venueName, List<String> columns) {

        VenueModel model = null;
        try {
            String query = "SELECT " + columns.toString() + " FROM VENUE WHERE Name=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, venueName);

            ResultSet rs = ps.executeQuery();

            String name = null;
            String address = null;
            int capacity = 0;
            int eventId = 0;

            if (rs.next()) {
                for (String column : columns) {
                    switch (column) {
                        case "Name":
                            name = rs.getString("Name");
                            break;
                        case "Address":
                            address = rs.getString("Address");
                            break;
                        case "Capacity":
                            capacity = rs.getInt("Capacity");
                            break;
                        case "eventID":
                            eventId = rs.getInt("eventID");
                            break;
                    }
                }

                // Close resources
                rs.close();
                ps.close();
            }
            model = new VenueModel(name, address, capacity, eventId);
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return model;
    }

	public void joinVenues(String venue1Name, String venue2Name) {
		try {
			String query = "SELECT V1.venue_name AS venue1_name, V1.venue_address AS venue1_address, V1.venue_capacity AS venue1_capacity, " +
					"V2.venue_name AS venue2_name, V2.venue_address AS venue2_address, V2.venue_capacity AS venue2_capacity " +
					"FROM VENUE V1 " +
					"JOIN VENUE V2 ON V1.event_id = V2.event_id " +
					"WHERE V1.venue_name = ? AND V2.venue_name = ? AND V1.venue_name <> V2.venue_name";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, venue1Name);
			ps.setString(2, venue2Name);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String venue1NameResult = rs.getString("venue1_name");
				String venue1Address = rs.getString("venue1_address");
				int venue1Capacity = rs.getInt("venue1_capacity");

				String venue2NameResult = rs.getString("venue2_name");
				String venue2Address = rs.getString("venue2_address");
				int venue2Capacity = rs.getInt("venue2_capacity");

				System.out.println("Venue 1: " + venue1NameResult + ", Address: " + venue1Address +
						", Capacity: " + venue1Capacity);
				System.out.println("Venue 2: " + venue2NameResult + ", Address: " + venue2Address +
						", Capacity: " + venue2Capacity);
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
}
