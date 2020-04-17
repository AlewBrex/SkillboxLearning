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
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        String hql = "From " + Course.class.getSimpleName() + " Where price > 120000";
        List<Course> courses = session.createQuery(hql).getResultList() ;
        courses.forEach(c-> System.out.println(c.getName() + " - " + c.getPrice()));

//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Course> query = builder.createQuery(Course.class);
//        Root<Course> root = query.from(Course.class);
//        query.select(root).where(builder.greaterThan(root.get("price"),100000))
//                .orderBy(builder.desc(root.get("price")));
//        List<Course> courseList = session.createQuery(query).setMaxResults(5).getResultList();
//        courseList.forEach(c -> System.out.println(c.getName() + "  " + c.getPrice()));
        sessionFactory.close();
    }
}