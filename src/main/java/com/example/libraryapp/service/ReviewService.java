package com.example.libraryapp.service;

import com.example.libraryapp.dao.ReviewRepository;
import com.example.libraryapp.entity.Review;
import com.example.libraryapp.requestmodels.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;


@Service
@Transactional
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception {
        Review validateReview=reviewRepository.findByUserEmailAndBookId(userEmail,reviewRequest.getBookId());
        if(validateReview!=null){
            throw new Exception("Review Already Exist");
        }
        Review review =new Review();
        review.setBookId(reviewRequest.getBookId());
        review.setDate(Date.valueOf(LocalDate.now()));
        review.setUserEmail(userEmail);
        review.setRating(reviewRequest.getRating());
        review.setReviewDescription(reviewRequest.getReviewDescription().map(Objects::toString).orElse(null));
        reviewRepository.save(review);
    }

    public Boolean validateUser(String userEmail,Long bookId){
        Review validateReview=reviewRepository.findByUserEmailAndBookId(userEmail,bookId);
        if(validateReview!=null){
            return true;
        }else {
            return false;
        }
    }
}
