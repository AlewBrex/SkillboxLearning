import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        try(SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build())
        {
            Session session = sessionFactory.openSession();
            String hqlStudent = "FROM " + Student.class.getSimpleName();
            String hqlCourse = "FROM " + Course.class.getSimpleName();
            String hqlPurchase = "FROM " + Purchaselist.class.getSimpleName();

            List<Student> studentList = session.createQuery(hqlStudent).getResultList();
            List<Course> courseList = session.createQuery(hqlCourse).getResultList();
            List<Purchaselist> purchaselists = session.createQuery(hqlPurchase).getResultList();

            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();

            for (int i = 0; i < purchaselists.size(); i++) {
                LinkedPurchaseListId linkedPurchaseListId = new LinkedPurchaseListId();
                PurchaselistId pd = purchaselists.get(i).getId();
                String stName = pd.getStudentName();
                String csName = pd.getCourseName();

                for (int j = 0; j < studentList.size(); j++) {
                    String nameSt = studentList.get(j).getName();
                    int idSt = studentList.get(j).getId();
                    if (stName.equals(nameSt)){
                        linkedPurchaseListId.setStudentId(idSt);
                        break;
                    }
                }
                for (int r = 0; r < courseList.size(); r++) {
                    String nameCs = courseList.get(r).getName();
                    int idCs = courseList.get(r).getId();
                    if (csName.equals(nameCs)){
                        linkedPurchaseListId.setCourseId(idCs);
                        break;
                    }
                }
                linkedPurchaseList.setId(linkedPurchaseListId);
            }
            session.save(linkedPurchaseList);
            session.close();
        }
    }
}