package project.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.database.DatabaseConnection;

public class BurgerDAO extends DatabaseConnection {
    public String getBurgerName(String menuId) {
    	String burgurName = null;
    	String sql = "SELECT menu_name ";
        sql += "FROM burger_table ";
        sql += "WHERE menu_id = ?";
        try (Connection connection = getConnection();
          PreparedStatement psmt = connection.prepareStatement(sql)) {

          psmt.setString(1, menuId);
          ResultSet rs = psmt.executeQuery();
          
          if(rs.next()) {
        	  burgurName = rs.getString("menu_name");
          }
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return burgurName;
    }
    
    
    public int getBurgerPrice(String menuId) {
    	int burgurPrice = 0;
    	String sql = "SELECT menu_price ";
        sql += "FROM burger_table ";
        sql += "WHERE menu_id = ?";
        try (Connection connection = getConnection();
          PreparedStatement psmt = connection.prepareStatement(sql)) {

          psmt.setString(1, menuId);
          ResultSet rs = psmt.executeQuery();
          
          if(rs.next()) {
        	  burgurPrice = rs.getInt("menu_price");
          }
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return burgurPrice;
    }
}
