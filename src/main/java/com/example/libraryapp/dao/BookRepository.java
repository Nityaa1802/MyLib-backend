package com.example.libraryapp.dao;

import com.example.libraryapp.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);

    Page<Book> findByCategory(@RequestParam("category") String category,Pageable pageable);

    Optional<Book> findById(@RequestParam("id") Long id);

    @Query("SELECT book from Book book where book.id in :book_ids")
    List<Book> findBooksByBookIds(@Param("book_ids") List<Long> bookId);
}



