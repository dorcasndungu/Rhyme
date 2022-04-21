package com.example.rhyme.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhyme.R;
import com.example.rhyme.constants.Constants;
import com.example.rhyme.databinding.FragmentRhymeBinding;
import com.example.rhyme.lyricsModels.MyRhyme;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class RhymeFragment extends Fragment{
    FragmentRhymeBinding binding;
    TextView rhymePh;
    TextView titlePh;
    @BindView(R.id.rhymeUpdate)Button fragmentSave;
    public RhymeFragment() {
        // Required empty public constructor
    }
public String pushId;
    public String myRhyme;
    public String myRhymeTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//         Inflate the layout for this fragment
        Bundle bundle = this.getArguments();
         myRhyme = bundle.getString("positionRhyme");
         myRhymeTitle = bundle.getString("positionTitle");
        String myRhymePushId=bundle.getString("positionPushId");
        Toast.makeText(getActivity(),myRhymeTitle, Toast.LENGTH_SHORT).show();
        binding= FragmentRhymeBinding.inflate(inflater, container, false);
        binding.phtitle.setText(bundle.getString("positionTitle"));
        binding.phrhyme.setText(bundle.getString("positionRhyme"));
        pushId=myRhymePushId;
        binding.phrhyme.setMovementMethod(new ScrollingMovementMethod());


        binding.rhymeUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                MyRhyme newRhyme = new MyRhyme(bundle.getString("positionTitle"), binding.phrhyme.getText().toString());
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();

                DatabaseReference rhymesRef = FirebaseDatabase
                        .getInstance()
                        .getReference(Constants.FIREBASE_CHILD_RHYMES)
                        .child(uid);
                rhymesRef.child(bundle.getString("positionPushId")).setValue(newRhyme);
                Toast.makeText(getActivity(), "Your rhyme is successfully updated", Toast.LENGTH_LONG).show();
//                Map<String,Object> hashMap=new HashMap<>();
//                hashMap.put("rhyme", binding.phrhyme.getText());
//                rhymesRef.child(bundle.getString("positionPushId")).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
//                    @Override
//                    public void onSuccess(Object o) {
//
//                    }
//                });
                Intent intent = new Intent(getActivity(),SavedRhymesActivity.class);
                startActivity(intent);
            }

        });
        return binding.getRoot();
    }




}