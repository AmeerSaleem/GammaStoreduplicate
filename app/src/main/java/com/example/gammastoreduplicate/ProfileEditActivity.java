package com.example.gammastoreduplicate;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.StorageReference;

public class ProfileEditActivity extends AppCompatActivity {

    EditText profileName,profileDescription;
    LinearLayout linearLayoutImageChooser;
    TextView textImageInform;
    ImageView image_display;
    public static final int CHOOSE_IMAGE = 101;
    Uri imageUri;
    StorageReference storageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile_edit);

        textImageInform = findViewById(R.id.text_view_image_informer);
        image_display = findViewById(R.id.image_view_pic_display);
        profileName = findViewById(R.id.edit_text_profile_name);
        profileDescription = findViewById(R.id.edit_text_profile_description);
        linearLayoutImageChooser = findViewById(R.id.linear_layout_image_chooser);

        linearLayoutImageChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

    }

    private void openImageChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,CHOOSE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();

            Glide.with(this)
                    .load(imageUri)
                    .into(image_display);
            textImageInform.setVisibility(View.GONE);
        }
    }
}