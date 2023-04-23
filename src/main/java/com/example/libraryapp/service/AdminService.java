package com.example.libraryapp.service;

import com.example.libraryapp.dao.BookRepository;
import com.example.libraryapp.dao.CheckoutRepository;
import com.example.libraryapp.dao.ReviewRepository;
import com.example.libraryapp.entity.Book;
import com.example.libraryapp.entity.Checkout;
import com.example.libraryapp.requestmodels.AddBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AdminService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CheckoutRepository checkoutRepository;


    public void postBook(AddBookRequest addBookRequest){
        Book book=new Book();
        book.setCopiesAvailable(addBookRequest.getCopies());
        book.setCopies(addBookRequest.getCopies());
        book.setAuthor(addBookRequest.getAuthor());
        book.setImg(addBookRequest.getImg());
        book.setDescription(addBookRequest.getDescription());
        book.setTitle(addBookRequest.getTitle());
        book.setCategory(addBookRequest.getCategory());
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) throws Exception {
        if(bookRepository.findById(bookId)==null){
            throw new Exception("Book doesn't Exist");
        }
        bookRepository.deleteById(bookId);
        checkoutRepository.deleteAllByBookId(bookId);
        reviewRepository.deleteAllByBookId(bookId);
    }

    public void increaseQuantity(Long bookId)throws Exception{
        Optional<Book> book=bookRepository.findById(bookId);
        if(!book.isPresent()){
            throw new Exception("Book doesn't Exist");
        }
        book.get().setCopies(book.get().getCopies()+1);
        book.get().setCopiesAvailable(book.get().getCopiesAvailable()+1);
        bookRepository.save(book.get());
    }
    public void decreaseQuantity(Long bookId)throws Exception{
        Optional<Book> book=bookRepository.findById(bookId);
        if(!book.isPresent()){
            throw new Exception("Book doesn't Exist");
        }
        if(book.get().getCopiesAvailable()>0){
            book.get().setCopies(book.get().getCopies()-1);
            book.get().setCopiesAvailable(book.get().getCopiesAvailable()-1);
            bookRepository.save(book.get());
        }
    }
/*We should use @Transactional only if we have to manipulat data between multiple database
 or table or repositories, service should rollback transaction if one repository faild to
  insert data for exemple, in this case i think it's best practices to add @Transctional on services level.

 */
}
