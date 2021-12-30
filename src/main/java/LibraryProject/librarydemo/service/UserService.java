package LibraryProject.librarydemo.service;
import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.model.BorrowBookSystem;
import LibraryProject.librarydemo.model.User;
import java.util.List;


public interface UserService {

    User saveUser(User user);
    List<User> getUsers();
    User getUserById(long id);
    void deleteUser(long id);
    List<Book> getUserBooks(long id);
    List<BorrowBookSystem> getUserBorrowedBooks(long id);




}
