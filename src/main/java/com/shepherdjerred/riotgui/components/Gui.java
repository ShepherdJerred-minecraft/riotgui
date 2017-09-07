package com.shepherdjerred.riotgui.components;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Gui {

    private static List<Gui> inventories = new CopyOnWriteArrayList<>();

    private String title;
    private Layout layout;
    private Inventory inventory;
    private List<Item> items;
    private List<Item> controls;
    private Map<Integer, Item> itemMap;

    public Gui(@NotNull String title, @NotNull Layout layout, @NotNull List<Item> items) {
        Validate.isTrue(layout.getItemSlots().size() == items.size());
        this.title = title;
        this.layout = layout;
        this.items = items;
        itemMap = new HashMap<>();
        createInventory();
    }

    public Gui(@NotNull String title, @NotNull Layout layout, @NotNull List<Item> items, @NotNull List<Item> controls) {
        Validate.isTrue(layout.getControlSlots().size() == controls.size());
        this.title = title;
        this.layout = layout;
        this.items = items;
        this.controls = controls;
        itemMap = new HashMap<>();
        createPaginatedInventory();
    }

    public static void removeInventory(Inventory inventory) {
        for (Gui gui : inventories) {
            if (gui.getInventory().equals(inventory))
                inventories.remove(gui);
        }
    }

    public static boolean containsInventory(Inventory inventory) {
        for (Gui gui : inventories) {
            if (gui.getInventory().equals(inventory))
                return true;
        }
        return false;
    }

    public static Gui getGUIInventory(Inventory inventory) {
        for (Gui gui : inventories) {
            if (gui.getInventory().equals(inventory))
                return gui;
        }
        return null;
    }

    public static List<Gui> getInventories() {
        return inventories;
    }

    private void createInventory() {
        inventory = Bukkit.createInventory(null, layout.getLines().length * 9, title);
        int i = 0;

        for (Item item : items) {
            inventory.setItem(layout.getItemSlots().get(i), item.getItemStack());
            itemMap.put(layout.getItemSlots().get(i), item);
            i++;
        }
    }

    private void createPaginatedInventory() {
        createInventory();
        int i = 0;

        for (Item item : controls) {
            inventory.setItem(layout.getControlSlots().get(i), item.getItemStack());
            itemMap.put(layout.getControlSlots().get(i), item);
            i++;
        }
    }

    public String getTitle() {
        return title;
    }

    public Layout getLayout() {
        return layout;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean itemAtSlot(Integer slot) {
        return itemMap.containsKey(slot);
    }

    public Item getItemAtSlot(Integer slot) {
        return itemMap.getOrDefault(slot, null);
    }
}
