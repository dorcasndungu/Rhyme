package com.example.rhyme.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rhyme.R;
import com.example.rhyme.constants.Constants;
import com.example.rhyme.lyricsModels.MyRhyme;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;

public class RhymeFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.title)
    TextView myRhymeTitleLabel;
    @BindView(R.id.rhyme)
    EditText myRhymeLabel;
    @BindView(R.id.fragmentSave)Button fragmentSave;
    public RhymeFragment() {
        // Required empty public constructor
    }
public String pushId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = this.getArguments();
        String myRhyme = bundle.getString("positionRhyme").toString();
        String myRhymeTitle = bundle.getString("positionTitle");
      //  String myRhymePushId=bundle.getString("positionPushId");
        myRhymeTitleLabel.setText(myRhymeTitle);
        myRhymeLabel.setText(myRhyme);
//        pushId=myRhymePushId;

        return inflater.inflate(R.layout.fragment_rhyme, container, false);
    }
    @Override
    public void onClick(View view) {
        if (view==fragmentSave){
//            firebase.database().ref('Promotions/promo_en').set({
//                    desc_promo: value,
//                    name_promo: value
//  });
            MyRhyme newRhyme=new MyRhyme(myRhymeTitleLabel.getText().toString(),myRhymeLabel.getText().toString());
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference rhymesRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_RHYMES)
                    .child(uid);
            newRhyme.setPushId(pushId);
            rhymesRef.setValue(newRhyme);
        }
    }
}