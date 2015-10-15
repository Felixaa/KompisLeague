package no.aardal.kompisleague.models;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Chris on 10/7/2015.
 */
public class Match implements Serializable {

    public Long gameId;
    public Long gameLength;
    public String gameMode;
    public Long gameStartTime;
    public String gameType;
    public Long mapId;

    public Match build(Map map) {

        if (map != null) {
            this.gameId = (Long)map.get("gameId");
            this.gameLength = (Long) map.get("gameLength");
            this.gameMode = (String)map.get("gameMode");
            this.gameStartTime = (Long)map.get("gameStartTime");
            this.gameType = (String)map.get("gameType");
            this.mapId = (Long)map.get("mapId");
        }


        return this;
    }

}
