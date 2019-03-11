package com.gosilama.noted.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.gosilama.noted.model.Note;
import com.gosilama.noted.repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(Application application) {
        super(application);

        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() { return allNotes; }

    public void insert(Note note) { noteRepository.insert(note); }

    public void deleteAllNotes() { noteRepository.deleteAll(); }

    public void deleteNote(Note note) { noteRepository.delete(note); }

    public void deleteNoteById(int id) { noteRepository.deleteById(id); }
}
