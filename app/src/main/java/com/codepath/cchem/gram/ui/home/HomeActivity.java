package com.codepath.cchem.gram.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.cchem.gram.R;
import com.codepath.cchem.gram.ui.camera.CameraFragment;
import com.codepath.cchem.gram.ui.profile.ProfileFragment;
import com.codepath.cchem.gram.ui.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottNavView;
    private FragmentManager fragmentManager;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottNavView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            FragmentTransaction t = fragmentManager.beginTransaction();
            fragment = new StoriesFragment();
            t.replace(R.id.content_frame, fragment);
            t.commit();
        } else {
            fragment = (Fragment) fragmentManager.findFragmentById(R.id.content_frame);
        }
        bottNavView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.stories_fragment:
                fragment = new StoriesFragment();
                Toast.makeText(HomeActivity.this,"Clicked Stories",Toast.LENGTH_LONG).show();
                break;
            case R.id.camera_fragment:
                fragment = new CameraFragment();
                Toast.makeText(HomeActivity.this,"Clicked Camera",Toast.LENGTH_LONG).show();
                break;
            case R.id.profile_fragment:
                fragment = new ProfileFragment();
                Toast.makeText(HomeActivity.this,"Clicked Profile",Toast.LENGTH_LONG).show();
                break;
            case R.id.settings_fragment:
                fragment = new SettingsFragment();
                Toast.makeText(HomeActivity.this,"Clicked Settings",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        return false;
    }
}