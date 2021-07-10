//package com.codepath.cchem.gram;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//
//import com.codepath.cchem.gram.ui.login.LoginActivity;
//import com.parse.ParseUser;
//
//public class SettingsActivity extends AppCompatActivity {
//    TextView logout;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_settings);
//
//        logout = findViewById(R.id.tvLogout);
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ParseUser.logOut();
//                ParseUser currentUser = ParseUser.getCurrentUser();
//                goToLogin();
//            }
//        });
//    }
//
//    private void goToLogin() {
//        Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
//        startActivity(intent);
//        finish();
//    }
//}