import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements Repository<Student, Integer> {


    @Override
    public void createTable() {

        JdbcUtils.setConnection();
        JdbcUtils.setStatement();

        try {
            JdbcUtils.statement.execute("CREATE TABLE IF NOT EXISTS t_student(\n" +
                    "id SERIAL UNIQUE,\n" +
                    "name VARCHAR(50) NOT NULL CHECK(LENGTH(name)>0),\n" +
                    "lastname VARCHAR(50) NOT NULL CHECK(LENGTH(name)>0),\n" +
                    "city VARCHAR(50) NOT NULL, \n" +
                    "age INTEGER NOT NULL CHECK(age>0)  )");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JdbcUtils.statement.close();
                JdbcUtils.connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @Override
    public void save(Student entity) {
        JdbcUtils.setConnection();
        JdbcUtils.setPreparedStatement("INSERT INTO t_student(name,lastname,city,age) VALUES(?,?,?,?)");

        try {
            JdbcUtils.preparedStatement.setString(1, entity.getName());
            JdbcUtils.preparedStatement.setString(2, entity.getLastname());
            JdbcUtils.preparedStatement.setString(3, entity.getCity());
            JdbcUtils.preparedStatement.setInt(4, entity.getAge());
            JdbcUtils.preparedStatement.executeUpdate();
            System.out.println("İşlem başarılı");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JdbcUtils.connection.close();
                JdbcUtils.statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public List<Student> findAll() {
        JdbcUtils.setConnection();
        JdbcUtils.setStatement();
        List<Student> students = new ArrayList<>();

        try {
            ResultSet rs = JdbcUtils.statement.executeQuery("SELECT * FROM t_student");
            while (rs.next()) {

                Student student = new Student(rs.getString("name"),
                        rs.getString("lastname"), rs.getString("city"),
                        rs.getInt("age"));

                student.setId(rs.getInt("id"));
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JdbcUtils.statement.close();
                JdbcUtils.connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return students;
    }

    @Override
    public void update(Student entity) {
        JdbcUtils.setConnection();
        JdbcUtils.setPreparedStatement("UPDATE t_student SET name = ?, lastname = ?,city = ?,age=? WHERE id = ?");

        try {

            JdbcUtils.preparedStatement.setString(1, entity.getName());
            JdbcUtils.preparedStatement.setString(2, entity.getLastname());
            JdbcUtils.preparedStatement.setString(3, entity.getCity());
            JdbcUtils.preparedStatement.setInt(4, entity.getAge());
            JdbcUtils.preparedStatement.setInt(5, entity.getId());

            int update = JdbcUtils.preparedStatement.executeUpdate();

            if (update > 0) {
                System.out.println("Güncelleme başarılı");

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                JdbcUtils.connection.close();
                JdbcUtils.statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }


    }

    @Override
    public void deleteById(Integer id) {

        JdbcUtils.setConnection();
        JdbcUtils.setStatement();

        try {
            int delete = JdbcUtils.statement.executeUpdate("DELETE FROM t_student WHERE id=" + id);
            if (delete>0){
                System.out.println("Öğrenci silindi" + id);
            }else {
                System.out.println("Öğrenci bulunumadı");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Student findById(Integer id) {

        JdbcUtils.setConnection();
        JdbcUtils.setPreparedStatement("SELECT * FROM t_student WHERE id = ?");
        Student student = null;
        try {
            JdbcUtils.preparedStatement.setInt(1, id);
            ResultSet rs = JdbcUtils.preparedStatement.executeQuery();
            if (rs.next()) {

                student = new Student(rs.getString("name"),
                        rs.getString("lastname"), rs.getString("city"),
                        rs.getInt("age"));
                student.setId(rs.getInt("id"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                JdbcUtils.connection.close();
                JdbcUtils.statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        return student;
    }

}
