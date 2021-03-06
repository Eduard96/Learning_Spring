package org.example.controllers;

import org.example.model.Person;
import org.example.services.PersonServiceWithRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    /**
     * Если мы должны передать .html странице какую-либо информацию,
     * тогда используем в параметрах функции Model.
     * Если же контроллеру передается какая-либо информация,
     * в этом случае используем аннотацию @ModelAttribute
     */

    private final PersonServiceWithRepository personService;

    @Autowired
    public PeopleController(PersonServiceWithRepository personService) {
        this.personService = personService;
    }

    @GetMapping
    public String showPeople(Model model) {
        model.addAttribute("people", personService.getAllUsers());
        return "people/show_people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.getUserById(id));
        return "people/show_person";
    }

    @GetMapping("/new")
    public String addPersonPage(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping
    public String createNewPerson(@ModelAttribute @Valid Person person, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "people/new";
        personService.persist(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.getUserById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String editPerson(@PathVariable("id") int id,
                             @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "people/edit";
        personService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }

}
