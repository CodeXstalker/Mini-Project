package com.example.kit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.kit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddTeachers extends AppCompatActivity {

    private ImageView AddTeacherImage;
    private EditText AddTeacherName;
    private EditText AddTeacherEmail;
    private EditText AddTeacherPost;
    private Spinner  AddTeacherCategory;
    private Button AddTeacherButton;
    private String category;
    private final int REQ  = 1;
    private Bitmap bitmap = null;

    private ProgressDialog pd;
    private StorageReference  storageReference;
    private DatabaseReference reference,dbref;


    private String name, email, post, downloadUrl = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teachers);




        AddTeacherCategory= findViewById(R.id.addTeacherCategory);
        AddTeacherName = findViewById(R.id.addTeacherName);
        AddTeacherImage = findViewById(R.id.addTeacherImage);
        AddTeacherEmail = findViewById(R.id.addTeacherEmail);
        AddTeacherPost = findViewById(R.id.addTeacherPost);
        AddTeacherButton = findViewById(R.id.addTeacherButton);
         // Here we initialized progressDialog
        pd = new ProgressDialog(this);

        AddTeacherImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // openGallery(); method will invoke the gallery and its body in on line 86
                openGallery();
            }
        });

        AddTeacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //method to check validation whether any field is null or not
                checkValidation();

            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");
        storageReference = FirebaseStorage.getInstance().getReference();

        String[] categoryList = {"Select Category", "Computer Science","Mechanical Engineering","Information Technology","computer Science AIML"};

/*
    In Android, Adapter is a bridge between UI component and data source that helps us to fill data in UI component.
    It holds the data and send the data to an Adapter view then view can takes
    the data from the adapter view and shows the data on different views like as ListView, GridView, Spinner etc.

    --> Here we are setting the adapter to the SPINNER so, we can add the values of the string arrays
        in the spinner(As the Adapter class definition see)
 */

        AddTeacherCategory.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,categoryList)
        );




// Here we are setting "setOnItemSelectedListener" on  spinner element
       AddTeacherCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               category = AddTeacherCategory.getSelectedItem().toString();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });


    }

    private void checkValidation() {
        name = AddTeacherName.getText().toString();
        email = AddTeacherEmail.getText().toString();
        post = AddTeacherPost.getText().toString();


// To generate error if any of text-field if empty and if category is not selected
        if (name.isEmpty()) {
            AddTeacherName.setError("Empty");
            AddTeacherName.requestFocus();
        } else if (email.isEmpty()) {
            AddTeacherEmail.setError("Empty");
            AddTeacherEmail.requestFocus();
        } else if(post.isEmpty()) {
            AddTeacherPost.setError("Empty");
            AddTeacherPost.requestFocus();
        }else if(category.equals("Select Category")){
            Toast.makeText(this, "Please Select Category", Toast.LENGTH_SHORT).show();
        }else if(bitmap == null){
            //If no image is selected or if bitmap is null
            insertData();
        }else{
            // if no image is insert in circularImageCardView
            pd.setMessage("Uploading....");
            pd.show();

            insertImage();
        }
    }

    // Method to insert image

    private void insertImage() {
        //Below two line will show  the progress of the task using ProgressDialog variable;

        ByteArrayOutputStream baos = new ByteArrayOutputStream(); //Here we are compressing the images
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filePath;

        filePath = storageReference.child("Teachers").child(finalimg+"jpg");


        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(AddTeachers.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                if(task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    downloadUrl=String.valueOf(uri);
                                    // Method to insert data
                                               insertData();
                                }
                            });
                        }
                    });

                }
                else{
                    pd.dismiss();// when error occur while uploading the this will run
                    Toast.makeText(AddTeachers.this,"Something Went wrong",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void insertData() {
        //Used calender class to get the time and date for that particular moment


        dbref = reference.child(category);// notice child k andar phle unique key hoga
        final String uniqueKey =dbref.push().getKey();// In this we wil get the unique , where we can store all the data

        TeacherData teacherData = new TeacherData(name,email,post,downloadUrl,uniqueKey);

        /*
            1.Here we are storing data in firebase
            2.We also added SuccessListener to ensure that the file is uploaded
        */

        dbref.child(uniqueKey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                pd.dismiss();//If uploading done successfully
                Toast.makeText(AddTeachers.this,"Teacher Added",Toast.LENGTH_LONG).show();
            }


            // 3.And we also added addOnFailureListenerr to ensure that the file is uploaded

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();//If uploading failed
                Toast.makeText(AddTeachers.this,"Something Went Wrong",Toast.LENGTH_LONG).show();
            }
        });



    }


    private void openGallery() {
        Intent pickImage = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        );
        startActivityForResult(pickImage, REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ&& resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            AddTeacherImage.setImageBitmap(bitmap);


        }
    }
}