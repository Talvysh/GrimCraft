package io.github.talvysh.region.dungeon;

import org.bukkit.Location;
import org.bukkit.util.BoundingBox;

import java.util.List;

public class Dungeon {
    private final BoundingBox box;

    static public List<Dungeon> instances;

    public Dungeon(Location l1, Location l2){
        box = new BoundingBox(l1.getX(), l1.getZ(), l1.getY(), l2.getX(), l2.getZ(), l2.getY());
        instances.add(this);
    }

    public boolean containsLocation(Location l){
        /* Checks if a location is inside its bounding box */
        return box.contains(l.toVector());
    }

    static public Dungeon getDungeon(Location l){
        for(Dungeon d : instances){
            if (d.containsLocation(l)){
                return d;
            }
        }

        return null;
    }
}
