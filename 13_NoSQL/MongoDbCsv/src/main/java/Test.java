import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;

public class Test
{
    private static final String path = "src/main/resources/mongo.csv";
    private static final String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

    public static void main(String[] args) throws IOException
    {
        MongoClient mongoClient = new MongoClient("127.0.0.1",27017);
        MongoDatabase database = mongoClient.getDatabase("local");

        MongoCollection<Document> collection = database.getCollection("students");
        collection.drop();

        List<String> allStudents = Files.readAllLines(Paths.get(path));
        allStudents.forEach(s -> collection.insertOne(parseCsvLines(s)));

        System.out.println("Общее количество студентов: " + collection.countDocuments());

        var queryAge = BsonDocument.parse("{age: {$gt: 40}}");
        System.out.println("Количество студентов старше 40 лет: " + collection.countDocuments(queryAge));

        var queryName = BsonDocument.parse("{age: 1}");
        collection.find().sort(queryName).limit(1).forEach((Consumer<Document>) doc ->
                System.out.println("Имя самого молодого студента: " + doc.getString("name")));

        var queryCourses = BsonDocument.parse("{age: -1}");
        collection.find().sort(queryCourses).limit(1).forEach((Consumer<Document>) document ->
                System.out.println("Список курсов самого старого студента: " + document.getString("courses")));
    }

    private static Document parseCsvLines(String str)
    {
        if (str != null)
        {
            String[] array = str.split(regex, -1);
            return new Document()
                    .append("name",array[0])
                    .append("age",Integer.parseInt(array[1]))
                    .append("courses",array[2].replaceAll("\"",""));
        }
        return null;
    }
}
