package LibraryProject.librarydemo.Repository;

import LibraryProject.librarydemo.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookTestRepository;


    @Test
    void canSaveBook() {
        //given
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("name1");
        book1.setAuthor("author1");
        book1.setCategory("category1");
        book1.setLanguage("language1");
        book1.setPublicationDate(1971);
        book1.setIsbn("isbn1");
        //when
        bookTestRepository.save(book1);
        //  then
        assertThat(bookTestRepository.getById(1L).getName()).isEqualTo(book1.getName());

    }
    
}