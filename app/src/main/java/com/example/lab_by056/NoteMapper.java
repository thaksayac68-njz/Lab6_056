package com.example.lab_by056;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NoteMapper {
    static Gson gson = new Gson();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.US);

    private static Date stringToDate(String str) {
        if (str == null) return new Date();
        try {
            return dateFormat.parse(str.trim());
        } catch (Exception e) {
            return new Date();
        }
    }

    private static String dateToString(Date date) {
        if (date == null) return "";
        return dateFormat.format(date);
    }

    // OOP -> Entity
    public static NoteEntity toEntity(Note note) {
        Date date = stringToDate(note.getCreatedDate());
        if (note instanceof TextNote) {
            return new NoteEntity(note.getTitle(), "text", null, ((TextNote) note).getContent(), date);
        } else if (note instanceof CheckListNote) {
            String jsonItems = gson.toJson(((CheckListNote) note).getCheckList());
            return new NoteEntity(note.getTitle(), "checklist", jsonItems, null, date);
        }
        return null;
    }

    // Entity -> OOP
    public static Note fromEntity(NoteEntity entity) {
        String dateStr = entity.createdDate != null ? entity.createdDate.toString() : dateToString(entity.createdDate);
        Student owner = new Student();
        owner.setUsername("Default User");

        if ("text".equals(entity.type)) {
            TextNote note = new TextNote();
            note.setTitle(entity.title);
            note.setCreatedDate(dateStr);
            note.setContent(entity.content);
            note.setOwner(owner);
            return note;
        } else if ("checklist".equals(entity.type)) {
            List<String> items = gson.fromJson(entity.checklistItemsJson, new TypeToken<List<String>>(){}.getType());
            CheckListNote note = new CheckListNote();
            note.setTitle(entity.title);
            note.setCreatedDate(dateStr);
            note.setCheckList(items);
            note.setOwner(owner);
            return note;
        }
        return null;
    }
}
