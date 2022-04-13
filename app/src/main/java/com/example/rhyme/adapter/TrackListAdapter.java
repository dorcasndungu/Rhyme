package com.example.rhyme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhyme.Models.Track;
import com.example.rhyme.R;
import com.example.rhyme.ui.LyricRhymeFragment;
import com.example.rhyme.ui.MainActivity;

import java.util.List;

import retrofit2.Callback;

public class TrackListAdapter extends RecyclerView.Adapter<TrackListAdapter.trackViewHolder>{
    private List<Track> trackList;
    private Context context;


    public TrackListAdapter(List<Track> trackList,Context context) {
        this.trackList = trackList;
        this.context = context;

    }

    @NonNull
    @Override
    public TrackListAdapter.trackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        return new trackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackListAdapter.trackViewHolder holder, int position) {
        Track track=trackList.get(position);
        holder.artistName.setText(track.getTrack().getArtistName());
        holder.trackName.setText(track.getTrack().getTrackName());
        holder.albumName.setText(track.getTrack().getAlbumName());

    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }
    public static class trackViewHolder extends RecyclerView.ViewHolder {
        private TextView artistName;
        private TextView trackName;
        private TextView albumName;
        public trackViewHolder(@NonNull View itemView) {
            super(itemView);
            artistName=itemView.findViewById(R.id.itemArtist);
            trackName=itemView.findViewById(R.id.itemTrackName);
            albumName=itemView.findViewById(R.id.itemAlbum);

        }


    }


}

