package io.github.talvysh;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.util.logging.Level;

public final class GrimCraft extends JavaPlugin {
    public static GrimCraft ref;

    public void save(){}

    public void load(){}

    @Override
    public void onEnable() {
        ref = this;
        // Plugin startup logic
        getLogger().log(Level.INFO, "GrimCraft enabled!");

        // Test DB
        SaveData.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        // TODO: Make necessary DB calls.
    }
}
