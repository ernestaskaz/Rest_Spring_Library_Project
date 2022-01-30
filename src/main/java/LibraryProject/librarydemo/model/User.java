package LibraryProject.librarydemo.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private List<Book> userBooks = new ArrayList<>();
    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<BorrowBookSystem> borrowedBooksCard = new ArrayList<>();
    

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
    
	public List<BorrowBookSystem> getBorrowedBooksCard() {
		return borrowedBooksCard;
	}
	
	public List<BorrowBookSystem> getActiveBorrowedBooks() {
		List<BorrowBookSystem> allUserBooks = getBorrowedBooksCard();
		   List<BorrowBookSystem> activeUserBooks = new ArrayList<>();
		   for (BorrowBookSystem borrow : allUserBooks) {
			   if(!borrow.isReturned()) {
				   activeUserBooks.add(borrow);
			   }
		   }
	       return activeUserBooks;
	}
	
	public void addBorrowedBooksCard(BorrowBookSystem borrow) {
		borrowedBooksCard.add(borrow);
	}


}
