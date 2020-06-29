package main.controllers;

import main.model.Thing;
import main.model.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController
{
    @Autowired
    ThingRepository thingRepository;

    @RequestMapping("/")
    public String index(Model model)
    {
        Iterable<Thing> thingIterable = thingRepository.findAll();
        ArrayList<Thing> things = new ArrayList<>();
        for (Thing thing : thingIterable)
        {
            things.add(thing);
        }
        model.addAttribute("things",things);
        model.addAttribute("thingsCount",things.size());
        return "index";
    }
}
