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
                // Clear previous result and show progress bar
                textViewResult.setText("");
                progressBarSearch.setVisibility(View.VISIBLE);

                // Start Thread for dummy searching
                new Thread(() -> {
                    try {
                        // Delay 2 seconds
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update UI on main thread
                    runOnUiThread(() -> {
                        progressBarSearch.setVisibility(View.GONE);
                        textViewResult.setText("ไม่พบข้อมูล");
                    });
                }).start();
            }
        });
    }
}