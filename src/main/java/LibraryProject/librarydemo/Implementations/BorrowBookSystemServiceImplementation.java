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
import LibraryProject.librarydemo.service.BookService;
import LibraryProject.librarydemo.service.BorrowBookSystemService;
import LibraryProject.librarydemo.service.UserService;

@Service
public class BorrowBookSystemServiceImplementation implements BorrowBookSystemService {
	
    private BorrowRepository borrowRepository;
    private UserService userService;
    private BookService bookService;
	
    
	  public BorrowBookSystemServiceImplementation(BorrowRepository borrowRepository, BookService bookService, UserService userService) {
	  this.borrowRepository = borrowRepository;
	  this.bookService = bookService;
	  this.userService = userService;
	}


	@Override
	public void saveBorrow(long userId, long bookId) {
        
      Book book = bookService.getBookById(bookId);
      User user = userService.getUserById(userId);
  
        if (book.isAvailable()) {
            if (userService.getUserActiveBooks(userId).size() < 3) {
                
            	BorrowBookSystem borrow =  new BorrowBookSystem(LocalDate.now(),LocalDate.now().plusMonths(1), user, book);
            	book.addBorrowedBooksCard(borrow);
                user.addBorrowedBooksCard(borrow);
                book.setAvailable(false);
                borrowRepository.save(borrow);
            } else {
      
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
	

   @Override
    public void extendBook(long borrowId) {
    	BorrowBookSystem borrow = borrowRepository.getById(borrowId);
        LocalDate dateToExtend = borrow.getDateToReturn();
        if (borrow.isCanBeExtended()) {
            borrow.setDateToReturn(dateToExtend.plusMonths(1));
            borrow.setCanBeExtended(false);
            borrowRepository.save(borrow);
        } else {
            throw new IllegalArgumentException("can't extend more than once");

        }
    }


@Override
public void deleteBorrowById(long borrowId) {
	Optional<BorrowBookSystem> borrow = borrowRepository.findById(borrowId);
    borrowRepository.deleteById(borrowId);
	
}


@Override
public void returnBorrowedBook(long borrowId) {
	BorrowBookSystem currentBorrow = borrowRepository.getById(borrowId);
	currentBorrow.setReturned(true);
	currentBorrow.setDateReturned(LocalDate.now());
	currentBorrow.getBook().setAvailable(true);
	borrowRepository.save(currentBorrow);
	
	
}





	
	



}
