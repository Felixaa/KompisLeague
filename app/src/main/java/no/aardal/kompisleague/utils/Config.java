package no.aardal.kompisleague.utils;

import java.util.ArrayList;

/**
 * Created by Chris on 10/7/2015.
 */
public class Config {

    public static final String baseUrl = "https://euw.api.pvp.net";
    public static final String region = "EUW1";
    public static final String urlParamKey = "6a85e0eb-e775-4012-a854-8999b4a80923";
    public static final String profilePicUrl = "http://ddragon.leagueoflegends.com/cdn/5.20.1/img/profileicon/";
    public static final String splashArtUrl = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/";


    public static ArrayList<String> getTestDator() {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add("WILLIAM");
        }
        return data;
    }

}
