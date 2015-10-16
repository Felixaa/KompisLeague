package no.aardal.kompisleague.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import no.aardal.kompisleague.R;
import no.aardal.kompisleague.models.Summoner;

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
    }


    @Override
    public int getItemCount() {
        return summoners.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView helloLadies;

        public ViewHolder(View itemView) {
            super(itemView);
            helloLadies = (TextView)itemView.findViewById(R.id.cardview_text);
        }
    }
}
