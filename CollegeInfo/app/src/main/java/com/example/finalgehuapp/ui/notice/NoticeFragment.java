package com.example.finalgehuapp.ui.notice;

//import android.app.Activity;
import android.annotation.SuppressLint;
import android.os.Bundle;
//import java.lang.ClassNotFoundException;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.finalgehuapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {
    private RecyclerView deleteNoticeRecycle;
    private ProgressBar progressBar;
    private ArrayList<NoticeData> list;
    private NoticeAdapter adapter;

    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notice2, container, false);
        deleteNoticeRecycle = view.findViewById(R.id.deleteNoticeRecycle);
        progressBar = view.findViewById((R.id.progressBar));

        reference = FirebaseDatabase.getInstance().getReference().child("Notice");

        deleteNoticeRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        deleteNoticeRecycle.setHasFixedSize(true);

        getNotice();

        return view;
    }

    private void getNotice() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list=new ArrayList<>();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    NoticeData data=snapshot.getValue(NoticeData.class);
                    list.add(data);
                }
                adapter=new NoticeAdapter(getContext(),list);
                adapter.notifyDataSetChanged();



                progressBar.setVisibility(View.GONE);
                deleteNoticeRecycle.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);

                Toast.makeText(getContext(),databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}