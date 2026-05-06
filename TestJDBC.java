import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/SchoolDB";
        String user = "root";
        String password = "Root@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // optional

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected!");

            Statement stmt = conn.createStatement();

            // INSERT
            stmt.executeUpdate("INSERT INTO Students(name, age, grade) VALUES ('David', 16, '10th')");

            // UPDATE
            stmt.executeUpdate("UPDATE Students SET grade='9th' WHERE name='Alice'");

            // DELETE
            stmt.executeUpdate("DELETE FROM Students WHERE name='Charlie'");

            // SELECT
            ResultSet rs = stmt.executeQuery("SELECT * FROM Students");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " +
                                   rs.getString("name") + " " +
                                   rs.getInt("age") + " " +
                                   rs.getString("grade"));
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}