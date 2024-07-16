package project.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import project.database.DatabaseConnection;

public class OrderDAO extends DatabaseConnection {
    public void orderVerification(String userId, String burgerName, int burgerPrice,
                                  String sideName, int sidePrice,
                                  String drinkName, int drinkPrice, int totalPrice) {
        
        String sql = "INSERT INTO order_table (order_id, user_id, burger_menu_name, burger_menu_price, " +
                     "side_menu_name, side_menu_price, " +
                     "drink_menu_name, drink_menu_price, total_price) " +
                     "VALUES (order_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = getConnection();
             PreparedStatement psmt = connection.prepareStatement(sql)) {
        	  
             psmt.setString(1, userId);
             psmt.setString(2, burgerName);
             psmt.setInt(3, burgerPrice);
             psmt.setString(4, sideName);
             psmt.setInt(5, sidePrice);
             psmt.setString(6, drinkName);
             psmt.setInt(7, drinkPrice);
             psmt.setInt(8, totalPrice);
            
             psmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // 모든 사용자의 구매 내역 가져오기
    public List<OrderVO> getAllOrderHistory() {
      List<OrderVO> orderHistory = new ArrayList<>();
      String sql = "SELECT * FROM order_table";
      
      try (Connection connection = getConnection();
           PreparedStatement psmt = connection.prepareStatement(sql);
           ResultSet rs = psmt.executeQuery()) {
          
          while (rs.next()) {
              int orderId = rs.getInt("order_id");
              String userId = rs.getString("user_id");
              String burgerName = rs.getString("burger_menu_name");
              int burgerPrice = rs.getInt("burger_menu_price");
              String sideName = rs.getString("side_menu_name");
              int sidePrice = rs.getInt("side_menu_price");
              String drinkName = rs.getString("drink_menu_name");
              int drinkPrice = rs.getInt("drink_menu_price");
              int totalPrice = rs.getInt("total_price");
              Timestamp orderTime = rs.getTimestamp("order_time");

              OrderVO order = new OrderVO(userId, burgerName, burgerPrice,
                                          sideName, sidePrice, drinkName, drinkPrice,
                                          totalPrice, orderTime);
              
              order.setOrderId(orderId);
              
              orderHistory.add(order);
          }
          
      } catch (SQLException e) {
          e.printStackTrace();
      }
      
      return orderHistory;
  }

    
    
    // 특정 사용자의 구매 내역 가져오기
    public List<OrderVO> getOrderHistory(String userId) {
        List<OrderVO> orderHistory = new ArrayList<>();
        String sql = "SELECT * FROM order_table WHERE user_id = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement psmt = connection.prepareStatement(sql)) {
            
            psmt.setString(1, userId);
            ResultSet rs = psmt.executeQuery();
            
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                String burgerName = rs.getString("burger_menu_name");
                int burgerPrice = rs.getInt("burger_menu_price");
                String sideName = rs.getString("side_menu_name");
                int sidePrice = rs.getInt("side_menu_price");
                String drinkName = rs.getString("drink_menu_name");
                int drinkPrice = rs.getInt("drink_menu_price");
                int totalPrice = rs.getInt("total_price");
                Timestamp orderTime = rs.getTimestamp("order_time");

                OrderVO order = new OrderVO(userId, burgerName, burgerPrice,
                                            sideName, sidePrice, drinkName, drinkPrice,
                                            totalPrice, orderTime);
                
                order.setOrderId(orderId);
                
                orderHistory.add(order);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return orderHistory;
    }
    
 // 주문 내역 삭제 메소드
    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM order_table WHERE order_id = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement psmt = connection.prepareStatement(sql)) {
            
            psmt.setInt(1, orderId);
            
            psmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // 최근 주문 내역 번호 반환
    public int getMaxOrderId() {
        String sql = "SELECT MAX(order_id) FROM order_table";
        
        try (Connection connection = getConnection();
          PreparedStatement psmt = connection.prepareStatement(sql)) {
            
          ResultSet rs = psmt.executeQuery();
            
          if (rs.next()) {
              return rs.getInt(1);
          }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1; // 실패 시 -1을 반환하거나 다른 방식으로 처리할 수 있습니다.
    }
    
    // 메뉴 변경
    public void updateOrder(int orderId, String burgerName, int burgerPrice,
     String sideName, int sidePrice, String drinkName, int drinkPrice, int totalPrice) {
     String sql = "UPDATE order_table SET " +
     "burger_menu_name = ?, burger_menu_price = ?, " +
     "side_menu_name = ?, side_menu_price = ?, " +
     "drink_menu_name = ?, drink_menu_price = ?, " +
     "total_price = ? " +
     "WHERE order_id = ?";

     try (Connection connection = getConnection();
     PreparedStatement psmt = connection.prepareStatement(sql)) {

     psmt.setString(1, burgerName);
     psmt.setInt(2, burgerPrice);
     psmt.setString(3, sideName);
     psmt.setInt(4, sidePrice);
     psmt.setString(5, drinkName);
     psmt.setInt(6, drinkPrice);
     psmt.setInt(7, totalPrice);
     psmt.setInt(8, orderId);

     int rowsUpdated = psmt.executeUpdate();

     if (rowsUpdated > 0) {
       System.out.println("");
       System.out.println("주문 내역이 성공적으로 수정되었습니다.");
     } else {
       System.out.println("주문 내역 수정에 실패했습니다.");
     }

     } catch (SQLException e) {
       e.printStackTrace();
     }
}

}
