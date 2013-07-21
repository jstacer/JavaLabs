package lab5;

import java.sql.*;

public class Student {
	//create properties
	int id, zip;
	double gpa;
	String firstName, lastName, street, city, state, email;
	
	//empty and full constructors
	public Student(){
	}
	
	public Student(int id, String firstName, String lastName, String street, String city,
					String state, int zip, String email, double gpa) {
		this.id = id;
		this.zip = zip;
		this.gpa = gpa;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.email = email;
	}
	
	//setters and getters for all properties
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setZipcode(int zip){
		this.zip = zip;
	}
	
	public int getZipcode(){
		return zip;
	}
	
	public void setGpa(double gpa){
		this.gpa = gpa;
	}
	
	public double getGpa(){
		return gpa;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setStreet(String street){
		this.street = street;
	}
	
	public String getStreet(){
		return street;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setState(String state){
		this.state = state;
	}
	
	public String getState(){
		return state;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	//prints out all properties
	public void display(){
		System.out.println("Student ID : " + id);
		System.out.println("First Name : " + firstName);
		System.out.println("Last Name  : " + lastName);
		System.out.println("Street     : " + street);
		System.out.println("City       : " + city);
		System.out.println("State      : " + state);
		System.out.println("Zip Code   : " + zip);
		System.out.println("Email      : " + email);
		System.out.printf("GPA        : %.1f", gpa);
	}
	
	
	//uses id as parameter to locate record and save all fields from DB into class properties
	public void find(int sid){
		
		try {
			//Load Driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//Get a Connection
			Connection c1 = DriverManager.getConnection("jdbc:odbc:JavaDB");
			
			//Create a Statement
			Statement stmt = c1.createStatement();
			
			//Setup SQL statement
			String sql = "Select * from Students where ID = " + sid;
			
			//Execute Query
			ResultSet rs = stmt.executeQuery(sql);
			
			//Save results into properties
			while (rs.next()) {
				id = rs.getInt(1);
				firstName = rs.getString(2);
				lastName = rs.getString(3);
				street = rs.getString(4);
				city = rs.getString(5);
				state = rs.getString(6);
				zip = rs.getInt(7);
				email = rs.getString(8);
				gpa = rs.getDouble(9);
			}
			
			System.out.println("Record found.");
				
			//Close Connection
			c1.close();
	
		}
		catch (SQLException se) { System.out.println(se); }
		catch (Exception e) { System.out.println(e); }
		
	} //end find()
	
	
	//accepts full set of properties and inserts full record into DB
	public void insert(int id, String firstName, String lastName, String street, String city,
						String state, int zip, String email, double gpa){
		try {
			//Load Driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//Get a Connection
			Connection c1 = DriverManager.getConnection("jdbc:odbc:JavaDB");
			
			//Setup SQL statement with prepared statement
			String sql = "INSERT INTO Students(ID, FirstName, Lastname, Street, City, " +
					 	"State, Zip, EMail, GPA) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = c1.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, street);
			ps.setString(5, city);
			ps.setString(6, state);
			ps.setInt(7, zip);
			ps.setString(8, email);
			ps.setDouble(9, gpa);
			
			//Execute Update
			int val = ps.executeUpdate();
			
			//Put parameter values into properties
			this.id = id;
			this.zip = zip;
			this.gpa = gpa;
			this.firstName = firstName;
			this.lastName = lastName;
			this.street = street;
			this.city = city;
			this.state = state;
			this.email = email;
			
			System.out.println("Record inserted.");
				
			//Close Connection
			c1.close();
	
		}
		catch (SQLException se) { System.out.println(se); se.printStackTrace(); }
		catch (Exception e) { System.out.println(e); }
		
	} //end insert()
	
	
	//deletes record that matches id of invoking object
	public void deleteDB(){
		try {
			//Load Driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//Get a Connection
			Connection c1 = DriverManager.getConnection("jdbc:odbc:JavaDB");
			
			//Create a Statement
			Statement stmt = c1.createStatement();
			
			//Setup SQL statement
			String sql = "DELETE FROM Students WHERE ID = " + this.id;
			
			//Execute Query
			int val = stmt.executeUpdate(sql);
			
			System.out.println("Record deleted.");
				
			//Close Connection
			c1.close();
	
		}
		catch (SQLException se) { System.out.println(se); se.printStackTrace();}
		catch (Exception e) { System.out.println(e); }
	} //end deleteDB()
	
	
	//uses id to find DB record matching object, updates record with all object's properties
	public void updateDB(){
		try {
			//Load Driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//Get a Connection
			Connection c1 = DriverManager.getConnection("jdbc:odbc:JavaDB");
			
			//Setup SQL statement. Noted: breaking a line and concatenating this string gave me
			//an ODBC driver error saying it expected more parameters. I suspect that using
			//concatenated strings doesn't work well for prepared statements because the count
			//of ?s restarts after a string break. As currently written, it works.
			String sql = 
			"UPDATE Students SET FirstName = ?, LastName = ?, Street = ?, City = ?, State = ?, Zip = ?, EMail = ?, GPA = ? WHERE ID = " 
			+ this.id;  
			
			//Create a Statement
			PreparedStatement ps = c1.prepareStatement(sql);
			
			ps.setString(1, this.firstName);
			ps.setString(2, this.lastName);
			ps.setString(3, this.street);
			ps.setString(4, this.city);
			ps.setString(5, this.state);
			ps.setInt(6, this.zip);
			ps.setString(7, this.email);
			ps.setDouble(8, this.gpa);
			
			//Execute update
			ps.executeUpdate();
			
			System.out.println("Table updated.");
				
			//Close Connection
			c1.close();
	
		}
		catch (SQLException se) { System.out.println(se); se.printStackTrace(); }
		catch (Exception e) { System.out.println(e); }
	} //updateDB()
	
	
	public static void main(String[] args) {
		//all methods tested with provided code for each lab question
	}

} //end class
