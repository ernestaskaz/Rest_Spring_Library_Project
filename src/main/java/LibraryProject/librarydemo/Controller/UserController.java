package LibraryProject.librarydemo.Controller;

import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.model.BorrowBookSystem;
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

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long userId) {
    return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
        return new ResponseEntity<String>("User deleted", HttpStatus.OK);
    }

    @GetMapping("{id}/borrowedbooks")
    public List<BorrowBookSystem> getBorrowedBooks(@PathVariable("id") long userId){
        return userService.getAllBorrowedUserBooks(userId);
    }
    
    @GetMapping("{id}/activebooks")
    public List<BorrowBookSystem> getUserActiveBooks(@PathVariable("id") long userId){
        return userService.getUserActiveBooks(userId);
    }
    
    @GetMapping("{id}/bookhistory")
    public List<BorrowBookSystem> getUserBookHistory(@PathVariable("id") long userId){
        return userService.getUserBookHistory(userId);
    }


}
