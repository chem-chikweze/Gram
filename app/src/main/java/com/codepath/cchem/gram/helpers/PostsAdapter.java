package com.codepath.cchem.gram.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.cchem.gram.R;
import com.codepath.cchem.gram.data.model.Post;
import com.codepath.cchem.gram.ui.home.DetailsActivity;
import com.codepath.cchem.gram.ui.home.HomeActivity;
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private String TAG = "PostsAdapter";
    private Context context;
    private List<Post> posts;
    NavController navController = null;


    public PostsAdapter (Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(v);
//                Intent i = new Intent(context, HomeActivity.class);
//                i.putExtra("DetailsFragment",1);
//                context.startActivity(i);
//                showEditDialog(v);
                Toast.makeText(context, "Recycle Click" + position, Toast.LENGTH_SHORT).show();
            }
        });


        Post post = posts.get(position);
        holder.bind(post);
    }
    public void goTo(View v) {
        Intent intent = new Intent(context, DetailsActivity.class );
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);

            tvDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navController = Navigation.findNavController(itemView);
                    if(navController !=  null) {
                        navController.navigate(R.id.action_stories_fragment_to_detailsFragment);
                    }
                }
            });
        }

        public void bind(Post post) {
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            ParseFile image = post.getImage();
            if(image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }
        }
    }

    private void showEditDialog(View view) {
        navController = Navigation.findNavController(view);
        if(navController !=  null) {
            navController.navigate(R.id.action_stories_fragment_to_detailsFragment);
        }
    }

}
