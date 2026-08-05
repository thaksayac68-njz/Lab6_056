package com.example.lab_by056;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button mButton,addButton,bButton ;

    ImageView logoImage ;

    ProgressBar loadData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextNote note1 = new TextNote();
        note1.setTitle(" Working Lab3 ");
        note1.setContent(" class and object ");
        note1.setCreatedDate(" 3 july 2026 ");
        note1.getSummary();

        TextNote note2 = new TextNote();
        note2.setTitle(" github ");
        note2.setContent(" share to github ");
        note2.setCreatedDate(" 15 july 2026 ");
        note2.getSummary();

        Admin user1 = new Admin();
        user1.setUsername(" AIMMY ");
        user1.setAge(19);
        user1.setGender(" female ");
        user1.setDepartment(" IT ");
        user1.login();
        user1.editStudent();

        Student user2 = new Student();
        user2.setUsername(" Gus ");
        user2.setAge(19);
        user2.setGender(" female ");
        user2.setStudentId(" 66012345 ");
        user2.login();
        user2.editNote();

        //event Source
        mButton = findViewById(R.id.button);
        addButton = findViewById(R.id.button2);
        bButton = findViewById(R.id.button6);
        loadData = findViewById(R.id.progressBar);
        loadData.setVisibility(View.GONE);//progress bar is gone
        //event Listener
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //event handler
                System.out.println("click");
                Intent aboutMe = new Intent(getApplicationContext(), AboteMeActivity.class);
                startActivity(aboutMe);

            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //event handler
                Intent addNote = new Intent(getApplicationContext(),AddNote.class);
                startActivity(addNote);

            }
        });
        bButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show progress bar
                loadData.setVisibility(View.VISIBLE);

                // Create and start Thread
                new Thread(() -> {
                    // Load from DB (delay 4 seconds)
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
                    
                    // Return to main Thread for UI updates
                    runOnUiThread(() -> {
                        loadData.setVisibility(View.GONE);
                        Intent browseNote = new Intent(getApplicationContext(), BrowseNoteActivity.class);
                        startActivity(browseNote);
                        finish();
                    });
                }).start();
            }
        });
        logoImage = findViewById(R.id.imageView);
        logoImage.setImageResource(R.drawable.njzlogo);

    }
}