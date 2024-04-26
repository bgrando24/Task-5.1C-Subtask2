package com.example.task_51c_subtask2;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class PlayActivityView extends AppCompatActivity {
    private final String DEBUG_TAG = "debug_logs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_view);

        Log.d(DEBUG_TAG, "PlayActivityView created");

//        get URL from intent
        String url = getIntent().getStringExtra("URL");
        Log.d(DEBUG_TAG, "URL: " + url);

//        extract video ID
        String[] videoId = url.split("v=");
        Log.d(DEBUG_TAG, "Video ID: " + videoId[1]);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                String videoId = "S0Q4gqBUs7c";
                youTubePlayer.loadVideo(videoId[1], 0);
            }
        });
    }
}