package LibraryProject.librarydemo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Book> userBooks = new ArrayList<>();

    public void setId(int id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addBooksToUser(Book book) {
        userBooks.add(book);
    }

    public List<Book> getUserBooks() {
        return userBooks;
    }


}
