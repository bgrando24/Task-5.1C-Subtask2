package com.example.task_51c_subtask2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Provides an adapter for the playlist ListView
 */

public class PlaylistAdapter extends BaseAdapter {
    private final String DEBUG_TAG = "debug_logs";
    private Context context;
    private List<String> data;
    private LayoutInflater inflater;

    public PlaylistAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.playlist_item, parent, false);
        }

        TextView urlTextView = view.findViewById(R.id.urlListItemTextView);
        String url = data.get(position);
        urlTextView.setText(url);

        Log.d(DEBUG_TAG, "Setting text for position " + position + ": " + url);

        return view;
    }
}
