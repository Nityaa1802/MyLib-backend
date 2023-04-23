package com.example.libraryapp.controller;

import com.example.libraryapp.requestmodels.ReviewRequest;
import com.example.libraryapp.service.ReviewService;
import com.example.libraryapp.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin("*")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PostMapping("/secure")
    public void postReview(@RequestHeader("Authorization") String token, @RequestBody ReviewRequest reviewRequest) throws Exception {
        System.out.println("review posted");
        String userEmail= ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if(userEmail==null){
            throw new Exception("UserEmail is missing");
        }
        reviewService.postReview(userEmail,reviewRequest);
    }
    @GetMapping("/secure/user/Book")
    public Boolean validateReview(@RequestHeader("Authorization") String token,@RequestParam Long bookId){

        String userEmail=ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        return reviewService.validateUser(userEmail,bookId);
    }
}
