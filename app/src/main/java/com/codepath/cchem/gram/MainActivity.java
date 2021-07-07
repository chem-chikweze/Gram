package com.codepath.cchem.gram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // TODO: Organize the User Experience that leads to Settings Activity
    TextView tvToSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ParseObject firstObject = new  ParseObject("FirstClass");
//        firstObject.put("message","Hey ! First message from android. Parse is now connected");
//        firstObject.saveInBackground(e -> {
//            if (e != null){
//                Log.e("MainActivity", e.getLocalizedMessage());
//            }else{
//                Log.d("MainActivity","Object saved.");
//            }
//        });
        tvToSettings = findViewById(R.id.tvToSettings);
        tvToSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}