package com.shepherdjerred.riotgui;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;

public class GuiPlayer {

    private static Map<UUID, GuiPlayer> guiPlayers = new HashMap<>();

    private UUID uuid;
    private Stack<View> views;

    public GuiPlayer(UUID uuid) {
        this.uuid = uuid;
        this.views = new Stack<>();
        guiPlayers.put(uuid, this);
    }

    public static GuiPlayer getGuiPlayer(UUID uuid) {
        if (guiPlayerExists(uuid))
            return guiPlayers.get(uuid);
        else
            return new GuiPlayer(uuid);
    }

    public static GuiPlayer getGuiPlayer(Player player) {
        if (guiPlayerExists(player)) {
            return guiPlayers.get(player.getUniqueId());
        } else {
            return new GuiPlayer(player.getUniqueId());
        }
    }

    public static boolean guiPlayerExists(UUID uuid) {
        return guiPlayers.containsKey(uuid);
    }

    public static boolean guiPlayerExists(Player player) {
        return guiPlayers.containsKey(player.getUniqueId());
    }

    public void addView(View view) {
        views.push(view);
    }

    public boolean emptyViews() {
        return views.isEmpty();
    }

    public View getView() {
        return views.pop();
    }

    public Stack<View> getViews() {
        return views;
    }

    public void clearViews() {
        views.empty();
    }

}
