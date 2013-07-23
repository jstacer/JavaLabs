package lab6;

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
		
		if(this.id == 0){
			System.out.println("No record to display.");
			return;
		}
		
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
	public String find(int sid){
		
		try {
			//Load Driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//Get a Connection
			Connection c1 = DriverManager.getConnection("jdbc:odbc:JavaDB");
			
			//Test to see if record already exists
			boolean exists = recordExists(sid, c1);
			//if statement determines whether to continue find() method
			if (exists == false){
				return "Record does not exist.";
			}
			
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
			
			//Close Connection
			c1.close();
		}
		catch (SQLException se) { System.out.println(se); }
		catch (Exception e) { System.out.println(e); }
		
		return "Record found.";
	} //end find()
	
	
	//accepts full set of properties and inserts full record into DB
	public String insert(int id, String firstName, String lastName, String street, String city,
						String state, int zip, String email, double gpa){
		try {
			//Load Driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//Get a Connection
			Connection c1 = DriverManager.getConnection("jdbc:odbc:JavaDB");
			
			//Test to see if record already exists
			boolean exists = recordExists(id, c1);
			//if statement determines whether to continue insert() method
			if (exists == true){
				return "Record already exists.";
			}
			
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
				
			//Close Connection
			c1.close();
	
		}
		catch (SQLException se) { System.out.println(se); se.printStackTrace(); }
		catch (Exception e) { System.out.println(e); }
		
		return "Record inserted.";
	} //end insert()
	
	
	//deletes record that matches id of invoking object
	public String deleteDB(int id){
		try {
			//Load Driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//Get a Connection
			Connection c1 = DriverManager.getConnection("jdbc:odbc:JavaDB");
			
			//Test to see if record already exists
			boolean exists = recordExists(id, c1);
			//if statement determines whether to continue delete() method
			if (exists == false){
				return "No record to delete.";
			}
			
			//Create a Statement
			Statement stmt = c1.createStatement();
			
			//Setup SQL statement
			String sql = "DELETE FROM Students WHERE ID = " + id;
			
			//Execute Query
			stmt.executeUpdate(sql);
				
			//Close Connection
			c1.close();
	
		}
		catch (SQLException se) { System.out.println(se); se.printStackTrace();}
		catch (Exception e) { System.out.println(e); }
		
		return "Record deleted.";
	} //end deleteDB()
	
	
	//uses id to find DB record matching object, updates record with all object's properties
	public String updateDB(int id, String firstName, String lastName, String street, String city,
			String state, int zip, String email, double gpa){
		try {
			//Load Driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//Get a Connection
			Connection c1 = DriverManager.getConnection("jdbc:odbc:JavaDB");
			
			//Test to see if record already exists
			boolean exists = recordExists(id, c1);
			//if statement determines whether to continue update() method
			if (exists == false){
				return "No record to update.";
			}
			
			//Setup SQL statement. Noted: breaking a line and concatenating this string gave me
			//an ODBC driver error saying it expected more parameters. I suspect that using
			//concatenated strings doesn't work well for prepared statements because the count
			//of ?s restarts after a string break. As currently written, it works.
			String sql = 
			"UPDATE Students SET FirstName = ?, LastName = ?, Street = ?, City = ?, State = ?, Zip = ?, EMail = ?, GPA = ? WHERE ID = " 
			+ id;  
			
			//Create a Statement
			PreparedStatement ps = c1.prepareStatement(sql);
			
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, street);
			ps.setString(4, city);
			ps.setString(5, state);
			ps.setInt(6, zip);
			ps.setString(7, email);
			ps.setDouble(8, gpa);
			
			//Execute update
			ps.executeUpdate();
				
			//Close Connection
			c1.close();
	
		}
		catch (SQLException se) { System.out.println(se); se.printStackTrace(); }
		catch (Exception e) { System.out.println(e); }
		
		return "Record updated.";
	} //updateDB()
	
	
	//tests to see if a record exists
	public boolean recordExists(int id, Connection c1){	
		//variable to return
		boolean exists = false;
		try{
			//Create a Statement
			Statement stmt = c1.createStatement();

			//Setup SQL statement
			String sql = "Select * from Students where ID = " + id;

			//Execute Query
			ResultSet rs = stmt.executeQuery(sql);
			
			//test to see if first column has ID #
			while(rs.next()){
				if(rs.getInt(1) != 0)
					exists = true;
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return exists;
	} //end recordExists	
		
	
	public static void main(String[] args) {
		
	}

} //end class
