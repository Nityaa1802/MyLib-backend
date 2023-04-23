package com.example.libraryapp.dao;

import com.example.libraryapp.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CheckoutRepository extends JpaRepository<Checkout,Long> {
    Checkout findByUserEmailAndBookId(String userEmail,Long bookId);

    List<Checkout> findBooksByUserEmail(String userEmail);



    @Modifying
    @Query("DELETE from Checkout where bookId= :book_id")
    void deleteAllByBookId(@Param("book_id") Long bookId);



//DELETE from Checkout where bookId= :book_id
}
