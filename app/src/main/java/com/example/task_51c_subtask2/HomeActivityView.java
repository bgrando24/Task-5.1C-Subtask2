package com.example.task_51c_subtask2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class HomeActivityView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_view);

//        youtube URL
        EditText url = findViewById(R.id.URLTextEdit);

//        play button
        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(v -> {
//            navigate to play view and pass URL
            Intent playIntent = new Intent(HomeActivityView.this, PlayActivityView.class);
            playIntent.putExtra("URL", url.getText().toString());
            startActivity(playIntent);
        });

//        add to playlist button
        Button addToPlaylistButton = findViewById(R.id.addToPlaylistButton);
        addToPlaylistButton.setOnClickListener(v -> {
//            add url to playlist and notify of success
            DataManager data = new DataManager(this);
            AuthManager auth = new AuthManager(this);
            int userId = auth.getAuthedUser();
            boolean successful = data.insertVideo(url.getText().toString(), userId);
            if (successful) {
                Snackbar.make(addToPlaylistButton, "Video added to playlist", Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(addToPlaylistButton, "Failed to add video to playlist", Snackbar.LENGTH_SHORT).show();
            }
        });

//        view my playlist button
        Button viewPlaylistButton = findViewById(R.id.myPlaylistButton);
        viewPlaylistButton.setOnClickListener(v -> {
//            navigate to playlist view
            Intent playlistIntent = new Intent(HomeActivityView.this, MyPlaylistActivityView.class);
            startActivity(playlistIntent);
        });
    }
}