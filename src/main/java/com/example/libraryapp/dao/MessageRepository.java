package com.example.libraryapp.dao;

import com.example.libraryapp.entity.Message;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface MessageRepository extends JpaRepository<Message,Long> {
    Page<Message> findByUserEmail(@RequestParam("userEmail") String userEmail, Pageable pageable);
    Page<Message> findByClosed(@RequestParam("closed") boolean closed,Pageable pageable);

}
