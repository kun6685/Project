package project.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    protected Connection connection = null;
    protected PreparedStatement psmt;
    protected ResultSet resultSet;

    public Connection getConnection() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String driver = "oracle.jdbc.driver.OracleDriver";
        String user = "project";
        String password = "project";

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결에 실패했습니다.");
            e.printStackTrace();
        }

        return connection;
    }
}
