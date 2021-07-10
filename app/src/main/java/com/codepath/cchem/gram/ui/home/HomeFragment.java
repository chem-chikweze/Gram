package com.codepath.cchem.gram.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.codepath.cchem.gram.R;
import com.codepath.cchem.gram.ui.camera.CameraFragment;
import com.codepath.cchem.gram.ui.profile.ProfileFragment;
import com.codepath.cchem.gram.ui.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {
    NavController navController = null;
    NavHostFragment navHostFragment;
    BottomNavigationView bottomBarView;
    private String TAG = "HomeFragment";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bottomBarView = view.findViewById(R.id.bottom_navigation);
        bottomBarView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.e(TAG, "home navall  " + item.getTitle().toString());

                switch (item.getTitle().toString()) {
                    case "Home":
                        fragment = new HomeFragment();
//                        Navigation.findNavController(view).navigate(R.id.);
                    case "Camera":
                        fragment = new CameraFragment();
                        // do something here
                    case "Profile":
                        fragment = new ProfileFragment();
                        // do something here
                    case "Settings":
                        fragment = new SettingsFragment();
                        // do something here
                    default:
                        break;
                }
//                fragmentManager.beginTransaction().add(fragment, fragment.getTag()).commit();
                return true;
            }
        });

    }

        private void setUpBottomNavigation(View view) {

    }
}