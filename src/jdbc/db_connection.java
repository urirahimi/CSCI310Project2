package jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;

import utilities.Pair;

public class db_connection {
	private String userName;
	private int imageCount;
	public db_connection(){
		userName = "daher";
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
			Pair p = new Pair("dog","/Users/dmdaher/Desktop");
			//boolean isCreateTest = createImage(p);
            //create Pair with image
			//send it over to createImage
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	//select the userID for the current user --- in this case "daher"
	//find the imageCount for that user using the userID found
	//then insert the pair image into the images field where the userID is the current user and the imageCount is incremented
	public boolean createImage(Pair p) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		try {
			//int imageCount = 0; //will need to maintain count number so should not be 0
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/collage?user=root&password=root&useSSL=false");
			st = conn.createStatement();
			ps = conn.prepareStatement("SELECT userID FROM UserInfo WHERE username=?");
			ps2 = conn.prepareStatement("SELECT imageCount FROM Image WHERE userID=?");				
			ps3 = conn.prepareStatement("INSERT INTO Image(userID, images, imageCount) VALUES(?,?,?)");
			ps.setString(1, this.userName);
			rs = ps.executeQuery();
			while (rs.next()) { //basically we are searching for all the userID with username daher or current user
				//then we are finding the imageCount for that specific user
				//then we are inserting the image in the image column that matches the current user's id
				rs.getInt("userID");
				ps2.setInt(1, rs.getInt("userID"));
				rs2 = ps2.executeQuery();
				rs2.next();
				ps3.setInt(1, rs.getInt("userID"));
				ps3.setObject(2, p);
				ps3.setInt(3, rs2.getInt("imageCount") + 1);
				ps3.executeUpdate();
				System.out.println("what is the imageCount? " + rs2.getInt("imageCount"));
//				ps2.setInt(rs.getInt("userID"), p, );
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
	
	public String retrieveImages(String userName) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        String bytes = "for testing...";
		try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/collage?user=root&password=root&useSSL=false");
            File file = new File();
            FileOutputStream fos;
            //fos. //input the file pathname here
            byte b[];
            java.sql.Blob blob;
            ps = conn.prepareStatement("SELECT * FROM UserInfo WHERE username=?");
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while(rs.next()){
                blob= rs.getBlob("images");
                b=blob.getBytes(1,(int)blob.length());
//                fos.write(b);
                return bytes;
            }            
            ps.close();
//            fos.close();
            conn.close();
            return bytes;
        }catch(Exception e){
            e.printStackTrace();
        }
		return bytes;
	}
	
	public boolean login(String userName, String password) {
		userName = this.userName;
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
		
		//this is for potential change in BLOB prepared statement
		/*
		+//public void insert() throws Exception {
		+Connection conn = null;
		+PreparedStatement ps = null;
		+
		+InputStream is = null;
		+try {
		+    conn = this.connection.getConnection();
		+    String sql = "INSERT INTO Table (image) VALUES (?)";
		+
		+    ps = conn.prepareStatement(sql);
		+    if (this.file != null && this.file.canRead()) {
		+        is = new BufferedInputStream(new FileInputStream(this.file));
		+        ps.setBinaryStream(1, is, (int) this.file.length());
		+    } else {
		+        ps.setNull(1, Types.BLOB);
		+    }
		+} catch (Exception e) {
		+    LOG.error("", e);
		+    throw e;
		+} finally {
		+    FileUtil.close(is);
		+    DAOUtil.close(conn);
		+}
		 }
		+*/
