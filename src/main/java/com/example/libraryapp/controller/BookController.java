package com.example.libraryapp.controller;

import com.example.libraryapp.dao.CheckoutRepository;
import com.example.libraryapp.entity.Book;
import com.example.libraryapp.responsemodels.ShelfCurrentLoansResponse;
import com.example.libraryapp.service.BookService;
import com.example.libraryapp.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/books")

public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CheckoutRepository checkoutRepository;

    @PutMapping("/secure/checkout")
    public Book checkoutBook (@RequestParam Long bookId, @RequestHeader(value= "Authorization") String token) throws Exception {
        String userEmail= ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        return bookService.checkoutBook(userEmail, bookId);
    }

    @GetMapping("/secure/ischeckedout/byuser")
    public boolean checkBookByUser(@RequestHeader(value= "Authorization") String token,@RequestParam Long bookId){
        String userEmail= ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        return bookService.checkoutBookByUser(userEmail, bookId);
    }

    @GetMapping("/secure/currentloans/count")
    public int currentloanscount(@RequestHeader(value= "Authorization") String token){

        String userEmail= ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        return bookService.currentLoansCount(userEmail);
    }

    @GetMapping("/secure/currentloans")
    public List<ShelfCurrentLoansResponse> currentLoans(@RequestHeader("Authorization") String token)throws Exception{
        String userEmail=ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        return bookService.currentLoans(userEmail);
    }

    @PutMapping("/secure/return")
    public void returnBook(@RequestHeader("Authorization") String token,@RequestParam Long bookId) throws Exception {
        String userEmail=ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
         bookService.returnBook(userEmail,bookId);
    }
    @PutMapping("/secure/renewloan")
    public void renewLoan(@RequestHeader("Authorization") String token,@RequestParam Long bookId)throws Exception{
        String userEmail=ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        bookService.renewLoan(userEmail,bookId);
    }
}
