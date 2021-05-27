package ru.zorinivanand.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zorinivanand.dao.PersonDAO;
import ru.zorinivanand.models.Person1;

@Controller
@RequestMapping("/people")
public class PController {
    private final PersonDAO personDAO;

    public PController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people",personDAO.index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id,Model model){
        model.addAttribute("person",personDAO.show(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person")Person1 person1){
        return "people/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("person")Person1 person1){
        personDAO.save(person1);
        return "redirect:/people";
    }
}
