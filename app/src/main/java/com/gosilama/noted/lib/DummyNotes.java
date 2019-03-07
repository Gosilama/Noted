package com.gosilama.noted.lib;

import com.gosilama.noted.model.Note;

import java.util.ArrayList;
import java.util.List;

public final class DummyNotes {
    private DummyNotes(){ }

    private static final List<Note> notes = new ArrayList<>();

    public static List<Note> getNotes() {
        for (int i = 0; i <= 5; i++) {
            String title = String.format("title %s", i);
            String content = String.format("content %s", i);
            String userId = String.format("user_id_%s", i);
            Note note = new Note(title, content, userId);
            notes.add(note);
        }

        return notes;
    }
}
