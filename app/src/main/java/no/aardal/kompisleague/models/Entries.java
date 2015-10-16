package no.aardal.kompisleague.models;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by chrisaardal on 16/10/15.
 */
public class Entries implements Serializable {

    public String division;


    public Entries build(Map map) {
        if (map != null) {
            this.division =(String)map.get("division");
        }

        return this;
    }
}
