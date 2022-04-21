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
import com.example.rhyme.ui.RhymeFragment;
import com.example.rhyme.ui.SavedRhymesActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class FirebaseMyRhymeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View mView;
    Context mContext;

    public FirebaseMyRhymeViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindMyRhyme(MyRhyme myRhyme) {
        TextView itemTrackName = (TextView) mView.findViewById(R.id.itemTrackName);
        itemTrackName.setText(myRhyme.getTitle());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<MyRhyme> rhymes = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RHYMES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    rhymes.add(snapshot.getValue(MyRhyme.class));
                }

                int itemPosition = getLayoutPosition();

                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                RhymeFragment RhymeFragment=new RhymeFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.RecyclerView,RhymeFragment).addToBackStack(null).commit();

                Bundle bundle = new Bundle();
                bundle.putString("positionRhyme",rhymes.get(itemPosition).getRhyme());
                bundle.putString("positionTitle",rhymes.get(itemPosition).getTitle());
                bundle.putString("positionPushId",rhymes.get(itemPosition).getPushId());

                RhymeFragment.setArguments(bundle);
//                Intent intent = new Intent(mContext, SavedRhymesActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("rhymes", Parcels.wrap(rhymes));

             //   mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    }

