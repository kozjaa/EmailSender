package com.message.message.repository;

import com.message.message.domain.Message;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends CassandraRepository<Message, UUID> {
    @Query("INSERT INTO message(id, email, title, content, magicnumber) values(?0, ?1, ?2, ?3, ?4) USING TTL 300")
    void saveMessage(UUID id, String email, String title, String content, Integer magicnumber);
}
