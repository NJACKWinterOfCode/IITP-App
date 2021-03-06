package com.grobo.notifications.clubs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.grobo.notifications.R;

import java.util.List;

public class ClubsRecyclerAdapter extends RecyclerView.Adapter<ClubsRecyclerAdapter.ClubsViewHolder> {

    private Context context;
    private List<ClubItem> clubList;


    public ClubsRecyclerAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ClubsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_club, parent, false);

        return new ClubsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ClubsViewHolder holder, int position) {

        if (clubList != null) {
            final ClubItem current = clubList.get(position);

            holder.name.setText(current.getName());
            holder.name.setTransitionName("transition_title" + position);
            holder.bio.setText(current.getBio());
            holder.bio.setTransitionName("transition_bio" + position);
            Glide.with(context)
                    .load(current.getImage())
                    .placeholder(R.drawable.baseline_dashboard_24)
                    .into(holder.image);
            holder.image.setTransitionName("transition" + position);
            holder.image.setTransitionName("transition_image" + position);

            holder.rootLayout.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putInt("transition_position", position);
                bundle.putString("id", current.getId());

                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                        .addSharedElement(holder.image, "transition_image" + position)
                        .addSharedElement(holder.bio, "transition_bio" + position)
                        .addSharedElement(holder.name, "transition_title" + position)
                        .build();

                Navigation.findNavController(v).navigate(R.id.nav_club_detail,
                        bundle,
                        null,
                        extras);                });


        } else {
            holder.name.setText("Loading ...");
        }
    }

    @Override
    public int getItemCount() {
        if (clubList != null)
            return clubList.size();
        else return 0;
    }

    class ClubsViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView bio;
        LinearLayout rootLayout;
        ImageView image;

        ClubsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.club_name);
            bio = itemView.findViewById(R.id.club_bio);
            rootLayout = itemView.findViewById(R.id.card_club_root);
            image = itemView.findViewById(R.id.club_image);
        }
    }

    void setClubList(List<ClubItem> clubs){
        clubList = clubs;
        notifyDataSetChanged();
    }

}
