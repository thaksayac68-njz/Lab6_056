package com.example.lab_by056;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class AddNote extends AppCompatActivity {

    EditText title, content, user ;
    Button addNote,mButton ;
    TextView showNote;
    NoteController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_note);
        
        controller = new NoteController(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        title = findViewById(R.id.editTextText);
        content = findViewById(R.id.editTextText2);
        // Assuming you have an EditText for user, or use a default one for now
        // user = findViewById(R.id.editTextUser); 
        addNote = findViewById(R.id.button4);
        showNote = findViewById(R.id.textView4);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strOfTile = title.getText().toString();
                String strOfContent = content.getText().toString();
                String strOfDate = new Date().toString();
                String strOfUser = "Default User"; // Or get from EditText if available

                controller.saveNote(strOfTile, strOfContent, strOfDate, strOfUser);
            }
        });
        mButton = findViewById(R.id.button5);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
            }
        });
    }

    public void displayTextNote(TextNote note) {
        String display = "Owner: " + note.getOwner().getUsername() +
                        "\nTitle: " + note.getTitle() +
                        "\nContent: " + note.getContent() +
                        "\nDate: " + note.getCreatedDate();
        showNote.setText(display);
    }
}