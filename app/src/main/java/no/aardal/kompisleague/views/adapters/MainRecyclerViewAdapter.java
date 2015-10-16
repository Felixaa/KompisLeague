package no.aardal.kompisleague.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import no.aardal.kompisleague.R;
import no.aardal.kompisleague.models.Summoner;
import no.aardal.kompisleague.utils.Config;

/**
 * Created by Chris on 10/7/2015.
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    ArrayList<Summoner> summoners;
    Context mContext;

    public MainRecyclerViewAdapter(ArrayList<Summoner> summoners, Context context) {
        this.summoners = summoners;
        this.mContext = context;
    }

    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.card_view_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.helloLadies.setText(summoners.get(position).name);


        if (summoners.get(position).profileIconId != null) {
            getSummonerIcon(summoners.get(position).profileIconId, holder);
        }
    }


    @Override
    public int getItemCount() {
        return summoners.size();
    }


    private void getSummonerIcon(Double summonerIcon, ViewHolder holder) {
        String profileUrl = Config.profilePicUrl + summonerIcon.intValue() + ".png";
        Picasso.with(mContext).load(profileUrl).into(holder.profilePicture);
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView helloLadies;
        public ImageView profilePicture;

        public ViewHolder(View itemView) {
            super(itemView);
            helloLadies = (TextView)itemView.findViewById(R.id.cardview_text);
            profilePicture = (ImageView)itemView.findViewById(R.id.profile_picture);
        }
    }
}
