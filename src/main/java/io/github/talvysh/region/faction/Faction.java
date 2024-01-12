package io.github.talvysh.region.faction;

import io.github.talvysh.GrimCraft;
import io.github.talvysh.region.Region;
import io.github.talvysh.SaveData;
import io.github.talvysh.member.Character;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/*
* The faction system is setup so that a solo player
* can take down zergs if they want to.  There's no "faction power",
* or over the type mechanics to grind out what you need to raid
* another faction.
* */

public class Faction extends Region {
    String name;
    Character owner;
    List<Player> members;
    List<Player> officers;
    List<Chunk> chunks;
    Block heart;

    static private final JavaPlugin plugin = GrimCraft.ref;
    static List<Faction> instances = new ArrayList<>();

    public Faction(Character _owner, String _name) {
        /* Creation of a new Faction */

        if (_owner.faction != null){
            _owner.player.sendMessage("");
            return;
        }

        instances.add(this);
        name = _name;
        owner = _owner;

        // Save to DB
        try {
            Connection con = SaveData.connect();
            assert con != null;

            PreparedStatement s = con.prepareStatement("INSERT INTO factions (name, owner) VALUES(?,?)");
            s.setString(1, name);
            s.setString(2, "Talvysh");
            s.executeUpdate();

            plugin.getLogger().log(Level.INFO, "Saved faction to DB!");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Faction(){
        /* Creation from DB */
    }

    public void addMember(Player player){
        members.add(player);
    }

    public void removeMember(Player player){
        /* Removes the member from the faction and updates the DB */
        members.remove(player);

        // TODO: Implement DB
    }

    public void invite(Player player){

    }

    public void kick(Player player){

    }

    public void promote(Player player){

    }

    public void demote(Player player){

    }

    public boolean isMember(Player player){
        /* Checks if a player is part of this faction */

        if (player == null) return false;
        return members.contains(player);
    }

    public void delete(){
        /* Completely removes the faction and deletes it from the DB */
        Faction.free(this);

        // TODO: Implement DB
    }

    public void glow(){
        /* Causes the heart to glow, helping raiders find the heart when they are within range */
    }

    static Faction getFaction(Location location){
        /* Gets the faction that this location belongs to or returns null */

        Chunk chunkCheck = location.getChunk();

        for (Faction f : instances){
            for (Chunk c : f.chunks){
                if (c == chunkCheck){
                    return f;
                }
            }
        }

        return null;
    }

    static boolean canInteract(Player player, Location location){
        /* Check whether the player can interact with a location or not. */

        Faction f = getFaction(location);
        // If there is no faction, then we can interact
        if (f == null) return true;

        // Return if the player is part of the faction
        return f.isMember(player);
    }

    static void free(Faction f){
        /* Kickstart the cleaning up of resources */
        instances.remove(f);
        f = null;
    }
}
