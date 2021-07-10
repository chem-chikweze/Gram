package com.codepath.cchem.gram.ui.start;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.codepath.cchem.gram.R;
import com.parse.ParseUser;

import org.jetbrains.annotations.NotNull;

public class StartFragment extends Fragment {
    NavController navController = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        if( ParseUser.getCurrentUser() != null){
            if(navController !=  null) {
                navController.navigate(R.id.action_startFragment_to_homeActivity);
            }
        } else {
            navController.navigate(R.id.action_startFragment_to_loginFragment);
        }

    }
}