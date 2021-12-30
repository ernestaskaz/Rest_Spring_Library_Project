package LibraryProject.librarydemo.Implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import LibraryProject.librarydemo.Repository.BookRepository;
import LibraryProject.librarydemo.Repository.BorrowRepository;
import LibraryProject.librarydemo.Repository.UserRepository;
import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.model.BorrowBookSystem;
import LibraryProject.librarydemo.model.User;
import LibraryProject.librarydemo.service.BorrowBookSystemService;

@Service
public class BorrowBookSystemServiceImplementation implements BorrowBookSystemService {
	
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private BorrowRepository borrowRepository;
	
	
	

    public BorrowBookSystemServiceImplementation(BorrowRepository borrowRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.borrowRepository = borrowRepository;
    	this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }


	@Override
	public void saveBorrow(long userId, long bookId) {
        Book book = bookRepository.getById(bookId);
        User user = userRepository.getById(userId);
        BorrowBookSystem borrow =  new BorrowBookSystem(LocalDate.now(),LocalDate.now().plusMonths(1), user, book);
        if (book.isAvailable()) {
            if (user.getUserBooks().size() < 3 || user.getUserBooks() == null) {
                book.assignUser(user);
                user.addBooksToUser(book);
                book.setAvailable();
                bookRepository.save(book);
                userRepository.save(user);
                borrowRepository.save(borrow);
            } else {

                //TODO. google normali error praktika web aplikacijoje. Gal atskira returnentity/exception custom klasė? Ar struktūriškai turėčiau naudoti Response entity tik control?
                throw new IllegalArgumentException("maximum number of books taken can't exceed 3 at a time");
            }
        } else { throw new IllegalArgumentException("book already taken");

        }

    }


	@Override
	public List<BorrowBookSystem> getAllBorrows() {
		return borrowRepository.findAll();
	}

	@Override
	public BorrowBookSystem getBorrowById(long id) {
	      Optional<BorrowBookSystem> borrow = borrowRepository.findById(id);
	        return borrow.get();
	}
	
	
//    @Override
//    public void setTakenAndReturnDates(BorrowBookSystem borrow) {
//        borrow.setDateTaken(LocalDate.now());
//        borrow.setDateToReturn(LocalDate.now().plusMonths(1));
//
//    }

   @Override
    public void extendBook(long borrowId) {
    	BorrowBookSystem borrow = borrowRepository.getById(borrowId);
        LocalDate dateToExtend = borrow.getDateToReturn();
        if (!borrow.isCanBeExtended()) {
            borrow.setDateToReturn(dateToExtend.plusMonths(1));
            borrow.setCanBeExtended(false);
            borrowRepository.save(borrow);
        } else {
            throw new IllegalArgumentException("can't extend more than once");

        }
    }
	
	



}
