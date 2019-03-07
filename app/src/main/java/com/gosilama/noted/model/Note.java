package com.gosilama.noted.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.gosilama.noted.lib.TimestampConverter;

import java.util.Date;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "note_id")
    private int id;

    @ColumnInfo(name = "note_title")
    private String noteTitle;

    @ColumnInfo(name = "note_content")
    private String noteContent;

    @ColumnInfo(name = "user_id")
    private String userId;

    @ColumnInfo(name = "created_at")
    @TypeConverters({TimestampConverter.class})
    private Date createdAt;

    @ColumnInfo(name = "updated_at")
    @TypeConverters({TimestampConverter.class})
    private Date updatedAt;
}
