package com.mbappsoftware.mobiledevtest.ui.adapter;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mbappsoftware.mobiledevtest.R;
import com.mbappsoftware.mobiledevtest.data.response.GitHubRepository;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryViewHolder> {

    private List<GitHubRepository> gitHubRepositories = new ArrayList<>();

    public RepositoryAdapter(List<GitHubRepository> gitHubRepositories) {
        this.gitHubRepositories = gitHubRepositories;
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_github, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        holder.bind(gitHubRepositories.get(position));

        GitHubRepository gitHubRepository = gitHubRepositories.get(position);
        String url = gitHubRepository.getOwner().getAvatarUrl();


    }

    @Override
    public int getItemCount() {
        return gitHubRepositories.size();
    }
}

class RepositoryViewHolder extends RecyclerView.ViewHolder {
    TextView tvRepositoryName;
    TextView tvRepositoryDescription;
    TextView tvForksQuantity;
    TextView tvStarsQuantity;
    ImageView ivProfile;
    TextView tvUsername;
    TextView tvName;

    public RepositoryViewHolder(@NonNull View itemView) {
        super(itemView);
        instanceViews(itemView);
    }

    public void instanceViews(View itemView) {
        tvRepositoryName = itemView.findViewById(R.id.tv_repository_name);
        tvRepositoryDescription = itemView.findViewById(R.id.tv_repository_description);
        tvForksQuantity = itemView.findViewById(R.id.tv_forks_quantity);
        tvStarsQuantity = itemView.findViewById(R.id.tv_stars_quantity);
        ivProfile = itemView.findViewById(R.id.iv_profile);
        tvUsername = itemView.findViewById(R.id.tv_username);
        tvName = itemView.findViewById(R.id.tv_name);
    }

    public void bind(GitHubRepository gitHubRepository) {
        tvRepositoryName.setText(gitHubRepository.getName());
        tvRepositoryDescription.setText(gitHubRepository.getDescription());
        tvForksQuantity.setText(gitHubRepository.getForks());
        tvStarsQuantity.setText(gitHubRepository.getStars());
        tvUsername.setText(gitHubRepository.getOwner().getUserName());

        Glide.with(itemView.getContext())
                .asBitmap()
                .load(gitHubRepository.getOwner().getAvatarUrl())
                .circleCrop()
                .error(R.drawable.ic_profile)
                .placeholder(R.drawable.ic_profile)
                .into(ivProfile);

//        String url = gitHubRepository.getOwner().getAvatarUrl();
//
//        Glide.with(itemView.getContext()).asBitmap().load(url).listener(new RequestListener<Bitmap>() {
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
//                ivProfile.setImageURI(Uri.parse(url));
//
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
//                return false;
//            }
//        }).into(ivProfile);
    }
}


