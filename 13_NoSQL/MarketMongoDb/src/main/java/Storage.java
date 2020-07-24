import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;

public class Storage
{
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> marketsCollection;
    private MongoCollection<Document> goodsCollection;

    public void init()
    {
        mongoClient = new MongoClient("127.0.0.1",27017);
        database = mongoClient.getDatabase("local");

        marketsCollection = database.getCollection("Markets");
        marketsCollection.drop();

        goodsCollection = database.getCollection("Goods");
        goodsCollection.drop();
    }

    public void addMarket(String marketName)
    {
        Document dctMarket = new Document()
                .append("nameMarket", marketName);
        if (marketsCollection.find(dctMarket).cursor().hasNext())
        {
            System.out.println("Error! Market exists");
        }
        else
        {
            dctMarket.append("goodsList", Collections.emptyList());
            marketsCollection.insertOne(dctMarket);
        }
    }

    public void addGood(String goodName, int priceGood)
    {
        Document dctGood = new Document()
                .append("nameGood", goodName);
        if (goodsCollection.find(dctGood).cursor().hasNext())
        {
            System.out.println("Error! Good exists");
        }
        else
        {
            dctGood.append("price", priceGood);
            goodsCollection.insertOne(dctGood);
        }
    }

    public void displayGood(String goodName,String marketName)
    {
        Document displayDctGood = new Document()
                .append("nameGood", goodName);
        Document displayDctMarket = new Document()
                .append("nameMarket", marketName);
        if (goodsCollection.countDocuments(displayDctGood) != 0)
        {
            if (marketsCollection.countDocuments(displayDctMarket) != 0)
            {
                var findMarket = (Bson)marketsCollection.find(displayDctMarket).first();
                var pushGood = new Document()
                        .append("$push", new Document()
                                .append("goodsList", goodName));
                marketsCollection.updateOne(findMarket, pushGood);
            }
            else
            {
                System.out.println("Market not found");
            }
        }
        else
        {
            System.out.println("Product not found");
        }
    }

    public void statisticGoods()
    {
        if (marketsCollection.find().cursor().hasNext())
        {
            marketsCollection.aggregate(
                    Arrays.asList(
                            Aggregates.lookup("Goods", "goodsList", "nameGood", "goods"),
                            Aggregates.unwind("$goods"),
                            Aggregates.group("$nameMarket",
                                    Accumulators.avg("avgPrice", "$goods.price"),
                                    Accumulators.min("minPrice", "$goods.price"),
                                    Accumulators.max("maxPrice", "$goods.price"),
                                    Accumulators.sum("sum", "$goods.price")
                            )
                    )
            ).forEach((Consumer<Document>) document ->
            {
                int count = (int) ((int) document.getInteger("sum") / document.getDouble("avgPrice"));
                int countLtHundred = getAvg(document.getString("_id"));

                System.out.println("Магазин " + document.getString("_id"));
                System.out.println("\tВсего в магазине " + document.getString("_id") + " " + count + " товаров");
                System.out.println("\tСредняя цена товаров в магазине " + document.getString("_id") + " составляет - " + document.getDouble("avgPrice"));
                System.out.println("\tМинимальная цена товаров в магазине " + document.getString("_id") + " составляет - " + document.getInteger("minPrice"));
                System.out.println("\tМаксимальная цена товаров в магазине " + document.getString("_id") + " составляет - " + document.getInteger("maxPrice"));
                System.out.println("\tВсего в магазине " + document.getString("_id") + " " + countLtHundred + " товаров дешевле 100 рублей");
            });
        }
        else {
            System.out.println("Магазины ещё не заполнены товарами!");
        }
    }


    private int getAvg(String nameMarket)
    {
        final int[] count = {0};
        BsonDocument query = BsonDocument.parse("{\"goods.price\": {$lt: 100}}");
        marketsCollection.aggregate(
                Arrays.asList(
                        Aggregates.lookup("Goods","goodsList", "nameGood", "goods"),
                        Aggregates.unwind("$goods"),
                        Aggregates.match(query),
                        Aggregates.group("$nameMarket",
                                Accumulators.avg("avgPrice","$goods.price"),
                                Accumulators.min("minPrice","$goods.price"),
                                Accumulators.max("maxPrice","$goods.price"),
                                Accumulators.sum("sum","$goods.price")
                        )
                )
        ).forEach((Consumer<Document>) document ->
        {
            if (document.getString("_id").equals(nameMarket))
            {
                int countLtHundred = (int) ((int) document.getInteger("sum") / document.getDouble("avgPrice"));
                count[0] = countLtHundred;
            }
        });
        return count[0];
    }
}