package LibraryProject.librarydemo.Implementations;
import LibraryProject.librarydemo.Repository.BookRepository;
import LibraryProject.librarydemo.Repository.UserRepository;
import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;



@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookServiceImplementationTest {

        @Autowired
        private BookRepository bookRepository;
        @Autowired
        private UserRepository userRepository;
        private BookServiceImplementation bookServiceTest;
	
// create object

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

        @BeforeEach
            void setUp() {
            bookServiceTest = new BookServiceImplementation(bookRepository, userRepository);



        }


    @Test
    void canAddBook() {
            //given
            Book book1 = new Book();
            setBook(book1, 1);
            //when
            bookServiceTest.saveBook(book1);
            //then
            assertThat(bookServiceTest.getBookById(1L).getName()).isEqualTo(book1.getName());

    }



        @Test
        void canGetAllBooks() {
            //given
            Book book1 = new Book();
            setBook(book1, 1);
            bookServiceTest.saveBook(book1);

            bookServiceTest.getBooks();
            //then
            assertThat(bookServiceTest.getBooks()).isNotNull();


        }
        @Test
        void canDeleteBook() {
            //given
            Book book1 = new Book();
            setBook(book1, 1);
            bookServiceTest.saveBook(book1);
            // when
            bookServiceTest.deleteBookById(book1.getId());
            //then
            assertThat(bookServiceTest.getBooks().size()).isEqualTo(0);


        }

        @Test
        void canSearchByAuthor() {
            //given
            Book book1 = new Book();
            setBook(book1, 1);
            bookServiceTest.saveBook(book1);
            //when
            List<Book> mockList = bookServiceTest.searchBooksByAuthor("author1");
            //then
            assertThat(mockList.get(0).getAuthor()).isEqualTo(book1.getAuthor());
        }
        @Test
        void canSearchByName() {
            //given
            Book book1 = new Book();
            setBook(book1, 1);
            bookServiceTest.saveBook(book1);
            //when
            List<Book> mockList = bookServiceTest.searchBooksByName("name1");
            //then
            assertThat(mockList.get(0).getName()).isEqualTo(book1.getName());
        }
        @Test
        void canSearchByCategory() {
            //given
            Book book1 = new Book();
            setBook(book1, 1);
            bookServiceTest.saveBook(book1);
            //when
            List<Book> mockList = bookServiceTest.searchBooksByCategory("category1");
            //then
            assertThat(mockList.get(0).getCategory()).isEqualTo(book1.getCategory());

        }

        @Test
        void canSearchByLanguage() {
            //given
            Book book1 = new Book();
            setBook(book1, 1);
            bookServiceTest.saveBook(book1);
            //when
            List<Book> mockList = bookServiceTest.searchBooksByLanguage("language1");
            //then
            assertThat(mockList.get(0).getLanguage()).isEqualTo(book1.getLanguage());
        }

        @Test
        void canSearchByIsbn() {
            //given
            Book book1 = new Book();
            setBook(book1, 1);
            bookServiceTest.saveBook(book1);
            //when
            List<Book> mockList = bookServiceTest.searchBooksByIsbn("isbn1");
            //then
            assertThat(mockList.get(0).getIsbn()).isEqualTo(book1.getIsbn());
        }

        @Test
        void canSearchByBookAvailable() {
            //given
            Book book1 = new Book();
            setBook(book1, 1);
            bookServiceTest.saveBook(book1);
            //when
            List<Book> mockList = bookServiceTest.searchBooksAvailable(true);
            //then
            assertThat(mockList.get(0).isAvailable()).isEqualTo(true);
        }

//        @Test
//        void canAssignBooksToUser() {
//
//            //given
//            Book book1 = new Book();
//            setBook(book1, 1);
//            bookServiceTest.saveBook(book1);
//            Book book2 = new Book();
//            setBook(book2, 2);
//            bookServiceTest.saveBook(book2);
//            Book book3 = new Book();
//            setBook(book3, 3);
//            bookServiceTest.saveBook(book3);
//            Book book4 = new Book();
//            setBook(book4, 4);
//            bookServiceTest.saveBook(book4);
//            User user = new User();
//            user.setId(1);
//            user.setUserName("Jim");
//            userRepository.save(user);
//
//            //when
//            bookServiceTest.assignBookToUser(book1.getId() , user.getId());
//            bookServiceTest.assignBookToUser(book2.getId() , user.getId());
//            bookServiceTest.assignBookToUser(book3.getId() , user.getId());
//            //then
//            assertThat(bookServiceTest.getBookById(1L).getUser().getUserName()).isEqualTo(user.getUserName());
//            assertThat(userRepository.findById(1L).get().getUserBooks().size()).isEqualTo(3);
//
//        }
//
//    @Test
//    void cantAssignFourBooksToUser() {
//
//        //given
//        Book book1 = new Book();
//        setBook(book1, 1);
//        bookServiceTest.saveBook(book1);
//        Book book2 = new Book();
//        setBook(book2, 2);
//        bookServiceTest.saveBook(book2);
//        Book book3 = new Book();
//        setBook(book3, 3);
//        bookServiceTest.saveBook(book3);
//        Book book4 = new Book();
//        setBook(book4, 4);
//        bookServiceTest.saveBook(book4);
//        User user = new User();
//        user.setId(1);
//        user.setUserName("Jim");
//        userRepository.save(user);
//
//        //when cant get more than 3 books
//        bookServiceTest.assignBookToUser(book1.getId() , user.getId());
//        bookServiceTest.assignBookToUser(book2.getId() , user.getId());
//        bookServiceTest.assignBookToUser(book3.getId() , user.getId());
//        Throwable exception = assertThrows(IllegalArgumentException.class, () -> bookServiceTest.assignBookToUser(book4.getId() , user.getId()));
//        //then
//        assertEquals("maximum number of books taken can't exceed 3 at a time", exception.getMessage());
//
//
//    }
//    @Test
//    void canExtendBook() {
//            //given
//        Book book1 = new Book();
//        setBook(book1, 1);
//        bookServiceTest.saveBook(book1);
//        User user = new User();
//        user.setId(1);
//        user.setUserName("Jim");
//        userRepository.save(user);
//        bookServiceTest.assignBookToUser(book1.getId() , user.getId());
//        LocalDate currentDateToCompare = bookServiceTest.getBookById(book1.getId()).getDateToReturn();
//            //when
//        bookServiceTest.extendBook(book1.getId());
//            //then
//        assertThat(currentDateToCompare.plusMonths(1)).isEqualTo(bookServiceTest.getBookById(book1.getId()).getDateToReturn());
//
//    }
//
//    @Test
//    void cantExtendBookTwice() {
//        //given
//        Book book1 = new Book();
//        setBook(book1, 1);
//        bookServiceTest.saveBook(book1);
//        User user = new User();
//        user.setId(1);
//        user.setUserName("Jim");
//        userRepository.save(user);
//        bookServiceTest.assignBookToUser(book1.getId() , user.getId());
//        LocalDate currentDateToCompare = bookServiceTest.getBookById(book1.getId()).getDateToReturn();
//        //when
//        bookServiceTest.extendBook(book1.getId());
//        //then
//        Throwable exception = assertThrows(IllegalArgumentException.class, () -> bookServiceTest.extendBook(book1.getId()));
//        //then
//        assertEquals("can't extend more than once", exception.getMessage());
//
//    }
//
//    @Test
//    void canAssignBooksToUserByGuid() {
//
//        //given
//        Book book1 = new Book();
//        setBook(book1, 1);
//        bookServiceTest.saveBook(book1);
//        User user = new User();
//        user.setId(1);
//        user.setUserName("Jim");
//        userRepository.save(user);
//
//        //when 1 book
//        bookServiceTest.assignBookToUserByGuid(bookServiceTest.getBookById(book1.getId()).getGuid() , user.getId());
//        System.out.println("user books" + userRepository.findById(1L).get().getUserBooks().size());
//        System.out.println("user books" + userRepository.findById(1L).get().getUserBooks().get(0).getGuid());
//        //then
//        assertThat(userRepository.findById(1L).get().getUserBooks().get(0).getGuid()).isEqualTo(bookServiceTest.getBookById(book1.getId()).getGuid());
//
//    }



}