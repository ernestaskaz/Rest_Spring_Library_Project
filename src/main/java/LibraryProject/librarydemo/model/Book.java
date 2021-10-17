package LibraryProject.librarydemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
@Entity
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private UUID guid = UUID.randomUUID();
    private String name;
    private String author;
    private String category;
    private String language;
    private int publicationDate;
    private String isbn;
    private boolean isAvailable = true;
    private LocalDate dateTaken = null;
    private LocalDate dateToReturn = null;
    private boolean wasTaken;
    // recursive problem, jeigu abu entities neturi json ignore. šis json ignore leidžia GET users matyti knygas, bet ne knygose user'ius.
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public User getUser() {
        return user;
    }

    public void assignUser(User user) {
        this.user = user;
    }


    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable() {
        isAvailable = false;
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

    public boolean isWasTaken() {
        return wasTaken;
    }

    public void setWasTaken(boolean wasTaken) {
        this.wasTaken = wasTaken;
    }

}