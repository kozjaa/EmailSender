package com.message.message.controller;

import com.message.message.domain.Message;
import com.message.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping(value = "/api/message")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMessage(@RequestBody Message message){
        messageService.saveMessage(message);
    }

    @PostMapping(value = "/api/send")
    @ResponseStatus(HttpStatus.OK)
    public void sendMessagesByMagicNumber(@RequestParam(value = "magic", required = true) Integer magicNumber){
        messageService.sendMessage(magicNumber);
    }

    @GetMapping(value = "/api/messages/{emailValue}")
    public List<Message> findMessagesByEmailValue(@PathVariable String emailValue){
        return messageService.findMessagesByEmailValue(emailValue);
    }
}
