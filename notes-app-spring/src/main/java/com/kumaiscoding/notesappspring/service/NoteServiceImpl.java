package com.kumaiscoding.notesappspring.service;

import com.kumaiscoding.notesappspring.dao.NoteRepository;
import com.kumaiscoding.notesappspring.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note findById(int id) {
        Optional<Note> result = noteRepository.findById(id);

        Note note = null;

        if (result.isPresent()) {
            note = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + id);
        }
        return note;
    }

    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void deleteById(int id) {
        noteRepository.deleteById(id);
    }

}
