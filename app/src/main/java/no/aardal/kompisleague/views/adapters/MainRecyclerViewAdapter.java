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

import java.util.ArrayList;

import no.aardal.kompisleague.R;
import no.aardal.kompisleague.models.Summoner;
import no.aardal.kompisleague.utils.Config;
import no.aardal.kompisleague.views.SmsDialogFragment;

/**
 * Created by Chris on 10/7/2015.
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    ArrayList<Summoner> summoners;
    FragmentManager mManager;
    Context mContext;

    public MainRecyclerViewAdapter(ArrayList<Summoner> summoners, Context context, FragmentManager manager) {
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
        holder.helloLadies.setText(summoners.get(position).name);


        if (summoners.get(position).profileIconId != null) {
            getSummonerIcon(summoners.get(position).profileIconId, holder);
        }

        if (summoners.get(position).phoneNr != null) {
            holder.ingameStatus.setText(summoners.get(position).phoneNr);
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
