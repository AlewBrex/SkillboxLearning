import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Purchaselist
{
    @EmbeddedId PurchaselistId id;
    private int price;
    @Column(name = "subscription_date")
    private String subscriptionDate ;

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getSubscriptionDate() {
        return subscriptionDate;
    }
    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
@Embeddable
class PurchaselistId implements Serializable
{
    public PurchaselistId(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "course_name")
    private String courseName;

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}