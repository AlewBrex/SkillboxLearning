package main;

import main.model.Thing;
import main.model.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ThingController
{
    @Autowired
    private ThingRepository thingRepository;

    @GetMapping("/things/")
    public List<Thing> list()
    {
        Iterable<Thing> iterable = thingRepository.findAll();
        ArrayList<Thing> arrayList = new ArrayList<>();
        for (Thing thing : iterable)
        {
            arrayList.add(thing);
        }
        return arrayList;
    }

    @PostMapping("/things/")
    public ResponseEntity add(@RequestBody Thing thing)
    {
        thingRepository.save(thing);
        return ResponseEntity.status(HttpStatus.OK).body(thing);
    }

    @PutMapping("/things/")
    public ResponseEntity update(@RequestBody Thing thing)
    {
        Thing thingUp = thingRepository.save(thing);
        if (thingUp == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
       return ResponseEntity.status(HttpStatus.OK).body(thingUp);
    }

    @PutMapping("/things/{id}")
    public ResponseEntity updateId(@RequestParam String name,String description, @PathVariable int id)
    {
        Optional<Thing> thingUpId = thingRepository.findById(id);
        if (thingUpId == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        thingUpId.get().setName(name);
        thingUpId.get().setDescription(description);
        thingRepository.save(thingUpId.get());
        return new ResponseEntity(thingUpId.get(), HttpStatus.OK);
    }

    @PostMapping("/things/{id}")
    public ResponseEntity addId(@PathVariable int id)
    {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(id);
    }

    @DeleteMapping("/things/")
    public ResponseEntity delete(@RequestBody Thing thing)
    {
        thingRepository.delete(thing);
        return ResponseEntity.status(HttpStatus.OK).body(thing);
    }

    @DeleteMapping("/things/{id}")
    public ResponseEntity deleteId(@PathVariable int id)
    {
        Optional<Thing> deleteThing = thingRepository.findById(id);
        if (!deleteThing.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        thingRepository.deleteById(id);
        return new ResponseEntity(deleteThing.get(),HttpStatus.OK);
    }

    @GetMapping("/things/{id}")
    public ResponseEntity get(@PathVariable int id)
    {
        Optional<Thing> optionalThing = thingRepository.findById(id);
        if (!optionalThing.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalThing.get(),HttpStatus.OK);
    }
}