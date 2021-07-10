package com.codepath.cchem.gram.ui.post;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.codepath.cchem.gram.R;
import com.codepath.cchem.gram.data.model.Post;
import com.codepath.cchem.gram.ui.camera.CameraFragment;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class CreatePostFragment extends Fragment {
    public static final String TAG = "CreatePostFragment";
    private EditText etDescription;
    private Button btnCaptureImage;
    private ImageView ivPostImage;
    private Button btnSubmit;

    NavController navController = null;

    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 12;
    public String photoFileName = "photo.jpg";
    File photoFile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        etDescription = view.findViewById(R.id.etDescription);
        btnCaptureImage = view.findViewById(R.id.btnCaptureImage);
        ivPostImage = view.findViewById(R.id.ivPostImage);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        btnCaptureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

//        queryPosts();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = etDescription.getText().toString();
                if(description.isEmpty()) {
                    Toast.makeText(getActivity(), "Description cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                ParseUser currentUser = ParseUser.getCurrentUser();
                savePost(description, currentUser);
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        } catch (ActivityNotFoundException e){

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivPostImage.setImageBitmap(imageBitmap);
        }
    }

//    private void launchCamera() {
//        Context context = getActivity();
//        PackageManager packageManager = context.getPackageManager();
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) == false) {
//            Toast.makeText(getActivity(), "This device has no camera", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        //.
//        // create Intent to take a picture and return control to the calling application
//        Intent takePictureIntent  = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////        // Create a File reference for future access
////        photoFile = getPhotoFileUri(photoFileName);
//
//        // wrap File object into a content provider
//        // required for API >= 24
//        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
////        Uri fileProvider = FileProvider.getUriForFile(getActivity(), "com.codepath.fileprovider", photoFile);
////        takePictureIntent .putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);
//
//        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
//        // So as long as the result is not null, it's safe to use the intent.
//        CameraFragment cameraFragment = (CameraFragment) getActivity();
//        if (takePictureIntent .resolveActivity(getActivity().getPackageManager()) != null) {
//            //.
//            try {
//                photoFile = createImageFile();
//            } catch (IOException e){
//                // Error occurred while creating the File
//                Toast toast = Toast.makeText(getActivity(), "There was a problem saving the photo...", Toast.LENGTH_SHORT);
//                toast.show();
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                Uri fileUri = Uri.fromFile(photoFile);
//                getActivity().setCapturedImageURI(fileUri);
//                getActivity().setCurrentPhotoPath(fileUri.getPath());
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,
//                        getActivity().getCapturedImageURI());
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//            }
//            // Start the image capture intent to take photo
//            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
//        }
//    }

//    // Returns the File for a photo stored on disk given the fileName
//    public File getPhotoFileUri(String fileName) {
//        // Get safe storage directory for photos
//        // Use `getExternalFilesDir` on Context to access package-specific directories.
//        // This way, we don't need to request external read/write runtime permissions.
//        File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);
//
//        // Create the storage directory if it does not exist
//        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
//            Log.d(TAG, "failed to create directory");
//        }
//
//        // Return the file target for the photo based on filename
//        return new File(mediaStorageDir.getPath() + File.separator + fileName);
//    }

    private void savePost(String description, ParseUser currentUser) {
        Post post = new Post();
        post.setDescription(description);
//        post.setImage
        post.setUser(currentUser);
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null) {
                    Log.i(TAG, "Post save successful");
                    etDescription.setText("");
                }else{
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(getActivity(), "Error while saving", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e == null) {
                    for (Post post : posts) {
                        Log.i(TAG, "Description" + post.getDescription().toString()+ " username: "+ post.getUser().getUsername().toString());
                    }
                } else {
                    Log.e(TAG, "Issue with getting posts", e);
                }
            }
        });
    }


}