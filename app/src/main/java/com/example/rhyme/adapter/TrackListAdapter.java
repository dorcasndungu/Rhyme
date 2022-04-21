package com.example.rhyme.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhyme.Models.Track;
import com.example.rhyme.R;
import com.example.rhyme.ui.LyricRhyme;


import java.util.List;

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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(context, LyricRhyme.class);
        intent.putExtra("artistName",track.getTrack().getArtistName());
        intent.putExtra("trackName",track.getTrack().getTrackName());
        intent.putExtra("albumName",track.getTrack().getAlbumName());
        context.startActivity(intent);
    Toast.makeText(context,track.getTrack().getTrackName(),Toast.LENGTH_SHORT).show();

    }
});
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }
    public static class trackViewHolder extends RecyclerView.ViewHolder {

        public TextView artistName;
        public TextView trackName;
        public TextView albumName;
        public trackViewHolder(@NonNull View itemView) {
            super(itemView);
            artistName=itemView.findViewById(R.id.itemArtist);
            trackName=itemView.findViewById(R.id.itemTrackName);
            albumName=itemView.findViewById(R.id.itemAlbum);

        }


    }


}

