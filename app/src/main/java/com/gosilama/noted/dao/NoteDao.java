package com.gosilama.noted.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.gosilama.noted.model.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Query("SELECT * FROM notes LIMIT 1")
    Note[] getAnyNote();

    @Query("SELECT * FROM notes ORDER BY note_title ASC")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM notes WHERE id=:noteId")
    LiveData<Note> getNote(int noteId);

    @Query("DELETE FROM notes")
    void deleteAllNotes();

    @Query("DELETE FROM notes WHERE id=:noteId")
    void deleteNoteById(int noteId);

    @Delete
    void deleteNote(Note note);
}
