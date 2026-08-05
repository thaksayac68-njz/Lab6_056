package com.example.lab_by056;

public class NoteController {

    //Attribute
    private AddNote view;

    public NoteController(AddNote view){
        this.view = view;
    }

    //method
    public void saveNote(String strOfTile, String strOfContent, String strOfDate, String strOfUser){
        // Create User (Assuming Student for this example)
        Student student = new Student();
        student.setUsername(strOfUser);

        // Create TextNote
        TextNote tNote = new TextNote();
        tNote.setTitle(strOfTile);
        tNote.setCreatedDate(strOfDate);
        tNote.setContent(strOfContent);
        
        // Link User to Note (Step 3 requirement)
        tNote.setOwner(student);

        // show note in view
        view.displayTextNote(tNote);
    }
}
