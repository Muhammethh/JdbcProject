import java.sql.*;

public class denemeUtils {

    public static Connection connection;
    public static Statement statement;
    public static PreparedStatement prpStatement;

    public static Connection setConnection() {

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JdbcProject", "techpro", "47yk2d8r6C.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;

    }

    public static Statement setStatement() {

        try {
            statement = setConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    public static PreparedStatement prpStatement(String sql){

        try {
            prpStatement = setConnection().prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prpStatement;
    }


    public static void close(){

        try {
            connection.close();
            statement.close();
            prpStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
