package com.example.libraryapp.controller;

import com.example.libraryapp.requestmodels.AddBookRequest;
import com.example.libraryapp.service.AdminService;
import com.example.libraryapp.service.BookService;
import com.example.libraryapp.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/secure/add/book")
    public void postBook(@RequestHeader("Authorization") String token, @RequestBody AddBookRequest addBookRequest)throws Exception{
        String admin= ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if(admin==null||!admin.equals("admin")){
            throw  new Exception("Administration page only");
        }
        adminService.postBook(addBookRequest);
    }

    @PutMapping("/secure/decrease/book/quantity")
    public  void decreaseQuantity(@RequestParam("bookId") Long bookId,@RequestHeader("Authorization") String token) throws Exception {
        String admin= ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if(admin==null||!admin.equals("admin")){
            throw  new Exception("Administration page only");
        }
        adminService.decreaseQuantity(bookId);
    }
    @PutMapping("/secure/increase/book/quantity")
    public void increaseQuantity(@RequestParam("bookId") Long bookId,@RequestHeader("Authorization") String token)throws  Exception{
        String admin= ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if(admin==null||!admin.equals("admin")){
            throw  new Exception("Administration page only");
        }
        adminService.increaseQuantity(bookId);
    }
    @DeleteMapping("/secure/delete/book")
    public void deleteBook(@RequestParam("bookId") Long bookId,@RequestHeader("Authorization") String token)throws Exception{
        String admin= ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if(admin==null||!admin.equals("admin")){
            throw  new Exception("Administration page only");
        }
        adminService.deleteBook(bookId);
    }
}
