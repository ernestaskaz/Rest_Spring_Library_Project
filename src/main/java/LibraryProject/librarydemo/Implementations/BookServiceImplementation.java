package LibraryProject.librarydemo.Implementations;
import LibraryProject.librarydemo.Repository.BookRepository;
import LibraryProject.librarydemo.Repository.UserRepository;
import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.model.BorrowBookSystem;
import LibraryProject.librarydemo.model.User;
import LibraryProject.librarydemo.service.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class BookServiceImplementation implements BookService {

    private BookRepository bookRepository;
    private UserRepository userRepository;

    public BookServiceImplementation(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Book saveBook(Book book) {
       return bookRepository.save(book);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.get();
    }

    @Override
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

//    @Override
//    public void assignBookToUser(long bookId, long userId) {
//        Book book = bookRepository.getById(bookId);
//        User user = userRepository.getById(userId);
//        if (book.isAvailable()) {
//            if (user.getUserBooks().size() < 3 || user.getUserBooks() == null) {
//                book.assignUser(user);
//                user.addBooksToUser(book);
//                book.setAvailable();
//                setTakenAndReturnDates(book);
//                saveBook(book);
//            } else {
//
//                //TODO. google normali error praktika web aplikacijoje. Gal atskira returnentity/exception custom klasė? Ar struktūriškai turėčiau naudoti Response entity tik control?
//                throw new IllegalArgumentException("maximum number of books taken can't exceed 3 at a time");
//            }
//        } else { throw new IllegalArgumentException("book already taken");
//
//        }
//
//    }

//    @Override
//    public void assignBookToUserByGuid(UUID guid, long userId) {
//        Book book = bookRepository.getByGuid(guid);
//        User user = userRepository.getById(userId);
//        if (book.isAvailable()) {
//            if (user.getUserBooks().size() < 3 || user.getUserBooks() == null) {
//                book.assignUser(user);
//                user.addBooksToUser(book);
//                book.setAvailable();
//                setTakenAndReturnDates(book);
//                saveBook(book);
//            } else {
//
//                //TODO. google normali error praktika web aplikacijoje. Gal atskira returnentity/exception custom klasė? Ar struktūriškai turėčiau naudoti Response entity tik control?
//                throw new IllegalArgumentException("maximum number of books taken can't exceed 3 at a time");
//            }
//        } else { throw new IllegalArgumentException("book already taken");
//
//        }
//    }

    @Override
    public List<Book> searchBooksByAuthor(String author) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            if(book.getAuthor().contains(author)) {
                booksByAuthor.add(book);
            }
        }
        return booksByAuthor;
    }

    @Override
    public List<Book> searchBooksByName(String name) {
        List<Book> booksByName = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            if(book.getName().contains(name)) {
                booksByName.add(book);
            }
        }
        return booksByName;
    }

    @Override
    public List<Book> searchBooksByCategory(String category) {
        List<Book> booksByCategory = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            if(book.getCategory().contains(category)) {
                booksByCategory.add(book);
            }
        }
        return booksByCategory;
    }

    @Override
    public List<Book> searchBooksByLanguage(String language) {
        List<Book> booksByLanguage = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            if(book.getLanguage().contains(language)) {
                booksByLanguage.add(book);
            }
        }
        return booksByLanguage;
    }

    @Override
    public List<Book> searchBooksByIsbn(String isbn) {
        List<Book> booksByIsbn = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            if(book.getIsbn().contains(isbn)) {
                booksByIsbn.add(book);
            }
        }
        return booksByIsbn;
    }

    @Override
    public List<Book> searchBooksAvailable(boolean isAvailable) {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            if(book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    @Override
    public List<Book> searchBooksUnavailable(boolean isAvailable) {
        List<Book> unavailableBooks = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            if(!book.isAvailable()) {
                unavailableBooks.add(book);
            }
        }
        return unavailableBooks;
    }

//    @Override
//    public void setTakenAndReturnDates(Book book) {
//        book.setDateTaken(LocalDate.now());
//        book.setDateToReturn(LocalDate.now().plusMonths(1));
//
//    }

//    @Override
//    public void extendBook(long bookId) {
//        Book book = bookRepository.getById(bookId);
//        LocalDate dateToExtend = book.getDateToReturn();
//        if (!book.isWasTaken()) {
//            book.setDateToReturn(dateToExtend.plusMonths(1));
//            book.setWasTaken(true);
//            saveBook(book);
//        } else {
//            throw new IllegalArgumentException("can't extend more than once");
//
//        }
//    }

    @Override
    public Book getBookByGuid(UUID guid) {
        Book book = bookRepository.getByGuid(guid);
        return book;
    }

@Override
public List<BorrowBookSystem> getBookBorrowingHistory(long id) {
	Optional<Book> book = bookRepository.findById(id);
	return book.get().getBorrowedBooksCard();
  
}

}
