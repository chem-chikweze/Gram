package com.codepath.cchem.gram.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.cchem.gram.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginFragment extends Fragment implements View.OnClickListener{
    FragmentActivity listener;
    String TAG = "LoginFragment";
    private EditText etUsername;
    private EditText etPassword;
    private EditText etEmail;
    private Button btnLogin;
    private String username;
    private String password;
    private String email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        etUsername = view.findViewById(R.id.username);
        etPassword = view.findViewById(R.id.password);
        etEmail = view.findViewById(R.id.email);
        btnLogin = (Button) view.findViewById(R.id.login);
        btnLogin.setOnClickListener(this);
        return view;
    }

    Boolean loggedIn = true;
    private boolean loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login "+ username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null){
                    loggedIn = false;
                    Log.e(TAG, "Issue with login", e);
                }
            }
        });
        return loggedIn;
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "Login Button Clicked");
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        email = etEmail.getText().toString();

        signUp(username, password, email);
        if(loginUser(username, password) == true) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeActivity);
        Toast.makeText(getActivity(), "Login Success!", Toast.LENGTH_SHORT).show();
        }
    }

    private void signUp(String username, String password, String email){
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null) {
                    loginUser(username, password);
                }else {
                    Log.i(TAG, "Sign up unsuccessful");
                }
            }
        });
    }
}