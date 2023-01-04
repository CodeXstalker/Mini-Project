package com.example.finalgehuapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalgehuapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    RecyclerView convoRecycle,otherRecycle;
    //indepRecycle,artsCulRecycle,sportsRecycle,stuInfoRecycle,placeRegRecycle
    GalleryAdapter adapter;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gallery, container, false);
        convoRecycle =view.findViewById(R.id.convoRecycle);
        otherRecycle =view.findViewById(R.id.otherRecycle);
        
        reference= FirebaseDatabase.getInstance().getReference().child("Gallery");
        getConvoImage();
        getOtherImage();
        return view;
    }

    private void getOtherImage() {
        reference.child("E-Cell").addValueEventListener(new ValueEventListener() {
            List<String>imageList=new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    String data=(String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter=new GalleryAdapter(getContext(),imageList);
                otherRecycle.setLayoutManager(new GridLayoutManager(getContext(),3));
                otherRecycle.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
//                sdjfhkljsadhfkslad

            }
        });
    }

    private void getConvoImage() {
        reference.child("SDC").addValueEventListener(new ValueEventListener() {
            List<String>imageList=new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    String data=(String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter=new GalleryAdapter(getContext(),imageList);
                convoRecycle.setLayoutManager(new GridLayoutManager(getContext(),3));
                convoRecycle.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}