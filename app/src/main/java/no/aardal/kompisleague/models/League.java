package no.aardal.kompisleague.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chrisaardal on 16/10/15.
 */
public class League implements Serializable {

    public String name;
    public String tier;
    public String queue;
    public ArrayList<Entry> entries = new ArrayList<>();


    public League build(Map map) {
        if (map != null) {
            this.name = (String)map.get("name");
            this.tier = (String)map.get("tier");
            this.queue = (String)map.get("queue");
            if (map.containsKey("entries")) {
                List<Map> rawEntries = (List<Map>) map.get("entries");
                    for (Map rawEntry : rawEntries) {
                        Entry entry = new Entry().build(rawEntry);
                        entries.add(entry);
                    }
            }

        }
        return this;
    }
}
