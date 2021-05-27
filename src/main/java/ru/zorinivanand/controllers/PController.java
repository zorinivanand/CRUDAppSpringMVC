package ru.zorinivanand.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zorinivanand.dao.PersonDAO;
import ru.zorinivanand.models.Person1;

import javax.validation.Valid;

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
    public String create(@ModelAttribute("person")@Valid Person1 person1,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person1);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person",personDAO.show(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person")@Valid Person1 person1, BindingResult bindingResult,@PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "people/edit";
        personDAO.update(id,person1);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
