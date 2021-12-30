package LibraryProject.librarydemo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.model.BorrowBookSystem;
import LibraryProject.librarydemo.service.BorrowBookSystemService;

@RestController
@RequestMapping("/borrow")
public class BorrowBookSystemController {
	
	private BorrowBookSystemService borrowBookSystemService;
	
	public BorrowBookSystemController(BorrowBookSystemService borrowBookSystemService) {
		this.borrowBookSystemService = borrowBookSystemService;
		
	}
	
	
//	
//    @PostMapping("/{userId}/{bookId}")
//    public ResponseEntity<BorrowBookSystem> saveBorrow(@RequestBody BorrowBookSystem borrow, @PathVariable("userId") long userId, @PathVariable("bookId") long bookId) {
//     return new ResponseEntity<BorrowBookSystem>(borrowBookSystemService.saveBorrow(borrow, 0, 0), HttpStatus.CREATED);
//    }
	
	
    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<String> saveBorrow(@PathVariable("userId") long userId, @PathVariable("bookId") long bookId) {
     borrowBookSystemService.saveBorrow(userId, bookId);
     return new ResponseEntity<String>("success",  HttpStatus.OK);
    }
    @GetMapping
    public List<BorrowBookSystem> getAllBorrows() {
        return borrowBookSystemService.getAllBorrows();
    }

    @GetMapping("{id}")
    public ResponseEntity<BorrowBookSystem> getBorrowById(@PathVariable("id") long borrowId) {
        return new ResponseEntity<BorrowBookSystem>(borrowBookSystemService.getBorrowById(borrowId), HttpStatus.OK);
    }


}
