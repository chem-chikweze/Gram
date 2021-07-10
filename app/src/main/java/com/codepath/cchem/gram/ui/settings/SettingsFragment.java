package com.codepath.cchem.gram.ui.settings;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.codepath.cchem.gram.R;
import com.parse.ParseUser;

import org.jetbrains.annotations.NotNull;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    NavController navController = null;
    TextView logout;
    private final String TAG = "SettingsFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }


    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logout = view.findViewById(R.id.tvLogout);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "Logout text Clicked");
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();
        Navigation.findNavController(view).navigate(R.id.action_settingsFragment_to_loginFragment);
    }
}