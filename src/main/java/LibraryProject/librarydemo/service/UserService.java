package LibraryProject.librarydemo.service;
import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.model.BorrowBookSystem;
import LibraryProject.librarydemo.model.User;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User saveUser(User user);
    List<User> getUsers();
    User getUserById(long id);
    void deleteUser(long id);
    List<BorrowBookSystem> getAllBorrowedUserBooks(long id);
    List<BorrowBookSystem> getUserActiveBooks(long id);
    List<BorrowBookSystem> getUserBookHistory(long id);




}
