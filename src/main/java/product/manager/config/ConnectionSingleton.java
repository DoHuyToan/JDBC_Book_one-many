package product.manager.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
    //product: TÊN CỦA DATABASE
    private static String jdbcURL = "jdbc:mysql://localhost:3306/book_one_many";
    private static String jdbcUsername = "root";
    //To@nOpen89: pass của máy mình vào SQL
    private static String jdbcPassword = "To@nOpen89";
    private static Connection connection;

    public static Connection getConnection(){
        if (connection == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                System.out.println("Ket noi Thanh cong");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("Ket noi loi");
            }
        }
        return connection;
    }
}
