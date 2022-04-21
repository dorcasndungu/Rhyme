package com.example.rhyme.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhyme.R;
import com.example.rhyme.constants.Constants;
import com.example.rhyme.lyricsModels.MyRhyme;

import com.example.rhyme.ui.SavedRhymesActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class FirebaseMyRhymeViewHolder extends RecyclerView.ViewHolder {
    View mView;
    Context mContext;
public TextView itemTitle,itemRhyme;
    public FirebaseMyRhymeViewHolder(View itemView) {
        super(itemView);

        itemTitle=itemView.findViewById(R.id.itemTrackName);
        itemRhyme=itemView.findViewById(R.id.itemArtist);
    }
}


