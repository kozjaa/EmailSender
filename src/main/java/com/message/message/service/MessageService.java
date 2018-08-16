package com.message.message.service;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.utils.UUIDs;
import com.message.message.domain.Message;
import com.message.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Iterator;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private CassandraOperations cassandraOperations;
    @Autowired
    private JavaMailSender javaMailSender;;

    public void saveMessage(Message message) {
        message.setId(UUIDs.timeBased());
        messageRepository.saveMessage(message.getId(),message.getEmail(),message.getTitle()
                ,message.getContent(),message.getMagicNumber());
    }

    public void sendMessage(Integer magicNumber) {
        Select select = QueryBuilder.select().from("message").
                where(QueryBuilder.eq("magicnumber", magicNumber)).allowFiltering();
        List<Message> list = cassandraOperations.select(select, Message.class);
        for (Iterator<Message> message = list.iterator(); message.hasNext();){
            Message mes = message.next();
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @Override
                public void prepare (MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                    messageHelper.setTo(mes.getEmail());
                    messageHelper.setSubject(mes.getTitle());
                    messageHelper.setText(mes.getContent());
                }
            };
              javaMailSender.send(preparator);
            }
        messageRepository.deleteAll(list);
    }

    public List<Message> findMessagesByEmailValue(String emailValue) {
        Select select = QueryBuilder.select().from("message")
                .where(QueryBuilder.eq("email", emailValue)).allowFiltering();
        List<Message> list = cassandraOperations.select(select, Message.class);
        return list;
    }
}
