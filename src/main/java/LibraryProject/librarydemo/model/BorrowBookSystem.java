package LibraryProject.librarydemo.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class BorrowBookSystem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dateTaken = null;
    private LocalDate dateToReturn = null;
    private LocalDate dateReturned = null;
	private boolean canBeExtended = true;
	private boolean isReturned = false;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnoreProperties({"borrowedBooksCard"})
    private User user;
    @ManyToOne
    @JsonIgnoreProperties({"borrowedBooksCard"})
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
    
    public BorrowBookSystem() {
    	
    }
    
	public BorrowBookSystem(LocalDate dateTaken, LocalDate dateToReturn, User user, Book book) {
		super();
		this.dateTaken = dateTaken;
		this.dateToReturn = dateToReturn;
		this.user = user;
		this.book = book;
	}
	public LocalDate getDateTaken() {
		return dateTaken;
	}
	public void setDateTaken(LocalDate dateTaken) {
		this.dateTaken = dateTaken;
	}
	public LocalDate getDateToReturn() {
		return dateToReturn;
	}
	public void setDateToReturn(LocalDate dateToReturn) {
		this.dateToReturn = dateToReturn;
	}
	public boolean isCanBeExtended() {
		return canBeExtended;
	}
	public void setCanBeExtended(boolean canBeExtended) {
		this.canBeExtended = canBeExtended;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void removeUser(User user) {
		this.user = null;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	public void removeBook(Book book) {
		this.book = null;
	}
	public long getId() {
		return id;
	}
	
    public LocalDate getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(LocalDate dateReturned) {
		this.dateReturned = dateReturned;
	}

	public boolean isReturned() {
		return isReturned;
	}

	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}
    
    
    

}
