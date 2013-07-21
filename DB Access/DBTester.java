package lab5;

import java.sql.*;

public class DBTester {

	
	public static void main(String args[]) {
		
		try {
			//Load Driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			System.out.println("Driver Loaded!!");
			
			//Get a Connection
			Connection c1 = DriverManager.getConnection("jdbc:odbc:JavaDB");
			System.out.println("Got Connection!!");
			
			//Create a Statement
			Statement stmt = c1.createStatement();
			
			//Setup SQL statement
			String sql = "Select * from Instructors";
			System.out.println(sql);
			
			//Execute Query
			ResultSet rs = stmt.executeQuery(sql);
			
			String id, firstName, lastName, street, city, state, zip, office, email;
			
			//Loop through data
			while (rs.next()) {
			
				id = rs.getString(1);
				firstName = rs.getString(2);
				lastName = rs.getString(3);
				street = rs.getString(4);
				city = rs.getString(5);
				state = rs.getString(6);
				zip = rs.getString(7);
				office = rs.getString(8);
				email = rs.getString(9);
				
				System.out.println("============================================");
				System.out.println("Instructor ID : " + id);
				System.out.println("First Name    : " + firstName);
				System.out.println("Last Name     : " + lastName);
				System.out.println("Street        : " + street);
				System.out.println("City          : " + city);
				System.out.println("State         : " + state);
				System.out.println("Zip           : " + zip);
				System.out.println("Office        : " + office);
				System.out.println("Email         : " + email);
			}
			
			//Close Connection
			c1.close();
	
		}
		catch (SQLException se) { System.out.println(se); }
		catch (Exception e) { System.out.println(e); }
	
	} //end main

}//end class
