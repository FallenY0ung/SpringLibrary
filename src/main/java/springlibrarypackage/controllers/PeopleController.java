package springlibrarypackage.controllers;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springlibrarypackage.DAO.BookDAO;
import springlibrarypackage.DAO.PersonDAO;
import springlibrarypackage.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    public PeopleController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String showPeople(Model model) {
        model.addAttribute("people", personDAO.findAll());
        return "people/index";
    }

    @GetMapping("/create")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/create";
    }

    @PostMapping
    public String create(@ModelAttribute ("person") @Valid Person person, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "people/create";
        }

        personDAO.save(person);

        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", bookDAO.findByPersonId(id));
        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute ("person") @Valid Person person,BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.update(id,person);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }


}
