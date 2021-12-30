package LibraryProject.librarydemo.service;

import java.util.List;
import LibraryProject.librarydemo.model.BorrowBookSystem;

public interface BorrowBookSystemService {
	
	
   // BorrowBookSystem saveBorrow(BorrowBookSystem borrow, long userId, long bookId);
	void saveBorrow(long userId, long bookId);
    List<BorrowBookSystem> getAllBorrows();
    BorrowBookSystem getBorrowById(long id);
//    List<BorrowBookSystem> getUserBorrows(long id);
//    List<BorrowBookSystem> getBookBorrows(long id);
//    void deleteBorrowById(long id);
//    void assignBookToUser(long bookId, long userId);
//    void assignBookToUserByGuid(UUID guid, long userId);
//    void setTakenAndReturnDates(Book book);
//    void extendBook(long borrowId);

}
