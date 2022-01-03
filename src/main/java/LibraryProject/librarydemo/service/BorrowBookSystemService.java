package LibraryProject.librarydemo.service;

import java.util.List;
import LibraryProject.librarydemo.model.BorrowBookSystem;

public interface BorrowBookSystemService {
	
	
	void saveBorrow(long userId, long bookId);
    List<BorrowBookSystem> getAllBorrows();
    BorrowBookSystem getBorrowById(long borrowId);
    void extendBook(long borrowId);
//    List<BorrowBookSystem> getUserBorrows(long id);
//    List<BorrowBookSystem> getBookBorrows(long id);
    void deleteBorrowById(long borrowId);
    void returnBorrowedBook(long borrowId);
 


}
