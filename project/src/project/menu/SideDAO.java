package project.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.database.DatabaseConnection;

public class SideDAO extends DatabaseConnection {
    public String getSideName(String menuId) {
    	String sideName = null;
    	String sql = "SELECT menu_name ";
        sql += "FROM side_table ";
        sql += "WHERE menu_id = ?";
        try (Connection connection = getConnection();
          PreparedStatement psmt = connection.prepareStatement(sql)) {

          psmt.setString(1, menuId);
          ResultSet rs = psmt.executeQuery();
          
          if(rs.next()) {
        	  sideName = rs.getString("menu_name");
          }
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sideName;
    }
    
    
    public int getSidePrice(String menuId) {
    	int sidePrice = 0;
    	String sql = "SELECT menu_price ";
        sql += "FROM side_table ";
        sql += "WHERE menu_id = ?";
        try (Connection connection = getConnection();
          PreparedStatement psmt = connection.prepareStatement(sql)) {

          psmt.setString(1, menuId);
          ResultSet rs = psmt.executeQuery();
          
          if(rs.next()) {
        	  sidePrice = rs.getInt("menu_price");
          }
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sidePrice;
    }
}
