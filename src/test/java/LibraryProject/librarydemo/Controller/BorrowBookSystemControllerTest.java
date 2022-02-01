package LibraryProject.librarydemo.Controller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.model.BorrowBookSystem;
import LibraryProject.librarydemo.model.User;
import LibraryProject.librarydemo.service.BorrowBookSystemService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BorrowBookSystemControllerTest {
	
	  @Autowired
	    private MockMvc mockMvc;
	  
	  @MockBean
	  private BorrowBookSystemService borrowService;

	
	  
	  
	    private Book setBook(Book book, int number) {
	        book.setId(number);
	        book.setName("name" + number);
	        book.setAuthor("author" + number);
	        book.setCategory("category" + number);
	        book.setLanguage("language" + number);
	        book.setPublicationDate(1970 + number);
	        book.setIsbn("isbn" + number);
	        return book;

	    }
	    
	    private User setUser(User user, int number) {
	    	user.setId(number);
	    	user.setUserName("user" + number);
	    	return user;
	    	
	    }

	    @Test
	    @DisplayName("can create borrow")
	    @Order(1)
	    void canSaveBorrow() throws Exception {

	    	 mockMvc.perform(MockMvcRequestBuilders.post("/borrow/1/1")
            .accept(MediaType.ALL_VALUE))
            .andDo(print())
            .andExpect(status().isOk());
	    }
	    @Test
	    @DisplayName("cant borrow already taken book")
	    @Order(2)
	    void cantBorrowTakenBook() throws Exception {
	    	
	    	
	    }
	    @Test
	    @DisplayName("cant borrow more than 3")
	    @Order(3)
	    void cantTakeMoreThanThreeBooks() throws Exception {
	    	
	    }
	    @Test
	    @DisplayName("can find all borrows")
	    @Order(4)
	    void canFindAllBorrows() throws Exception {
	    	
	       	Book book = new Book();
	    	User user = new User();
	     	setBook(book, 1);
	    	setUser(user, 1);
	    	List<BorrowBookSystem> currentList = new ArrayList<>();
	    	BorrowBookSystem borrow = new BorrowBookSystem(LocalDate.now(),LocalDate.now().plusMonths(1), user, book);
	    	currentList.add(borrow);
	    	
	    	when(borrowService.getAllBorrows()).thenReturn(currentList);

	        mockMvc.perform(MockMvcRequestBuilders.get("/borrow")
	                .accept(MediaType.ALL_VALUE))
	                .andExpect(status().isOk())
	                .andDo(print())
	                .andExpect(jsonPath("$.[0].dateTaken").value(LocalDate.now().toString()));
	    	
	    }
	    @Test
	    @DisplayName("can find one borrow by id")
	    @Order(5)
	    void canFindBorrowById() throws Exception {
	    	
	       	Book book = new Book();
		    	User user = new User();
		     	setBook(book, 1);
		    	setUser(user, 1);
		    	BorrowBookSystem borrow = new BorrowBookSystem(LocalDate.now(),LocalDate.now().plusMonths(1), user, book);
		    
		    	
		    	when(borrowService.getBorrowById(0L)).thenReturn(borrow);

		        mockMvc.perform(MockMvcRequestBuilders.get("/borrow/0")
		                .accept(MediaType.ALL_VALUE))
		                .andExpect(status().isOk())
		                .andDo(print())
		                .andExpect(jsonPath("$.dateTaken").value(LocalDate.now().toString()));
	    	
	    }
	    @Test
	    @DisplayName("can extend book")
	    @Order(6)
	    void canExtendBook() throws Exception {
	    	
	    	
	     	Book book = new Book();
	    	User user = new User();
	     	setBook(book, 1);
	    	setUser(user, 1);
	    	BorrowBookSystem borrow = new BorrowBookSystem(LocalDate.now(),LocalDate.now().plusMonths(1), user, book);

    	when(borrowService.getBorrowById(0L)).thenReturn(borrow);

	        mockMvc.perform(MockMvcRequestBuilders.put("/borrow/extend/0")
	                .accept(MediaType.ALL_VALUE))
	                .andExpect(status().isOk())
	                .andDo(print())
	                .andExpect(content().string(org.hamcrest.Matchers.containsString(" extended until ")));
	    	
	    }
	    @Test
	    @DisplayName("cant extend book twice")
	    @Order(7)
	    void canNotExtendBookTwice() throws Exception {

	    	
	    }
	    @Test
	    @DisplayName("can delete borrow")
	    @Order(8)
	    void canDeleteBorrow() throws Exception {
	       	Book book = new Book();
		    	User user = new User();
		     	setBook(book, 1);
		    	setUser(user, 1);
		    	BorrowBookSystem borrow = new BorrowBookSystem(LocalDate.now(),LocalDate.now().plusMonths(1), user, book);

	    	when(borrowService.getBorrowById(0L)).thenReturn(borrow);

		        mockMvc.perform(MockMvcRequestBuilders.delete("/borrow/0")
		                .accept(MediaType.ALL_VALUE))
		                .andExpect(status().isOk())
		                .andDo(print())
		                .andExpect(content().string(org.hamcrest.Matchers.containsString("Borrow information deleted")));
	    	
	    }
	    	
	    
	    @Test
	    @DisplayName("can return book")
	    @Order(9)
	    void canReturnBook() throws Exception {
	    	
	       	Book book = new Book();
		    	User user = new User();
		     	setBook(book, 1);
		    	setUser(user, 1);
		    	BorrowBookSystem borrow = new BorrowBookSystem(LocalDate.now(),LocalDate.now().plusMonths(1), user, book);

	    	when(borrowService.getBorrowById(0L)).thenReturn(borrow);

		        mockMvc.perform(MockMvcRequestBuilders.put("/borrow/return/0")
		                .accept(MediaType.ALL_VALUE))
		                .andExpect(status().isOk())
		                .andDo(print())
		                .andExpect(content().string(org.hamcrest.Matchers.containsString(" is returned on ")));
	    	
	    }

}
