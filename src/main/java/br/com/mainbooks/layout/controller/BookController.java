package br.com.mainbooks.layout.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.mainbooks.layout.model.Book;
import br.com.mainbooks.layout.repositories.BookRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @RequestMapping("/")
    public String index(Model model) {
        return "redirect:list";
    }

    @GetMapping("/create")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("create");

        mv.addObject("book", new Book());
        return mv;
    }

    @PostMapping("create")
    public String create(Book book) {
        repository.save(book);

        return "redirect:/list";
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("list");
        var books = repository.findAll();

        mv.addObject("books", books);

        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        var book = repository.findById(id);

        ModelAndView mv = new ModelAndView("create");

        mv.addObject("book", book);

        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        var book = repository.findById(id);

        repository.delete(book);

        ModelAndView mv = new ModelAndView("list");
        var books = repository.findAll();

        mv.addObject("books", books);

        return mv;
    }
}
