package io.company.library;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
@RequestMapping("/library")
public class LibraryWebController {

    @Autowired
    BookService bookService;


    @RequestMapping("/books")
    public String getWeb(Model containerToView) {
        //
        containerToView.addAttribute("booksfromController",
                bookService.getAllBooks());
        containerToView.addAttribute("aeatName", "https://sede.agenciatributaria.gob.es/");
        containerToView.addAttribute("aeat", -150);
        containerToView.addAttribute("changeInLove", false);
        return "showBooks";
    }

    @RequestMapping("/newBook")
    public String newBook() {
        return "newBook";
    }

    @RequestMapping("/insertBook")
    public String insertBook(Book book) {
        //
        bookService.createBook(book);
        return "redirect:books";
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(@RequestParam("bookIdFromView") Long id) {
        //
        bookService.deleteBookById(id);
        return "redirect:books";
    }

    @PostMapping("/replaceBook/{idFromView}")
    public String replaceBook(@PathVariable("idFromView") Long id, Book book){
            //
            Optional<Book> bookFound = bookService.findBookById(id);
            if (bookFound.isPresent()) {
                bookService.createBook(book);
                return "redirect:/library/books";
            } else  return "notfound.html";

    }

    @RequestMapping("/updateBook")
    public String updateBook(@RequestParam("bookIdFromView") Long id, Model model) {
        //examples of addAtrribute
        Optional<Book> bookFound = bookService.findBookById(id);
        if (bookFound.isPresent()) {
            model.addAttribute("bookfromController", bookFound.get());
            return "updateBook.html";
        } else return "notfound.html";
    }

    @RequestMapping("/javafaker")
    public String createFakeBooks( @RequestParam("qtyBooks") int qty ){

        //method while to create books
        //for(int i=0; i<qty; i++){
        //}
        //while( i < qty){}
        System.out.println("we are going to create " +  qty + " books");
        //int i = 0;
        for(int i=0; i<qty; i++){
            Faker faker = new Faker();
            String title = faker.book().title();
            String author = faker.book().author();
            int pages = faker.number().numberBetween(100, 1250);
            int year = faker.number().numberBetween(1500,2020);
            String isbn = String.format(faker.number().digits(5).toString() + "-ERUMR-" + faker.number().digits(5).toString());
            Book book = new Book(title,author,pages,year,isbn);
            bookService.createBook(book);
        }

        return "redirect:books";

    }

    @RequestMapping("/detailBook")
    public String detailBook(@RequestParam("bookIdFromView") Long id) {
        //
        //bookService.updateBookById(id);
        return "detailBook.html";
    }

}




