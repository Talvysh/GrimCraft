package io.github.talvysh.raiding;

import io.github.talvysh.GrimCraft;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class RaidItem {

    static List<RaidItem> instances;
    static GrimCraft ref = GrimCraft.ref;

    public RaidItem(String name, ItemStack item){
        instances.add(this);

        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(ref, "raid-item");
        assert meta != null;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        pdc.set(key, PersistentDataType.BOOLEAN, true);
    }

    static void generate(){
        /* Setups the raid items at server start */
    }

    static boolean isRaidItem(ItemStack item){
        ItemMeta meta = item.getItemMeta();

        if (meta == null) return false;

        NamespacedKey key = new NamespacedKey(ref, "raid-item");
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        return  pdc.has(key);
    }
}
