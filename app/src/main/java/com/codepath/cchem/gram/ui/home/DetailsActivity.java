package com.codepath.cchem.gram.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codepath.cchem.gram.R;
import com.codepath.cchem.gram.data.model.Post;
import com.parse.ParseFile;

public class DetailsActivity extends AppCompatActivity {
    Post post;
    private TextView tvUsername;
    private ImageView ivImage;
    private TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                post = null;
            } else {
                post = extras.getParcelable("Post Object");
                Toast.makeText(this, "Pst" +post.getDescription().toString(), Toast.LENGTH_SHORT).show();
            }
        }

        tvUsername = findViewById(R.id.tvUsername);
        ivImage = findViewById(R.id.ivImage);
        tvDescription = findViewById(R.id.tvDescription);

        tvDescription.setText(post.getDescription());
        tvUsername.setText(post.getUser().getUsername());
        ParseFile image = post.getImage();
        if(image != null) {
            Glide.with(this).load(image.getUrl()).into(ivImage);
        }
    }
}