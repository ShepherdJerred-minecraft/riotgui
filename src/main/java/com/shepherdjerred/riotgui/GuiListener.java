package com.shepherdjerred.riotgui;

import com.shepherdjerred.riotgui.components.Gui;
import com.shepherdjerred.riotgui.components.Item;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.jetbrains.annotations.NotNull;

public class GuiListener {

    public void click(@NotNull InventoryClickEvent event) {

        if (!Gui.containsInventory(event.getInventory()))
            return;

        Gui gui = Gui.getGUIInventory(event.getInventory());

        if (!gui.itemAtSlot(event.getSlot()))
            return;

        Item item = gui.getItemAtSlot(event.getSlot());
        item.run(event);

        event.setCancelled(true);
    }

    public void close(@NotNull InventoryCloseEvent event) {

        if (!Gui.containsInventory(event.getInventory()))
            Gui.removeInventory(event.getInventory());

    }

}
