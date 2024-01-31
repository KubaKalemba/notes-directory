package com.kumaiscoding.notesappspring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    public Note() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String contents) {
        this.content = contents;
    }
}
