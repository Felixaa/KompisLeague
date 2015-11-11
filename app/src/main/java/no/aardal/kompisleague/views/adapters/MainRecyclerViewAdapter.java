package no.aardal.kompisleague.views.adapters;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import no.aardal.kompisleague.R;
import no.aardal.kompisleague.models.League;
import no.aardal.kompisleague.models.Summoner;
import no.aardal.kompisleague.utils.Config;
import no.aardal.kompisleague.views.SmsDialogFragment;

/**
 * Created by Chris on 10/7/2015.
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    ArrayList<Summoner> summoners;
    ArrayList<League> leagues;
    FragmentManager mManager;
    Context mContext;

    public MainRecyclerViewAdapter(ArrayList<Summoner> summoners,
                                   Context context,
                                   FragmentManager manager) {
        this.summoners = summoners;
        this.mContext = context;
        this.mManager = manager;
    }

    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.card_view_item, parent, false);

        ViewHolder vh = new ViewHolder(v, new ViewHolder.ViewHolderClicks() {
            @Override
            public void onSmsClick(int position) {
                showDialog(mContext, position);
            }
        }) ;
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String leagueTier = summoners.get(position).league.tier;
        String division = summoners.get(position).league.entries.get(0).division;

        holder.helloLadies.setText(summoners.get(position).name);
        holder.leagueTierText.setText(leagueTier + " " + division);

        switch (summoners.get(position).league.tier) {
            case "DIAMOND":
                holder.leagueTierLogo.setImageResource(R.drawable.ic_diamond);
                break;
            case "PLATINUM":
                holder.leagueTierLogo.setImageResource(R.drawable.ic_platinum);
                break;
            case "GOLD":
                holder.leagueTierLogo.setImageResource(R.drawable.ic_gold);
                break;
            case "SILVER":
                holder.leagueTierLogo.setImageResource(R.drawable.ic_silver);
                break;
            case "BROZE":
                holder.leagueTierLogo.setImageResource(R.drawable.ic_bronze);
                break;
        }



        if (summoners.get(position).profileIconId != null) {
            getSummonerIcon(summoners.get(position).profileIconId, holder);
        }


        if (summoners.get(position).phoneNr != null) {
            holder.ingameStatus.setText(summoners.get(position).phoneNr);
        } else {
            holder.ingameStatus.setText("NO PHONENR");
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

    private void showDialog(Context context, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("phoneNr", summoners.get(position).phoneNr);
        SmsDialogFragment dialog = new SmsDialogFragment();
        dialog.setArguments(bundle);
        dialog.show(mManager, "Message");
    }



    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView helloLadies;
        public ImageView profilePicture;
        public ImageView leagueTierLogo;
        public TextView leagueTierText;
        public TextView ingameStatus;
        public Button sendSmsButton;

        public ViewHolderClicks mListener;

        public ViewHolder(View itemView, ViewHolderClicks listener) {
            super(itemView);
            this.mListener = listener;
            helloLadies = (TextView)itemView.findViewById(R.id.cardview_text);
            profilePicture = (ImageView)itemView.findViewById(R.id.profile_picture);
            ingameStatus = (TextView)itemView.findViewById(R.id.ingame_status_text);
            sendSmsButton = (Button)itemView.findViewById(R.id.sms_button);
            leagueTierLogo = (ImageView)itemView.findViewById(R.id.league_tier_logo);
            leagueTierText = (TextView)itemView.findViewById(R.id.league_tier_text);
            sendSmsButton.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (v instanceof Button) {
                mListener.onSmsClick(getAdapterPosition());
            }
        }


        private interface ViewHolderClicks {
            void onSmsClick(int position);
        }
    }
}
