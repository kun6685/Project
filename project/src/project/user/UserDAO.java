package project.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.database.DatabaseConnection;

public class UserDAO extends DatabaseConnection {

    // 로그인 기능
    public boolean loginCheck(UserVO userVO) {
        String sql = "SELECT COUNT(*) ";
        sql += "FROM user_table ";
        sql += "WHERE user_id = ? AND user_password = ?";
        try (Connection connection = getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userVO.getUserId());
            psmt.setString(2, userVO.getUserPassword());
            
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; // 로그인 성공
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // 로그인 실패
    }

    // 회원가입 기능
    public boolean registerCheck(UserVO userVO) {
        String sql = "INSERT INTO user_table (user_name, user_id, user_password, phone_number) ";
        sql += "VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql)) {

            psmt.setString(1, userVO.getUserName());
            psmt.setString(2, userVO.getUserId());
            psmt.setString(3, userVO.getUserPassword());
            psmt.setString(4, userVO.getPhoneNumber());
            
            int result = psmt.executeUpdate();

            if (result == 1) {
                return true; // 회원가입 성공
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // 회원가입 실패
    }

    // 회원정보 찾기 아이디
    public boolean findUserId(UserVO userVO) {
        String sql = "SELECT user_id ";
        sql += "FROM user_table ";
        sql += "WHERE user_name = ? AND phone_number = ?";
        try (Connection connection = getConnection();
             PreparedStatement psmt = connection.prepareStatement(sql)) {

            psmt.setString(1, userVO.getUserName());
            psmt.setString(2, userVO.getPhoneNumber());

            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("User ID: " + rs.getString("user_id"));
                    return true; // 아이디 찾기 성공
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // 아이디 찾기 실패
    }

    // 회원정보 찾기 비밀번호
    public boolean findUserPassword(UserVO userVO) {
        String sql = "SELECT user_password ";
        sql += "FROM user_table ";
        sql += "WHERE user_id = ? AND user_name = ? AND phone_number = ?";
        try (Connection connection = getConnection();
             PreparedStatement psmt = connection.prepareStatement(sql)) {

            psmt.setString(1, userVO.getUserId());
            psmt.setString(2, userVO.getUserName());
            psmt.setString(3, userVO.getPhoneNumber());

            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("User Password: " + rs.getString("user_password"));
                    return true; // 비밀번호 찾기 성공
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // 비밀번호 찾기 실패
    }
    
    // 회원 이름 불러오기
    public String getUserName(String userId) {
      String sql = "SELECT user_name FROM user_table WHERE user_id = ?";
      
      try (Connection connection = getConnection();
           PreparedStatement psmt = connection.prepareStatement(sql)) {
          psmt.setString(1, userId);
          
          try (ResultSet rs = psmt.executeQuery()) {
              if (rs.next()) {
                  return rs.getString("user_name"); // 회원 이름 반환
              }
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      
      return null; // 해당 회원 아이디가 없을 경우 null 반환
  }
    
 // 회원 아이디 불러오기
    public String getUserId(String userId) {
      String sql = "SELECT user_name FROM user_table WHERE user_id = ?";
      
      try (Connection connection = getConnection();
           PreparedStatement psmt = connection.prepareStatement(sql)) {
           psmt.setString(1, userId);
          
          try (ResultSet rs = psmt.executeQuery()) {
              if (rs.next()) {
                  return rs.getString("user_id"); // 회원 이름 반환
              }
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      
      return null; // 해당 회원 아이디가 없을 경우 null 반환
  }
   
   // 회원탈퇴 기능
    public boolean killUser(String userName, String userId, String userPassword) {
      String sql = "DELETE FROM user_table WHERE user_name = ? AND user_id = ? AND user_password = ?";
      
      try (Connection connection = getConnection();
           PreparedStatement psmt = connection.prepareStatement(sql)) {
          
          psmt.setString(1, userName);
          psmt.setString(2, userId);
          psmt.setString(3, userPassword);
          
          int rowsAffected = psmt.executeUpdate();
          
          if (rowsAffected > 0) {
            System.out.println(userName + "님, 회원 탈퇴가 성공적으로 처리되었습니다.");
            return true;
          } else {
            System.out.println("회원 탈퇴에 실패하였습니다. 입력하신 정보를 다시 확인해주세요.");
            return false;
          }
          
      } catch (SQLException e) {
          e.printStackTrace();
          return false;
      }
   }
    
    // 모든 회원정보 불러오기
    public List<UserVO> showAllUserInfo() {
      List<UserVO> userList = new ArrayList<>();
      String sql = "SELECT * FROM user_table";

      try (Connection connection = getConnection();
           PreparedStatement psmt = connection.prepareStatement(sql);
           ResultSet rs = psmt.executeQuery()) {

          while (rs.next()) {
              UserVO user = new UserVO();
              user.setIsAdmin(rs.getString("isAdmin"));
              user.setUserName(rs.getString("user_name"));
              user.setUserId(rs.getString("user_id"));
              user.setUserPassword(rs.getString("user_password"));
              user.setPhoneNumber(rs.getString("phone_number"));
              user.setCreationDate(rs.getDate("creation_date"));

              userList.add(user);
          }

      } catch (SQLException e) {
          e.printStackTrace();
      }

      return userList;
  }
}
