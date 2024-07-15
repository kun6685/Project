package project.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.database.DatabaseConnection;

public class DrinkDAO extends DatabaseConnection {
    public String getDrinkName(String menuId) {
    	String drinkName = null;
    	String sql = "SELECT menu_name ";
        sql += "FROM drink_table ";
        sql += "WHERE menu_id = ?";
        try (Connection connection = getConnection();
          PreparedStatement psmt = connection.prepareStatement(sql)) {

          psmt.setString(1, menuId);
          ResultSet rs = psmt.executeQuery();
          
          if(rs.next()) {
        	  drinkName = rs.getString("menu_name");
          }
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drinkName;
    }
    
    
    public int getDrinkPrice(String menuId) {
    	int drinkPrice = 0;
    	String sql = "SELECT menu_price ";
        sql += "FROM drink_table ";
        sql += "WHERE menu_id = ?";
        try (Connection connection = getConnection();
          PreparedStatement psmt = connection.prepareStatement(sql)) {

          psmt.setString(1, menuId);
          ResultSet rs = psmt.executeQuery();
          
          if(rs.next()) {
        	  drinkPrice = rs.getInt("menu_price");
          }
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drinkPrice;
    }
}
