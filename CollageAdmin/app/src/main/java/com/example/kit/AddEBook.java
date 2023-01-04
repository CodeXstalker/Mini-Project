package com.example.kit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class AddEBook extends AppCompatActivity {

    private CardView Uploadpdf;
    private final int REQ  = 1;
    private Uri PdfData;
    private ImageView previewUpload;
    private EditText PDFTitle;
    private TextView PdfTextView; //To display the file name which user have selected
    private Button UploadPDFBtn;
    private DatabaseReference DatabaseReference;
    private StorageReference storageReference;
    private String pdfName,title;  // This variable will store the name of PDF
    private ProgressDialog pd; //We have created this is to show progress of the current task using progress dialog
    String downloadUrl= ""; // if url not generated URl will be blank


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ebook);

        PdfTextView = findViewById(R.id.PdfHint);
        PDFTitle = findViewById(R.id.PdfTitle);
        Uploadpdf = findViewById(R.id.addPDf);
        UploadPDFBtn = findViewById(R.id.ButtonUploadPDF);
        DatabaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference =  FirebaseStorage.getInstance().getReference();
        pd = new ProgressDialog(this);  // Here we have initialized the the progress dialog

        UploadPDFBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = PDFTitle.getText().toString();
                if(title.isEmpty()){
                    PDFTitle.setError("Empty");
                    PDFTitle.requestFocus();
                }
                else if(PdfData == null){
                     Toast.makeText(AddEBook.this,"Please upload Pdf",Toast.LENGTH_SHORT);
                }
                    else{
                        uploadPDf();
                }

            }


        });

        Uploadpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void uploadPDf() {
        pd.setTitle("Please Wait..");
        pd.setMessage("Uploading pdf");
        pd.show();
        StorageReference reference = storageReference.child("pdf/"+pdfName+"-"+System.currentTimeMillis()+".pdf");
        reference.putFile(PdfData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri uri = uriTask.getResult();
                uploadData(String.valueOf(uri));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(AddEBook.this, "Some Thing Went Wron", Toast.LENGTH_SHORT).show();
            }
        });

    }
    // Uploading Data to FireBase
    private void uploadData(String valueOf) {
            // Finding unique key to store data
            String uniqueKey = DatabaseReference.child("Pdf").push().getKey();

            //Creating hashMap  to store data
            HashMap data = new HashMap<>();
            data.put("pdfTitle",title);
            data.put("pdfUrl",downloadUrl);

            DatabaseReference.child("pdf").child(uniqueKey).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    pd.dismiss();
                    Toast.makeText(AddEBook.this, "PDF uploaded successfully", Toast.LENGTH_SHORT).show();
                    PDFTitle.setText("");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddEBook.this, "Failed to Upload PDf", Toast.LENGTH_SHORT).show();
                }
            });

    }


    // we have to open gallery so we will use Intent

    /*I am not able to select pdf */


    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("pdf/docs/ppt");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Pdf File"),REQ);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ && resultCode == RESULT_OK){
            PdfData = data.getData();
            if(PdfData.toString().startsWith("content://")){
                Cursor  cursor = null;
                try {
                    cursor = AddEBook.this.getContentResolver().query(PdfData,null,null,null,null);
                    if(cursor !=null && cursor.moveToFirst()){
                        pdfName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(PdfData.toString().startsWith("file://")){
                pdfName = new File(PdfData.toString()).getName(); // By this function we will ge the name of PDF
            }
            PdfTextView.setText(pdfName);

        }


    }
}