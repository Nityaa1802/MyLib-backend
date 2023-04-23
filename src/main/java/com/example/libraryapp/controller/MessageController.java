package com.example.libraryapp.controller;

import com.example.libraryapp.entity.Message;
import com.example.libraryapp.requestmodels.AdminQuestionRequest;
import com.example.libraryapp.service.MessageService;
import com.example.libraryapp.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader("Authorization") String token, @RequestBody Message messageRequest){
        String userEmail= ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        messageService.postMessage(messageRequest,userEmail);
    }

    @PutMapping("/secure/admin/message")
    public void putMessage(@RequestHeader("Authorization") String token, @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception {
        String userEmail= ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        String admin =ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin==null||!admin.equals("admin")){
            throw new Exception("Administration page only");
        }
        messageService.putMessage(adminQuestionRequest,userEmail);
    }
}
