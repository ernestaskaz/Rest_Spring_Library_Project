package LibraryProject.librarydemo.Controller;

import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.model.User;
import LibraryProject.librarydemo.service.BookService;
import LibraryProject.librarydemo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);

    }
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();

    }

    //http://localhost:8080/users/1
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long userId) {
    return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
        return new ResponseEntity<String>("User deleted", HttpStatus.OK);
    }
    @GetMapping("{id}/mybooks")
    public List<Book> getUserBooks(@PathVariable("id") long userId){
        return userService.getUserBooks(userId);
    }


}
