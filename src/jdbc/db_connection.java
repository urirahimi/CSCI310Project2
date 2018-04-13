package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class db_connection {

	public static void main (String[] args) {
		System.out.println("HERERERERERERRERERER");
		//check sql database for this username and if password matches
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement colorPrep = null;
		ResultSet rs = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/collage?user=root&password=root&useSSL=false");
			st = conn.createStatement();
			ps = conn.prepareStatement("SELECT * FROM UserInfo WHERE username=? and passwords=?");
			ps.setString(1, "daher");
			ps.setString(2, "devin2");
			rs = ps.executeQuery();
			while (rs.next()) {
				String user = rs.getString("username");
				String pass = rs.getString("passwords");
				System.out.println ("username = " + user);
				System.out.println ("password = " + pass);
			}
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
	}
	public boolean login(String userName, String password) {
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
				System.out.println("checking if username = username" + rs.getString("username"));
				if(rs.getString("username").equals(username)) {
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
