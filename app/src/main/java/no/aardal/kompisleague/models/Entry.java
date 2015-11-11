package no.aardal.kompisleague.models;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by chrisaardal on 16/10/15.
 */
public class Entry implements Serializable {

    public String division;
    public String playerOrTeamName;
    public String playerOrTeamId;
    public Double wins;
    public Double losses;


    public Entry build(Map map) {
        if (map != null) {
            this.division =(String)map.get("division");
            this.playerOrTeamId = (String)map.get("playerOrTeamId");
            this.playerOrTeamName = (String)map.get("playerOrTeamName");
            this.wins = (Double)map.get("wins");
            this.losses = (Double)map.get("losses");
        }

        return this;
    }
}
