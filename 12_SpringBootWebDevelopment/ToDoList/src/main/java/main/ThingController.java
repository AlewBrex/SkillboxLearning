package main;

import main.model.Thing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ThingController
{
    @GetMapping("/things/")
    public List<Thing> list()
    {
        return Storage.getAllThings();
    }

    @PostMapping("/things/")
    public int add(Thing thing)
    {
        return Storage.addThing(thing);
    }

    @PutMapping("/things/")
    public ResponseEntity update(@RequestBody Thing thing)
    {
       return ResponseEntity.status(HttpStatus.OK).body(Storage.putThing(thing));
    }

    @PutMapping("/things/{id}")
    public ResponseEntity updateId(@RequestParam String name,String description, @PathVariable int id)
    {
        Thing thingUp = Storage.getThing(id);
        if (thingUp == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        thingUp.setName(name);
        thingUp.setDescription(description);
        Storage.addThing(thingUp);
        return ResponseEntity.status(HttpStatus.OK).body(thingUp);
    }

    @PostMapping("/things/{id}")
    public ResponseEntity addId(@PathVariable int id)
    {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(id);
    }

    @DeleteMapping("/things/")
    public void delete()
    {
        Storage.deleteAllThings();
    }

    @DeleteMapping("/things/{id}")
    public void delete(@PathVariable int id)
    {
        Storage.deleteThing(id);
    }

    @GetMapping("/things/{id}")
    public ResponseEntity get(@PathVariable int id)
    {
        Thing thing = Storage.getThing(id);
        if (thing == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(thing,HttpStatus.OK);
    }
}