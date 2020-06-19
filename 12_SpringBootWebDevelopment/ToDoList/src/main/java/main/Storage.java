package main;

import main.model.Thing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage
{
    private static int currentId = 1;
    private static HashMap<Integer,Thing> things = new HashMap<>();

    public static List<Thing> getAllThings()
    {
        ArrayList<Thing> thingList = new ArrayList<Thing>();
        thingList.addAll(things.values());
        return thingList;
    }

    public static int addThing(Thing thing)
    {
        int id = currentId++;
        thing.setId(id);
        things.put(id,thing);
        return id;
    }

    public static Thing getThing(int thingId)
    {
        if (things.containsKey(thingId))
        {
            return things.get(thingId);
        }
        return null;
    }

    public static void deleteThing(int thingId)
    {
        if (things.containsKey(thingId))
        {
            things.remove(thingId);
        }
    }

    public static void deleteAllThings()
    {
        things.clear();
    }
}