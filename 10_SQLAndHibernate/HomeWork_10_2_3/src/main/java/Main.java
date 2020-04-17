import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main
{
    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/skillbox?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String pass = "3s5a6dslipknot";

        try{
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name AS course_name, " +
                    "COUNT(student_name)/(SELECT COUNT(DISTINCT MONTH(subscription_date)) " +
                    "FROM purchaselist) AS avg_count FROM courses " +
                    "JOIN purchaselist ON courses.name = purchaselist.course_name " +
                    "GROUP BY courses.name ORDER BY subscription_date");
            while (resultSet.next()){
                String coursesName = resultSet.getString("course_name");
                String date = resultSet.getString("avg_count");
                System.out.println(coursesName + " - " + date);
            }
            statement.close();
            resultSet.close();
            connection.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}