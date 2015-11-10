package no.aardal.kompisleague.models;

import java.util.Map;

/**
 * Created by Chris on 10/7/2015.
 */
public class Summoner {

    public Double id;
    public String name;
    public Double profileIconId;
    public Double summonerLevel;
    public Double revisionDate;
    public String phoneNr;
    public League league;


    public Summoner build(Map map) {

        if (map != null) {
            this.id = (Double)map.get("id");
            this.name = (String)map.get("name");
            switch (name) {
                case "felixaa":
                    phoneNr = "96017672";
                    break;
                case "DugTheThug":
                    phoneNr = "98459121";
                    break;
                case "Bruh":
                    phoneNr = "94831272";
                    break;
                case "Reddet":
                    phoneNr = "45630516";
                    break;
                case "Flày":
                    phoneNr = "48435835";
                    break;
                case "vikathug":
                    phoneNr = "98713452";
                    break;
            }
            this.profileIconId = (Double)map.get("profileIconId");
            this.summonerLevel = (Double)map.get("summonerLevel");
            this.revisionDate = (Double)map.get("revisionDate");
        }

        return this;
    }
}
