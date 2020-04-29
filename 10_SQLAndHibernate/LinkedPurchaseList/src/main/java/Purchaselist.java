import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Purchaselist
{

    @EmbeddedId
    private PurchaselistId id;
    private int price;
    @Column(name = "subscription_date")
    private Date subscriptionDate ;

    public PurchaselistId getId() {
        return id;
    }

    public void setId(PurchaselistId id) {
        this.id = id;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public Date getSubscriptionDate() {
        return subscriptionDate;
    }
    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
@Embeddable
class PurchaselistId implements Serializable
{
    public PurchaselistId() {
    }
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