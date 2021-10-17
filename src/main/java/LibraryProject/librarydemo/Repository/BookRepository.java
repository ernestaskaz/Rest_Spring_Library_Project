package LibraryProject.librarydemo.Repository;

import LibraryProject.librarydemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    Book getByGuid(UUID guid);
}
