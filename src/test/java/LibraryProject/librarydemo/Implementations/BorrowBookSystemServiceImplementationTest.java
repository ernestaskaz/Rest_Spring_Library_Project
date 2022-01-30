package LibraryProject.librarydemo.Implementations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import LibraryProject.librarydemo.Repository.BookRepository;
import LibraryProject.librarydemo.Repository.BorrowRepository;
import LibraryProject.librarydemo.Repository.UserRepository;
import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.model.User;
import LibraryProject.librarydemo.service.BookService;
import LibraryProject.librarydemo.service.UserService;
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BorrowBookSystemServiceImplementationTest {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	BorrowRepository borrowRepository;
	private BorrowBookSystemServiceImplementation bookBorrowSystemServiceImpl;

	 

	 @BeforeEach
	     void setUp() {
		 bookBorrowSystemServiceImpl  = new BorrowBookSystemServiceImplementation(borrowRepository, bookRepository, userRepository);



	 }
	    

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
	    @DisplayName("can find all borrows")
	    void canFindAllBorrows() {
	    
	    	
	    	assertThat(bookBorrowSystemServiceImpl.getAllBorrows().size()).isEqualTo(1);
	    }

	}


