package LibraryProject.librarydemo.Implementations;
import LibraryProject.librarydemo.Repository.UserRepository;
import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.model.BorrowBookSystem;
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
    	Optional<User> user  = userRepository.findById(id);
//    	List<BorrowBookSystem> borrowedBooks = user.get().getBorrowedBooksCard();
//    	for (BorrowBookSystem borrow : borrowedBooks) {
//    		book.assignUser(null);
//    		book.setAvailable();
//    		book.setWasTaken(false);
//    	}
        userRepository.deleteById(id);
    }

	@Override
	public List<BorrowBookSystem> getAllBorrowedUserBooks(long id) {
		   Optional<User> user = userRepository.findById(id);
	       return user.get().getBorrowedBooksCard();
	}

	@Override
	public List<BorrowBookSystem> getUserActiveBooks(long id) {
			List<BorrowBookSystem> allUserBooks = getAllBorrowedUserBooks(id);
		   List<BorrowBookSystem> activeUserBooks = new ArrayList<>();
		   for (BorrowBookSystem borrow : allUserBooks) {
			   if(!borrow.isReturned()) {
				   activeUserBooks.add(borrow);
			   }
		   }
	       return activeUserBooks;
	}

	@Override
	public List<BorrowBookSystem> getUserBookHistory(long id) {
		List<BorrowBookSystem> allUserBooks = getAllBorrowedUserBooks(id);
		   List<BorrowBookSystem> userBookHistory = new ArrayList<>();
		   for (BorrowBookSystem borrow : allUserBooks) {
			   if(borrow.isReturned()) {
				   userBookHistory.add(borrow);
			   }
		   }
	       return userBookHistory;
	}

	}






