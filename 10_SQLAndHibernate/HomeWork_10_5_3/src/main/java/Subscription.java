import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "Subscriptions")
public class Subscription
{
    @EmbeddedId
    SubscriptionId id;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}

@Embeddable
class SubscriptionId implements Serializable
{
    public SubscriptionId(String studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
    @Column(name = "student_id")
    private String studentId;
    @Column(name = "course_id")
    private String courseId;

    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}