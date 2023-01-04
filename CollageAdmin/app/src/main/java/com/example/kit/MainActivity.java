package com.example.kit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView uploadNotice; //
    CardView uploadGalleryImage; //
    CardView AddEBook; // To add functionality to AddE-Book button
    CardView AddFacultyMembers; // To add functionality to UPDATE FACULTY button
    CardView deletenotice;

    // Doubt in video 13 24 15


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uploadNotice = findViewById(R.id.addNotice);
        uploadGalleryImage = findViewById(R.id.addImage);
        AddEBook = findViewById(R.id.addEbook);
        AddFacultyMembers = findViewById(R.id.UpdateFacultyList);
        deletenotice = findViewById(R.id.deleteNoticeMain);


        /*Adding click-listener to the Button in the man activity XML file*/
        uploadNotice.setOnClickListener(this);
        uploadGalleryImage.setOnClickListener(this);
        AddEBook.setOnClickListener(this);
        AddFacultyMembers.setOnClickListener(this);
        deletenotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.addNotice:
                    Intent AddNotice = new Intent(MainActivity.this,activity_UploadNotice.class);
                    startActivity(AddNotice);
                    break;
                case R.id.addImage:
                    Intent  AddGallery= new Intent(MainActivity.this,UploadImage.class);
                    startActivity(AddGallery);
                    break;
                case R.id.addEbook:
                    Intent  AddEbook= new Intent(MainActivity.this,AddEBook.class);
                    startActivity(AddEbook);
                    break;
                case R.id.UpdateFacultyList:
                    Intent AddFAculty = new Intent(MainActivity.this, UpdateFaculty.class);
                    startActivity(AddFAculty);
                    break;
                case R.id.deleteNoticeMain:
                    Intent DeleteNotices = new Intent(MainActivity.this, DeleteNoticeActivity.class);
                    startActivity(DeleteNotices);
                    break;
            }
    }
}