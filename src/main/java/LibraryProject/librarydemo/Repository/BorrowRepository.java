package LibraryProject.librarydemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import LibraryProject.librarydemo.model.BorrowBookSystem;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowBookSystem, Long> {

}
