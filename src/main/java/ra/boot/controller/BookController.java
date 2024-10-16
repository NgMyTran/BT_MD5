package ra.boot.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.boot.model.entity.Book;
import ra.boot.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

//    @GetMapping
//    public String listBooks(Model model) {
//        List<Book> books = bookService.findAll();
//        model.addAttribute("books", books);
//        return "book/list";
//    }
@GetMapping
public String listBooks(
        @RequestParam(defaultValue = "") String search,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "name") String sortBy,
        Model model) {

    Page<Book> bookPage = bookService.searchBooks(search, PageRequest.of(page, size, Sort.by(sortBy)));
    model.addAttribute("books", bookPage.getContent());
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", bookPage.getTotalPages());
    model.addAttribute("totalItems", bookPage.getTotalElements());
    model.addAttribute("search", search);
    model.addAttribute("sortBy", sortBy);
    return "book/list";
}

    @GetMapping("/add")
    public String createBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/add";
    }

    @PostMapping("/add")
    public String saveBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "book/edit";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id);
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }


}
