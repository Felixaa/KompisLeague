package no.aardal.kompisleague.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import no.aardal.kompisleague.R;
import no.aardal.kompisleague.controllers.RiotAPI;
import no.aardal.kompisleague.models.Summoner;
import no.aardal.kompisleague.utils.Config;
import no.aardal.kompisleague.views.adapters.MainRecyclerViewAdapter;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private ArrayList<Summoner> summoners;

    private RecyclerView recyclerView;
    private MainRecyclerViewAdapter recyclerAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //requestData();
    }

    private void initViews() {
        text = (TextView) findViewById(R.id.text);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerAdapter = new MainRecyclerViewAdapter(Config.getTestDator(), this);
        recyclerView.setAdapter(recyclerAdapter);

    }

    private void requestData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RiotAPI riot = retrofit.create(RiotAPI.class);
        String summonername = "felixaa,dugthethug";

        Call<Map> call = riot.getSummoner(summonername, Config.urlParamKey);
        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Response<Map> response, Retrofit retrofit) {
                Integer res = response.code();
                Log.d("RESPONSE MESSAGE: ", response.message());
                Log.d("RESPONSE RAW: ", response.raw().toString());
                Log.d("RESPONSE CODE: ", res.toString());

                Map item = (Map)response.body().get("felixaa");
                Summoner felixaa = new Summoner().build(item);
                Log.d("FELIXAXAXAAXAX", felixaa.name);
                Log.d("Profilepic", felixaa.profileIconId.toString());

                /*Map<String, String> result = (Map)response.body().get("felixaa");
                Log.d("RESUUUULT: ", result.get("name"));*/

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("FAILED", "FAILED");

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
