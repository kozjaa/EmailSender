package com.message.message.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("message")
public class Message {
    @PrimaryKey
    @JsonIgnore
    private UUID id;
    private String email;
    private String title;
    private String content;
    private Integer magicNumber;

    public Message(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(Integer magicNumber) {
        this.magicNumber = magicNumber;
    }
}
