package LibraryProject.librarydemo.Repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.model.BorrowBookSystem;
import LibraryProject.librarydemo.model.User;
@DataJpaTest
public class BorrowRepositoryTest {
	
    @Autowired
    private BorrowRepository borrowRepository;


    @Test
    @Disabled("needs more work")
    void canSaveBorrow() {
        //given
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("name1");
        book1.setAuthor("author1");
        book1.setCategory("category1");
        book1.setLanguage("language1");
        book1.setPublicationDate(1971);
        book1.setIsbn("isbn1");
        User user1 = new User();
        BorrowBookSystem borrow = new BorrowBookSystem(LocalDate.now(),LocalDate.now().plusMonths(1), user1, book1);
        //when
        borrowRepository.save(borrow);
        //  then
        assertThat(borrowRepository.findById(1L).get().getBook().getAuthor().compareTo(book1.getAuthor()));

    }

}
