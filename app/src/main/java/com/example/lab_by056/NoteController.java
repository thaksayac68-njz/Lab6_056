package com.example.lab_by056;

import java.util.List;

public class NoteController {

    //Attribute
    private final AddNote view;

    public NoteController(AddNote view){
        this.view = view;
    }

    // Method to save TextNote
    public void saveNote(String strOfTile, String strOfContent, String strOfDate, String strOfUser){
        // Create User (Assuming Student for this example)
        Student student = new Student();
        student.setUsername(strOfUser);

        // Create TextNote
        TextNote tNote = new TextNote();
        tNote.setTitle(strOfTile);
        tNote.setCreatedDate(strOfDate);
        tNote.setContent(strOfContent);
        
        // Link User to Note
        tNote.setOwner(student);

        // Save to Room Database
        new Thread(() -> {
            NoteEntity entity = NoteMapper.toEntity(tNote);
            if (entity != null) {
                AppDatabase.getInstance(view.getApplicationContext()).noteDao().insert(entity);
            }
            view.runOnUiThread(() -> view.displayTextNote(tNote));
        }).start();
    }

    // Method to save CheckListNote
    public void saveCheckListNote(String strOfTile, List<String> items, String strOfDate, String strOfUser) {
        // Create User
        Student student = new Student();
        student.setUsername(strOfUser);

        // Create CheckListNote
        CheckListNote clNote = new CheckListNote();
        clNote.setTitle(strOfTile);
        clNote.setCreatedDate(strOfDate);
        clNote.setCheckList(items);
        
        // Link User
        clNote.setOwner(student);

        // Save to Room Database
        new Thread(() -> {
            NoteEntity entity = NoteMapper.toEntity(clNote);
            if (entity != null) {
                AppDatabase.getInstance(view.getApplicationContext()).noteDao().insert(entity);
            }
            view.runOnUiThread(() -> view.displayCheckListNote(clNote));
        }).start();
    }
}
