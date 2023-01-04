package com.example.kit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {
    private FloatingActionButton fab;
    private RecyclerView csDepartment, meDepartment, itDepartment, csaimlDepartment;
    private LinearLayout csNoData, meNoData, itNoData, csaimlNoData;
    private List<TeacherData> list1, list2, list3, list4;

    private TeacherAdapter adapter;

    private DatabaseReference reference, dbref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);

        fab = findViewById(R.id.fab);

        csDepartment = findViewById(R.id.csDepartment);
        meDepartment = findViewById(R.id.meDepartment);
        csaimlDepartment = findViewById(R.id.csaimlDepartment);
        itDepartment = findViewById(R.id.itDepartment);

        csNoData = findViewById(R.id.csNoData);
        meNoData = findViewById(R.id.meNoData);
        itNoData = findViewById(R.id.itNoData);
        csaimlNoData = findViewById(R.id.csaimlNoData);
        
        reference = FirebaseDatabase.getInstance().getReference().child("teacher");

                
        csDepartment();
        csaimlDepartment();
        itDepartment();
        meDepartment();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    // we will start activity when we will click on floating action a   button
                startActivity(new Intent(UpdateFaculty.this, AddTeachers.class));
            }
        });
    }

            private void csDepartment() {
                dbref = reference.child("Computer Science");
                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot Datasnapshot) {
                        list1 = new ArrayList<>();
                        if(!Datasnapshot.exists()){
                            csNoData.setVisibility(View.VISIBLE);
                            csDepartment.setVisibility(View.GONE);
                        }
                        else{

                            csNoData.setVisibility(View.GONE);
                            csDepartment.setVisibility(View.VISIBLE);
                            for(DataSnapshot snapshot :Datasnapshot.getChildren())
                            {
                                TeacherData data = snapshot.getValue(TeacherData.class);
                                list1.add(data);
                            }
//
                            csDepartment.setHasFixedSize(true);
                            csDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                            adapter = new TeacherAdapter(list1,UpdateFaculty.this,"Computer Science");
                            csDepartment.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText( UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }


            private void meDepartment() {
                dbref = reference.child("Mechanical Engineering");
                dbref.child("Mechanical Engineering");
                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot Datasnapshot) {
                        list2 = new ArrayList<>();
                        if(!Datasnapshot.exists()){
                            meNoData.setVisibility(View.VISIBLE);
                            meDepartment.setVisibility(View.GONE);
                        }
                        else{

                            meNoData.setVisibility(View.GONE);
                            meDepartment.setVisibility(View.VISIBLE);
                            for(DataSnapshot snapshot :Datasnapshot.getChildren())
                            {
                                TeacherData data = snapshot.getValue(TeacherData.class);
                                list2.add(data);
                            }
                            meDepartment.setHasFixedSize(true);
                            meDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                            adapter = new TeacherAdapter(list2,UpdateFaculty.this,"Mechanical Engineering");
                            meDepartment.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText( UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            private void csaimlDepartment() {
                dbref = reference.child("computer Science AIML");
                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot Datasnapshot) {
                        list3 = new ArrayList<>();
                        if(!Datasnapshot.exists()){
                            csaimlNoData.setVisibility(View.VISIBLE);
                            csaimlDepartment.setVisibility(View.GONE);
                        }
                        else{

                            csaimlNoData.setVisibility(View.GONE);
                            csaimlDepartment.setVisibility(View.VISIBLE);
                            for(DataSnapshot snapshot :Datasnapshot.getChildren())
                            {
                                TeacherData data = snapshot.getValue(TeacherData.class);
                                list3.add(data);
                            }
                            csaimlDepartment.setHasFixedSize(true);
                            csaimlDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                            adapter = new TeacherAdapter(list3,UpdateFaculty.this,"computer Science AIML");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText( UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }


            private void itDepartment() {
                dbref = reference.child("Information Technology");
                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot Datasnapshot) {
                        list4 = new ArrayList<>();
                        if(!Datasnapshot.exists()){
                            itNoData.setVisibility(View.VISIBLE);
                            itDepartment.setVisibility(View.GONE);
                        }
                        else{

                            itNoData.setVisibility(View.GONE);
                            itDepartment.setVisibility(View.VISIBLE);
                            for(DataSnapshot snapshot :Datasnapshot.getChildren())
                            {
                                TeacherData data = snapshot.getValue(TeacherData.class);
                                list4.add(data);
                            }
                            itDepartment.setHasFixedSize(true);
                            itDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                            adapter = new TeacherAdapter(list4,UpdateFaculty.this,"Information Technology");
                            itDepartment.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText( UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

}