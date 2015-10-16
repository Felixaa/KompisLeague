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

    // Get recent games by summonerID
    @GET("/api/lol/{region}/v1.3/game/by-summoner/{summonerId}/recent")
    Call<Map> getRecentGames(@Path("region") String region, @Path("summonerId") String summonerId, @Query("api_key") String api_key);

    // Get summoner Tier and division
    @GET("/api/lol/{region}/v2.5/league/by-summoner/{summonerIds}/entry")
    Call<Map> getRanked(@Path("region") String region, @Path("summonerIds") String summonerIds, @Query("api_key") String api_key);
}
