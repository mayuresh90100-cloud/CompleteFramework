package Utils;
import java.sql.*;

public class DBUtils {
	
	 private Connection con;

	    // Constructor to establish connection
	    public DBUtils(String url, String user, String password) throws SQLException {
	        con = DriverManager.getConnection(url, user, password);
	    }
	    
	 // Fetch single cell value
	    public String getCellValue(String query) {
	        try (Statement stmt = con.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            if (rs.next()) {
	                return rs.getString(1);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

}
