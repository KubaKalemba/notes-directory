package com.kumaiscoding.notesappspring.controller;

import com.kumaiscoding.notesappspring.entity.Note;
import com.kumaiscoding.notesappspring.dao.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "http://localhost:5173")
public class NoteController {

    private NoteRepository noteRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/all")
    public List<Note> getAllNotes() {
        // Retrieve the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.toString());
        String username = authentication.getName();

        // Retrieve and return notes belonging to the authenticated user
        System.out.println("username: " + username);
        List<Note> userNotes = noteRepository.findByUserUsername(username);
        System.out.println(userNotes.size());
        return userNotes;
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable int id) {
        Optional<Note> note = noteRepository.findById(id);


        if (!note.isPresent()) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        return note.get();
    }

    @PostMapping("/all")
    public Note addNote(@RequestBody Note n) {
        noteRepository.save(n);
        return n;
    }

    @PutMapping("/all")
    public Note updateEmployee(@RequestBody Note note) {
        Note n = noteRepository.save(note);
        return n;
    }

    @DeleteMapping("/all/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isEmpty()) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        noteRepository.delete(note.get());
        return "Deleted employee with id of " + id;
    }


}
