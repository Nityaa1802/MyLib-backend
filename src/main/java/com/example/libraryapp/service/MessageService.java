package com.example.libraryapp.service;

import com.example.libraryapp.dao.MessageRepository;
import com.example.libraryapp.entity.Message;
import com.example.libraryapp.requestmodels.AdminQuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    public void postMessage(Message messageRequest,String userEmail){
        Message message=new Message(messageRequest.getTitle(),messageRequest.getQuestion());
        message.setUserEmail(userEmail);
        messageRepository.save(message);
    }

    public void putMessage(AdminQuestionRequest adminQuestionRequest,String userEmail) throws Exception {
        Optional<Message> message=messageRepository.findById(adminQuestionRequest.getId());
        if(!message.isPresent()){
            throw new Exception("Message not found");
        }
        message.get().setResponse(adminQuestionRequest.getResponse());;
        message.get().setAdminEmail(userEmail);
        message.get().setClosed(true);
        messageRepository.save(message.get());
    }

}
