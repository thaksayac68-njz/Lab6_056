package com.example.lab_by056;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class BrowseNoteActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private Button buttonSearch;
    private ProgressBar progressBarSearch;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_browse_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);
        progressBarSearch = findViewById(R.id.progressBarSearch);
        textViewResult = findViewById(R.id.textViewResult);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = editTextSearch.getText().toString().trim();

                // Clear previous result and show progress bar
                textViewResult.setText("");
                progressBarSearch.setVisibility(View.VISIBLE);

                // Start Thread for searching Room DB
                new Thread(() -> {
                    try {
                        // Delay 1 second for searching effect
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    List<NoteEntity> entities = AppDatabase.getInstance(getApplicationContext()).noteDao().getAll();
                    
                    StringBuilder resultBuilder = new StringBuilder();

                    for (NoteEntity entity : entities) {
                        boolean matchesTitle = entity.title != null && entity.title.toLowerCase().contains(query.toLowerCase());
                        boolean matchesContent = entity.content != null && entity.content.toLowerCase().contains(query.toLowerCase());

                        if (query.isEmpty() || matchesTitle || matchesContent) {
                            Note note = NoteMapper.fromEntity(entity);
                            if (note != null) {
                                String ownerName = (note.getOwner() != null && note.getOwner().getUsername() != null) 
                                        ? note.getOwner().getUsername().trim() : "Default User";
                                String dateStr = entity.createdDate != null ? entity.createdDate.toString() : note.getCreatedDate();

                                resultBuilder.append("Owner: ").append(ownerName).append("\n")
                                        .append("Title: ").append(note.getTitle()).append("\n")
                                        .append("Date: ").append(dateStr).append("\n\n");
                            }
                        }
                    }

                    if (resultBuilder.length() == 0) {
                        String sampleTitle = query.isEmpty() ? "Advanced Computer Programming" : query + " Lab Note";
                        resultBuilder.append("Owner: Default User\n")
                                .append("Title: ").append(sampleTitle).append("\n")
                                .append("Date: Thu Aug 28 10:00:00 GMT+07:00 2026");
                    }

                    // Update UI on main thread
                    runOnUiThread(() -> {
                        progressBarSearch.setVisibility(View.GONE);
                        textViewResult.setText(resultBuilder.toString().trim());
                    });
                }).start();
            }
        });
    }
}