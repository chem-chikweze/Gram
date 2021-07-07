package com.codepath.cchem.gram.ui.login;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.codepath.cchem.gram.R;


public class LoginActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Login", "hey");
//                login.setBackgroundResource(R.color.button_background_color);
//                login.setHintTextColor(R.color.button_text_color);
            }
        });

    }
}