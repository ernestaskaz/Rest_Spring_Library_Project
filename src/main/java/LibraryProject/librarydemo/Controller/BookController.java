package LibraryProject.librarydemo.Controller;
import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.service.BookService;
import LibraryProject.librarydemo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/books")
public class BookController {
// Spring initializes NEW in background; @Autowired shows it to Spring (dependency). Autowired not needed with spring 4.3(?)+
    private BookService bookService;
    private UserService userService;
// autowires automatically through constructor(jeigu vienas). recommended approach over @autowired fields. e nsures that values are not null;
    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        return new ResponseEntity<Book>(bookService.saveBook(book), HttpStatus.CREATED);
    }
    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long bookId) {
        return new ResponseEntity<Book>(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<String>("Book deleted", HttpStatus.OK);

    } 
//TODO. http status ok? pergalvoti. peržiūrėti be if, jeigu pridėjimas failina (arba tą pačią knygą pridedi, vistiek book added returnina.
    @PutMapping("/{bookId}/user/{userId}")
    public ResponseEntity<String> assignBookToUser(@PathVariable("bookId") long bookId, @PathVariable("userId") long userId) {
        bookService.assignBookToUser(bookId, userId);
        return new ResponseEntity<String>("book taken by "+ userService.getUserById(userId).getUserName() + " until  " + bookService.getBookById(bookId).getDateToReturn(),  HttpStatus.OK);
    }

    @PutMapping("/assignbyguid/{guid}/{userId}")
    public ResponseEntity<String> assignBookToUserByGuid(@PathVariable("guid") UUID guid, @PathVariable("userId") long userId) {
        bookService.assignBookToUserByGuid(guid, userId);
        return new ResponseEntity<String>("book taken by "+ userService.getUserById(userId).getUserName() + " until  " + bookService.getBookByGuid(guid).getDateToReturn(),  HttpStatus.OK);
    }

    @GetMapping("/findbyguid")
    public ResponseEntity<Book> findBookByGuid(@RequestParam UUID guid) {
        bookService.getBookByGuid(guid);
        return new ResponseEntity<Book>(bookService.getBookByGuid(guid),  HttpStatus.OK);
    }


    @PutMapping("/{bookId}/extendbook")
    public ResponseEntity<String> extendBook(@PathVariable("bookId") long bookId) {
        bookService.extendBook(bookId);
        return new ResponseEntity<String>("book extended", HttpStatus.OK);
    }
    //TODO. dubliuojasi author. Bad SEO. Jeigu būtų button'ai pasirinkti search sritį..nebūtų dubliavimo. apgalvoti: galima be buttonu padaryti? iš implementation su IF į Controller?
    @GetMapping("/search/author")
    public ResponseEntity<List<Book>> searchBooksByAuthor(@RequestParam String author) {
        return new ResponseEntity<List<Book>>(bookService.searchBooksByAuthor(author), HttpStatus.OK);
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<Book>> searchBooksByName(@RequestParam String name) {
        return new ResponseEntity<List<Book>>(bookService.searchBooksByName(name), HttpStatus.OK);
    }

    @GetMapping("/search/category")
    public ResponseEntity<List<Book>> searchBooksByCategory(@RequestParam String category) {
        return new ResponseEntity<List<Book>>(bookService.searchBooksByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/search/language")
    public ResponseEntity<List<Book>> searchBooksBylanguage(@RequestParam String language) {
        return new ResponseEntity<List<Book>>(bookService.searchBooksByLanguage(language), HttpStatus.OK);
    }

    @GetMapping("/search/isbn")
    public ResponseEntity<List<Book>> searchBooksByIsbn(@RequestParam String isbn) {
        return new ResponseEntity<List<Book>>(bookService.searchBooksByIsbn(isbn), HttpStatus.OK);
    }

    @GetMapping("/search/isavailable")
    public ResponseEntity<List<Book>> searchBooksAvailable(boolean available) {
        return new ResponseEntity<List<Book>>(bookService.searchBooksAvailable(available), HttpStatus.OK);
    }

    @GetMapping("/search/unavailable")
    public ResponseEntity<List<Book>> searchBooksUnavailable(boolean available) {
        return new ResponseEntity<List<Book>>(bookService.searchBooksUnavailable(available), HttpStatus.OK);
    }

}