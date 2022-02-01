package LibraryProject.librarydemo.Implementations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import LibraryProject.librarydemo.Repository.BookRepository;
import LibraryProject.librarydemo.Repository.BorrowRepository;
import LibraryProject.librarydemo.Repository.UserRepository;
import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.model.User;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BorrowBookSystemServiceImplementationTest {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BorrowRepository borrowRepository;
	private BorrowBookSystemServiceImplementation bookBorrowSystemServiceImpl;

	 

	 @BeforeEach
	     void setUp() {
		 bookBorrowSystemServiceImpl  = new BorrowBookSystemServiceImplementation(borrowRepository, bookRepository, userRepository);



	 }
	    
	 	// generates book with name1 author1 category1 etc. depending on passed in INT
	    Book setBook(Book book, int number) {
	        book.setId(number);
	        book.setName("name" + number);
	        book.setAuthor("author" + number);
	        book.setCategory("category" + number);
	        book.setLanguage("language" + number);
	        book.setPublicationDate(1970 + number);
	        book.setIsbn("isbn" + number);
	        return book;

	    }
	    // same as book
	    User setUser(User user, int number) {
	    	user.setId(number);
	    	user.setUserName("user" + number);
	    	return user;
	    	
	    }
	    

	    
	    
	    @Test
	    @DisplayName("Should save Borrow Book System card information with user and book info")
	    void canSaveBorrow() {
	    	//given
	    	Book book = new Book();
	    	User user = new User();
	    	setBook(book, 1);
	    	setUser(user, 1);
	    	bookRepository.save(book);
	    	userRepository.save(user);
	    	//when saving one borrow
	    	bookBorrowSystemServiceImpl.saveBorrow(user.getId(), book.getId());
	    	//then
	        assertThat(bookBorrowSystemServiceImpl.getBorrowById(1L).getBook().getAuthor()).isEqualTo(book.getAuthor());
	 	   
	    }
	    
	    @Test
	    @DisplayName("cant take more than 3 books")
	    void cantTakeMoreThanThreeBooks() {
	    	//given
	    	Book book1 = new Book();

	    	Book book2 = new Book();
	    	Book book3 = new Book();
	    	Book book4 = new Book();
	    	User user = new User();
	    	setBook(book1, 1);
	    	setBook(book2, 2);
	    	setBook(book3, 3);
	    	setBook(book4, 4);
	    	setUser(user, 1);
	    	bookRepository.save(book1);
	    	bookRepository.save(book2);
	    	bookRepository.save(book3);
	    	bookRepository.save(book4);
	    	userRepository.save(user);
	   
	    	//when saving one borrow
	    	bookBorrowSystemServiceImpl.saveBorrow(user.getId(), book1.getId());
	    	bookBorrowSystemServiceImpl.saveBorrow(user.getId(), book2.getId());
	    	bookBorrowSystemServiceImpl.saveBorrow(user.getId(), book3.getId());
	    
	    
	    	Throwable exception = assertThrows(IllegalArgumentException.class, () -> bookBorrowSystemServiceImpl.saveBorrow(user.getId(), book4.getId()));
		        //then
		        assertEquals("maximum number of books taken can't exceed 3 at a time", exception.getMessage());
	 	   
	    }	
	    
	    
	    @Test
	    @DisplayName("cant take the book that is taken")
	    void cantBorrowTakenBook() {
	    	//given
	    	Book book1 = new Book();
	    	User user1 = new User();
	    	User user2 = new User();
	    	setBook(book1, 1);
	    	setUser(user1, 1);
	    	setUser(user2, 2);
	    	bookRepository.save(book1);
	    	userRepository.save(user1);
	      	userRepository.save(user2);
	    	//when saving one borrow
	    	bookBorrowSystemServiceImpl.saveBorrow(user1.getId(), book1.getId());
	
	    
	    	Throwable exception = assertThrows(IllegalArgumentException.class, () -> bookBorrowSystemServiceImpl.saveBorrow(user2.getId(), book1.getId()));
		        //then
		        assertEquals("book already taken", exception.getMessage());
	 	   
	    }	
	    
	    @Test
	    @DisplayName("can find all borrows")
	    void canFindAllBorrows() {
	    
	    	Book book = new Book();
	    	User user = new User();
	    	setBook(book, 1);
	    	setUser(user, 1);
	    	bookRepository.save(book);
	    	userRepository.save(user);
	    	//when saving one borrow
	    	bookBorrowSystemServiceImpl.saveBorrow(user.getId(), book.getId());
	    	assertThat(bookBorrowSystemServiceImpl.getAllBorrows().size()).isEqualTo(1);
	    }
	    
	    @Test
	    @DisplayName("can find one borrow")
	    void canFindBorrowById() {
	    	Book book = new Book();
	    	User user = new User();
	    	setBook(book, 1);
	    	setUser(user, 1);
	    	bookRepository.save(book);
	    	userRepository.save(user);
	    	//when saving one borrow
	    	bookBorrowSystemServiceImpl.saveBorrow(user.getId(), book.getId());
	    	assertThat(bookBorrowSystemServiceImpl.getBorrowById(1L).getBook().getAuthor()).isEqualTo(book.getAuthor());
	    }
	    
	    @Test
	    @DisplayName("can extend book once")
	    void canExtendBook() {
	    	Book book = new Book();
	    	User user = new User();
	    	setBook(book, 1);
	    	setUser(user, 1);
	    	bookRepository.save(book);
	    	userRepository.save(user);
	    	// on saveBorrow dateToReturn is set +1 month. So if book is to be extended, should be +2 months. Does not work with currentdate+2months, 3 days 
	    	bookBorrowSystemServiceImpl.saveBorrow(user.getId(), book.getId());
	    	LocalDate currentDateToReturn = bookBorrowSystemServiceImpl.getBorrowById(1L).getDateToReturn();
	    	bookBorrowSystemServiceImpl.extendBook(1L);
	    	assertThat(bookBorrowSystemServiceImpl.getBorrowById(1L).getDateToReturn()).isEqualTo(currentDateToReturn.plusMonths(1));
	    	
	    	
	    }
	    
	    @Test
	    @DisplayName("can not extend book twice or more")
	    void canNotExtendBookTwice() {
	    	Book book = new Book();
	    	User user = new User();
	    	setBook(book, 1);
	    	setUser(user, 1);
	    	bookRepository.save(book);
	    	userRepository.save(user);
	    	// on saveBorrow dateToReturn is set +1 month. So if book is to be extended, should be +2 months.
	    	bookBorrowSystemServiceImpl.saveBorrow(user.getId(), book.getId());
	    	bookBorrowSystemServiceImpl.extendBook(1L);
	    	
	        Throwable exception = assertThrows(IllegalArgumentException.class, () -> bookBorrowSystemServiceImpl.extendBook(1L));
	        //then
	        assertEquals("can't extend more than once", exception.getMessage());
	    	
	    //	assertThat(bookBorrowSystemServiceImpl.getBorrowById(1L).getDateToReturn()).isEqualTo(LocalDate.now().plusMonths(2));
	    	
	    	
	    }
	    
	    @Test
	    @DisplayName("borrow can be deleted")
	    void canDeleteBorrow() {
	    	
	    	Book book = new Book();
	    	User user = new User();
	    	setBook(book, 1);
	    	setUser(user, 1);
	    	bookRepository.save(book);
	    	userRepository.save(user);
	    	bookBorrowSystemServiceImpl.saveBorrow(user.getId(), book.getId());
	    	bookBorrowSystemServiceImpl.deleteBorrowById(1L);
	    	assertThat(bookBorrowSystemServiceImpl.getAllBorrows().size()).isEqualTo(0);
	    }
	    
	    @Test
	    @DisplayName("book can be returned")
	    void canReturnBook() {
	    	
	    	Book book = new Book();
	    	User user = new User();
	    	setBook(book, 1);
	    	setUser(user, 1);
	    	bookRepository.save(book);
	    	userRepository.save(user);
	    	bookBorrowSystemServiceImpl.saveBorrow(user.getId(), book.getId());
	    	bookBorrowSystemServiceImpl.returnBorrowedBook(1L);
	    	assertThat(bookBorrowSystemServiceImpl.getBorrowById(1L).getDateReturned()).isEqualTo(LocalDate.now());
	    	
	    	
	    }
	    

	}


