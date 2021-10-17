package LibraryProject.librarydemo.service;


import LibraryProject.librarydemo.model.Book;
import java.util.List;
import java.util.UUID;


public interface BookService {

    Book saveBook(Book book);
    List<Book> getBooks();
    Book getBookById(long id);
    void deleteBookById(long id);
    void assignBookToUser(long bookId, long userId);
    void assignBookToUserByGuid(UUID guid, long userId);
    List<Book> searchBooksByAuthor(String author);
    List<Book> searchBooksByName(String name);
    List<Book> searchBooksByCategory(String category);
    List<Book> searchBooksByLanguage(String language);
    List<Book> searchBooksByIsbn(String isbn);
    List<Book> searchBooksAvailable(boolean isAvailable);
    List<Book> searchBooksUnavailable(boolean isAvailable);
    void setTakenAndReturnDates(Book book);
    void extendBook(long bookId);
    Book getBookByGuid(UUID guid);






}
