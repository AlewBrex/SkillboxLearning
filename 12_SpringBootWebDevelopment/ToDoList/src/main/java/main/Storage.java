package main;

import main.model.Thing;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage
{
    private static AtomicInteger currentId = new AtomicInteger(1);
    private static Hashtable<Integer,Thing> things = new Hashtable<>();

    public static List<Thing> getAllThings()
    {
        ArrayList<Thing> thingList = new ArrayList<Thing>();
        thingList.addAll(things.values());
        return thingList;
    }

    public static int addThing(Thing thing)
    {
        int id = currentId.incrementAndGet();
        thing.setId(id);
        things.put(id,thing);
        return id;
    }

    public static boolean putThing(Thing thing)
    {
        if(things.containsKey(thing.getId()))
        {
            things.put(thing.getId(),thing);
            return true;
        }
        return false;
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