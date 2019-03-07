package com.gosilama.noted.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.gosilama.noted.dao.NoteDao;
import com.gosilama.noted.lib.DummyNotes;
import com.gosilama.noted.model.Note;

import java.util.List;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    private static volatile NoteDatabase INSTANCE;

    public static NoteDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDatabase.class, "note_database")
                            .addCallback(sRoomDbCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDbCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final NoteDao noteDao;
        List<Note> dummyNotes = DummyNotes.getNotes();


        PopulateDbAsync(NoteDatabase noteDatabase) {
            noteDao = noteDatabase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (noteDao.getAnyNote().length < 1) {
                for (int i = 0; i < dummyNotes.size(); i++) {
                    noteDao.insert(dummyNotes.get(i));
                }
            }

            return null;
        }
    }
}
