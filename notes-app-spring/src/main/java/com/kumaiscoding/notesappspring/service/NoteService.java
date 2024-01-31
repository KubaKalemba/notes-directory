package com.kumaiscoding.notesappspring.service;

import com.kumaiscoding.notesappspring.entity.Note;

import java.util.List;

public interface NoteService {

    List<Note> findAll();

    Note findById(int id);

    Note save(Note note);

    void deleteById(int id);
}
