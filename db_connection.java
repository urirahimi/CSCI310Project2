package jdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import utilities.Pair;

public class db_connection {
	private String userName;
	private int imageCount;
	public db_connection(){
//		userName = "daher";
		imageCount = 0;
	}
	
//	public static void main (String[] args) {
//		System.out.println("HERERERERERERRERERER");
//		//check sql database for this username and if password matches
//		Connection conn = null;
//		Statement st = null;
//		PreparedStatement ps = null;
//		PreparedStatement ps2 = null;
//		PreparedStatement colorPrep = null;
//		ResultSet rs = null;
//		try {
//			
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost/collage?user=root&password=root&useSSL=false");
//			st = conn.createStatement();
//			ps = conn.prepareStatement("SELECT * FROM UserInfo WHERE username=? and passwords=?");
//			ps.setString(1, "daher");
//			ps.setString(2, "devin2");
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				String user = rs.getString("username");
//				String pass = rs.getString("passwords");
//				System.out.println ("username = " + user);
//				System.out.println ("password = " + pass);
//			}
//		} catch (SQLException sqle) {
//			System.out.println ("SQLException: " + sqle.getMessage());
//		} catch (ClassNotFoundException cnfe) {
//			System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (st != null) {
//					st.close();
//				}
//				if (ps != null) {
//					ps.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException sqle) {
//				System.out.println("sqle: " + sqle.getMessage());
//			}
//		}
//	}
	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			System.out.println("hi");
			Pair p = new Pair("dog","/Users/dmdaher/Desktop");
			//boolean isCreateTest = createImage(p);
            //create Pair with image
			//send it over to createImage
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	public String getUsername() {
		return this.userName;
	}
	//select the userID for the current user --- in this case "daher"
	//find the imageCount for that user using the userID found
	//then insert the pair image into the images field where the userID is the current user and the imageCount is incremented
	public boolean createImage(Pair p, String username) {
		this.userName = username;
		System.out.println("what is the username" + this.userName);
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		boolean flag = false;
		try {
			//int imageCount = 0; //will need to maintain count number so should not be 0
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/collage?user=root&password=root&useSSL=false");
			st = conn.createStatement();
			ps = conn.prepareStatement("SELECT userID FROM UserInfo WHERE username=?");
			ps2 = conn.prepareStatement("SELECT imageCount FROM Image WHERE userID=? order by imageCount desc");				
			ps3 = conn.prepareStatement("INSERT INTO Image(userID, images, imageCount) VALUES(?,?,?)");
			ps.setString(1, this.userName);
			rs = ps.executeQuery();
			System.out.println("hiIIII");
			while (rs.next()) { //basically we are searching for all the userID with username daher or current user
				//then we are finding the imageCount for that specific user
				//then we are inserting the image in the image column that matches the current user's id
				System.out.println("hi");
				rs.getInt("userID");	
				ps2.setInt(1, rs.getInt("userID"));
				rs2 = ps2.executeQuery();
				int count = 0;
				if(rs2.next())
					count = rs2.getInt("imageCount") ;
				ps3.setInt(1, rs.getInt("userID"));
				int newImageCount = count + 1;
				ps3.setString(2, this.userName + "-" + newImageCount);				
			    String directoryName = this.userName;
			    File directory = new File(directoryName);
			    if (! directory.exists()){
			        directory.mkdir();
			        // If you require it to make the entire directory path including parents,
			        // use directory.mkdirs(); here instead.
			    }

				p.saveImageToFile(this.userName + "-" + newImageCount);
				ps3.setInt(3, newImageCount);
				ps3.executeUpdate();
				flag = true;
				System.out.println("what is the imageCount? " + newImageCount);
				System.out.println("what is the int?" + rs.getInt("userID"));
				//CHECK IF DIRECTORY EXISTS
				//IF NOT, CREATE FILEPATH
				//IF SO, ADD 1 and CREATE FILEPATH
				//THEN IN DATABASE, INSERT INTO IMAGE TABLE
				//THEN CHECK THAT USERID IN THE IMAGE TABLE
				//AND GRAB THOSE IMAGES AND DISPLAY
//				if(rs.getString("username").equals(userName)) {
//					//p.saveImageToFile(userName + " /");
//				}
			}
			
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
			return false;
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println ("Exception: " + e.getMessage());
			return false;
		} finally {
			System.out.println("After finally loop");
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
				return false;
			}
			System.out.println("After finally second loop");
			if(flag == false) {
				System.out.println("After if flag false");
				return false;
			}
		}
		System.out.println("After everything");
		return true;	
	}
	
	//select the userID for the current user --- in this case "daher"
		//find the imageCount for that user using the userID found
		//then insert the pair image into the images field where the userID is the current user and the imageCount is incremented
	public ArrayList<Pair> getImages() {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		boolean flag = false;
		try {
			//int imageCount = 0; //will need to maintain count number so should not be 0
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/collage?user=root&password=root&useSSL=false");
			st = conn.createStatement();
			ps = conn.prepareStatement("SELECT userID FROM UserInfo WHERE username=?");
			ps2 = conn.prepareStatement("SELECT imageCount FROM Image WHERE userID=?");				
			ps3 = conn.prepareStatement("SELECT images FROM Image WHERE userID=?");
			ps.setString(1, this.userName);			
			rs = ps.executeQuery();
			while (rs.next()) { //basically we are searching for all the userID with username daher or current user
				//then we are finding the imageCount for that specific user
				//then we are inserting the image in the image column that matches the current user's id
				rs.getInt("userID");
				ps3.setString(1, this.userName);
				rs2 = ps.executeQuery();
				while (rs2.next()) {
					pairs.add(Pair.getPairFromFile(rs2.getString(1)));
				}
				flag = true;
			}
		}catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
			return null;
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
			return null;
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
				return null;
			}
		}
		if (flag == false) {
			return null;
		}else {
			return pairs;	
		}
	}
	
	public boolean login(String userName, String password) {
		this.userName = userName;
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement colorPrep = null;
		ResultSet rs = null;
		try {
			System.out.println ("inside jdbc try function");
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println ("after driver class declaration");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/collage?user=root&password=root&useSSL=false");
			//System.out.println ("after driver connection?");
			st = conn.createStatement();
			ps = conn.prepareStatement("SELECT * FROM UserInfo WHERE username=? and passwords=?");
			ps.setString(1, userName);
			ps.setString(2, password);
 			rs = ps.executeQuery();
 			while (rs.next()) {
 				System.out.println ("HERE?!!!!!!!");
 				String user = rs.getString("username");
 				String pass = rs.getString("passwords");
 				System.out.println ("username = " + user);
 				System.out.println ("password = " + pass);
				return true;
 			}
 		} catch (SQLException sqle) {
 			System.out.println ("SQLException: " + sqle.getMessage());
 		}catch (ClassNotFoundException cnfe) {
 			System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
 		}
		return false;
 	}
	public boolean signup(String username, String password) {
		this.userName = username;
		//check sql database for this username and if password matches
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/collage?user=root&password=root&useSSL=false");
			st = conn.createStatement();
			ps = conn.prepareStatement("SELECT * FROM UserInfo WHERE username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("checking if username = " + rs.getString("username"));
				if(rs.getString("username").equals(username)) {
					System.out.println("it does...");
					return false;
				}
			}
			System.out.println("about to insert into userinfo table");
			ps.close();
			ps = conn.prepareStatement("INSERT INTO UserInfo(username, passwords, images) VALUES(?,?,?)");
			ps.setString(1, username);
			ps.setString(2, password);
//			ps.setBlob(3, inputStream);
			ps.setNull(3, Types.BLOB);
			ps.executeUpdate();
			System.out.println("get here?");
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return true;
	}
}
