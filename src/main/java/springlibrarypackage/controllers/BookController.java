package springlibrarypackage.controllers;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springlibrarypackage.DAO.BookDAO;
import springlibrarypackage.DAO.PersonDAO;
import springlibrarypackage.models.Book;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String showBook(Model model) {
        System.out.println("showBook called - before DAO");
        try {
            java.util.List<Book> books = bookDAO.findAll();
            System.out.println("showBook called - after DAO, size = " + books.size());
            model.addAttribute("shell", books);
            return "book/index";
        } catch (Exception e) {
            e.printStackTrace();   // <-- вот это даст реальную причину
            throw e;
        }
    }




    @GetMapping("/create")
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/create";
    }

    @PostMapping
    public String create(@ModelAttribute ("book") @Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "book/create";
        }

        bookDAO.save(book);

        return "redirect:/book";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("updatedBook") Book updatedbook) {
        Book book = bookDAO.show(id);

        model.addAttribute("book", book);
        model.addAttribute("people", personDAO.findAll());
        if (book.getPersonId() != null) {
            model.addAttribute("person", personDAO.show(book.getPersonId()));
        }

        return "book/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute ("book") @Valid Book book,BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "book/edit";
        }
        bookDAO.update(id,book);

        return "redirect:/book";
    }

    @PatchMapping("/{id}/personId")
    public String updatePersonId(@ModelAttribute ("book") @Valid Book book,BindingResult bindingResult, @PathVariable("id") int id) {
        bookDAO.updatePersonId(id,book);
        return "redirect:/book";
    }
    @PatchMapping("/{id}/release")
    public String release(@ModelAttribute ("book") @Valid Book book,BindingResult bindingResult, @PathVariable("id") int id) {
        bookDAO.release(id);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/book";
    }


}
