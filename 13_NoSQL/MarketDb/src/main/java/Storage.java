import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

public class Storage
{
    private MongoCollection<Document> marketsCollection;
    private MongoCollection<Document> goodsCollection;

    public void init()
    {
        MongoClient mongoClient = new MongoClient("127.0.0.1",27017);
        MongoDatabase database = mongoClient.getDatabase("local");

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
            dctMarket.append("goods", new ArrayList<String>());
            marketsCollection.insertOne(dctMarket);
        }
    }

    public void addGood(String goodName, int priceGood)
    {
        Document dctGood = new Document()
                .append("nameGood", goodName);
        if (goodsCollection.find(dctGood).cursor().hasNext()) {
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
                var findMarket = (Bson)marketsCollection.find(displayDctMarket);
                var pushGood = new Document()
                        .append("$push", new Document()
                                .append("goods", goodName));
                marketsCollection.updateOne(findMarket,pushGood);
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

    }
}