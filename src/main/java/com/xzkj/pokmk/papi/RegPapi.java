package com.xzkj.pokmk.papi;

import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.entity.Player;

public class RegPapi extends PlaceholderHook {
    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equalsIgnoreCase("KMSS")){
            return "";
        }
        return "作者小正";
    }
}
