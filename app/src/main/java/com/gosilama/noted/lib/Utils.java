package com.gosilama.noted.lib;

import com.gosilama.noted.model.Note;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public final class Utils {
    private Utils(){ }

    private static final List<Note> notes = new ArrayList<>();

    public static List<Note> getDummyNotes() {
        for (int i = 0; i <= 5; i++) {
            String title = String.format("title %s", i);
            String content = String.format("content %s", i);
            String userId = String.format("user_id_%s", i);
            Note note = new Note();

            note.setUserId(userId);
            note.setNoteTitle(title);
            note.setNoteContent(content);
            note.setCreatedAt(getCurrentTimestamp());
            note.setUpdatedAt(getCurrentTimestamp());

            notes.add(note);
        }

        return notes;
    }

    static Date getCurrentTimestamp() {
        return Calendar.getInstance().getTime();
    }
}
