package no.aardal.kompisleague.controllers;

import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Chris on 10/7/2015.
 */
public interface RiotAPI {


    // get summoner by summonername
    @GET("/api/lol/euw/v1.4/summoner/by-name/{summonerNames}")
    Call<Map> getSummoner(@Path("summonerNames") String summonername, @Query("api_key") String api_key);


    // get current-match by summonerid
    @GET("/observer-mode/rest/consumer/getSpectatorGameInfo/{region}/{summonerId}")
    Call<Map> getCurrentMatch(@Path("region") String region, @Path("summonerId") String summonerId, @Query("api_key") String api_key);


}
