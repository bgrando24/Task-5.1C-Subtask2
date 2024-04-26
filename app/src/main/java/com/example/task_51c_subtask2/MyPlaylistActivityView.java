package com.example.task_51c_subtask2;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MyPlaylistActivityView extends AppCompatActivity {
    private String DEBUG_TAG = "debug_logs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_playlist_view);

        ListView playlistListView = findViewById(R.id.playlistListView);

        // get playlist data
        DataManager data = new DataManager(this);
        int authedUserId = new AuthManager(this).getAuthedUser();
        Cursor playlistCursor = data.getAllVideos(authedUserId);
        List<String> playlist = new ArrayList<String>();
        while (playlistCursor.moveToNext()) {
            Log.d(DEBUG_TAG, "URL: " + playlistCursor.getString(playlistCursor.getColumnIndex("URL")));
            playlist.add(playlistCursor.getString(playlistCursor.getColumnIndex("URL")));
        }

        // display user playlist
        PlaylistAdapter pAdapter = new PlaylistAdapter(this, playlist);
        playlistListView.setAdapter(pAdapter);

        Log.d(DEBUG_TAG, "Playlist count: " + pAdapter.getCount());
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_my_playlist_view);
//
//        // Create dummy data
//        List<String> playlist = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            playlist.add("Dummy URL " + i);
//        }
//
//        // display user playlist
//        PlaylistAdapter pAdapter = new PlaylistAdapter(this, playlist);
//        ListView playlistListView = findViewById(R.id.playlistListView);
//        playlistListView.setAdapter(pAdapter);
//
//        Log.d(DEBUG_TAG, "Playlist count: " + pAdapter.getCount());
//    }
}