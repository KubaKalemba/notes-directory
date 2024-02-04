package com.kumaiscoding.notesappspring.controller;

import com.kumaiscoding.notesappspring.dao.UserRepository;
import com.kumaiscoding.notesappspring.entity.Note;
import com.kumaiscoding.notesappspring.dao.NoteRepository;
import com.kumaiscoding.notesappspring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserRepository userRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> u = userRepository.findByUsername(username);
        if(u.isEmpty()) {
            throw new RuntimeException();
        }
        User user = u.get();
        n.setUser(user);

        noteRepository.save(n);
        return n;
    }

    @PutMapping("/all/{id}")
    public Note updateNote(@RequestBody Note note, @PathVariable int id) {
        Optional<Note> existingNote = noteRepository.findById(id);

        if (existingNote.isEmpty()) {
            throw new RuntimeException();
        }

        Note noteToUpdate = existingNote.get();
        noteToUpdate.setContent(note.getContent()); // Update content or any other fields

        Note n = noteRepository.save(noteToUpdate);
        return n;
    }

    @DeleteMapping("/all/{id}")
    public String deleteNote(@PathVariable int id) {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isEmpty()) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        noteRepository.delete(note.get());
        return "Deleted employee with id of " + id;
    }


}
