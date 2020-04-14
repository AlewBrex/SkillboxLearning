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
            ResultSet resultSet = statement.executeQuery("select name, " +
                    "(select count(*)/count(distinct(month(subscription_date))) " +
                    "from subscriptions where courses.id = subscriptions.course_id)" +
                    " as avgMonth from courses");
            while (resultSet.next()) {
                String coursesName = resultSet.getString("name");
                String date = resultSet.getString("avgMonth");
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