package LibraryProject.librarydemo.service;


import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.model.BorrowBookSystem;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
@Service
public interface BookService {

    Book saveBook(Book book);
    List<Book> getBooks();
    Book getBookById(long id);
    void deleteBookById(long id);
    List<Book> searchBooksByAuthor(String author);
    List<Book> searchBooksByName(String name);
    List<Book> searchBooksByCategory(String category);
    List<Book> searchBooksByLanguage(String language);
    List<Book> searchBooksByIsbn(String isbn);
    List<Book> searchBooksAvailable(boolean isAvailable);
    List<Book> searchBooksUnavailable(boolean isAvailable);
    Book getBookByGuid(UUID guid);
    List<BorrowBookSystem> getBookBorrowingHistory(long id);






}
