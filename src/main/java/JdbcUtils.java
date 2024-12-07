import java.sql.*;

public class JdbcUtils {

    public static Connection connection;
    public static Statement statement;
    public static PreparedStatement preparedStatement;

    public static void setConnection() {

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JdbcProject", "techpro", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void setStatement() {

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static void setPreparedStatement(String sql) {

        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
