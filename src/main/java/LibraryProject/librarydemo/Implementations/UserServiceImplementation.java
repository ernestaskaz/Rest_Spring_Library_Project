package LibraryProject.librarydemo.Implementations;
import LibraryProject.librarydemo.Repository.UserRepository;
import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.model.User;
import LibraryProject.librarydemo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;


    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
       Optional<User> user = userRepository.findById(id);
       return user.get();
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Book> getUserBooks(long id) {
        Optional<User> user = userRepository.findById(id);
       return user.get().getUserBooks();
    }


}




