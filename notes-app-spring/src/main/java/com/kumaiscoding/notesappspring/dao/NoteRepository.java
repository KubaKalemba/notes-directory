package com.kumaiscoding.notesappspring.dao;

import com.kumaiscoding.notesappspring.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface NoteRepository extends JpaRepository<Note, Integer> {

    @Query("SELECT n FROM Note n WHERE n.user.username = :username")
    List<Note> findByUserUsername(@Param("username") String username);
}
