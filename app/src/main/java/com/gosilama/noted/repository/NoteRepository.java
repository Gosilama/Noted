package com.gosilama.noted.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.gosilama.noted.dao.NoteDao;
import com.gosilama.noted.data.NoteDatabase;
import com.gosilama.noted.model.Note;

import java.util.List;

public class NoteRepository {
    private static NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getDatabase(application);

        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() { return  allNotes; }

    public static LiveData<Note> getNote(int id) {
        return noteDao.getNote(id);
    }

    public void insert(Note note) {
        new insertAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new deleteAsyncTask(noteDao).execute(note);
    }

    public void deleteById(int id) {
        deleteByIdAsyncTask(id, noteDao);
    }

    private static class insertAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao insertTaskDao;

        insertAsyncTask(NoteDao noteDao) { insertTaskDao = noteDao; }

        @Override
        protected Void doInBackground(Note... notes) {
            insertTaskDao.insert(notes[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao deleteTaskDao;

        deleteAsyncTask(NoteDao noteDao) { deleteTaskDao = noteDao; }

        @Override
        protected Void doInBackground(Note... notes) {
            deleteTaskDao.deleteNote(notes[0]);
            return null;
        }
    }

    private static void deleteByIdAsyncTask(final int id, NoteDao noteDao) {
        final NoteDao deleteByIdTaskDao = noteDao;
        final LiveData<Note> note = getNote(id);

        if (note != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    deleteByIdTaskDao.deleteNoteById(id);
                    return null;
                }
            }.execute();
        }
    }
}
