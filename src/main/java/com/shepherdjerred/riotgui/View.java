package com.shepherdjerred.riotgui;

import com.shepherdjerred.riotgui.components.Gui;
import com.shepherdjerred.riotgui.components.Item;
import com.shepherdjerred.riotgui.components.Layout;
import com.shepherdjerred.riotgui.components.Paginator;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class View {

    private Paginator paginator;
    private Gui gui;
    private Integer page;
    private String title;
    private Layout layout;
    private List<Item> items;
    private List<Item> controls;

    public View(@NotNull String title, @NotNull Layout layout, @NotNull List<Item> items) {
        this.title = title;
        this.layout = layout;
        this.items = items;
        gui = new Gui(title, layout, items);
    }

    public View(@NotNull Integer page, @NotNull String title, @NotNull Layout layout, @NotNull List<Item> items, @NotNull List<Item> controls) {
        this.page = page;
        this.title = title;
        this.layout = layout;
        this.items = items;
        this.controls = controls;
        paginator = new Paginator(layout, items);
        gui = new Gui(title, layout, paginator.getPageItems(page), controls);
    }

    public View(@NotNull String title, @NotNull Layout layout, @NotNull List<Item> items, @NotNull List<Item> controls) {
        this.page = 0;
        this.title = title;
        this.layout = layout;
        this.items = items;
        this.controls = controls;
        paginator = new Paginator(layout, items);
        gui = new Gui(title, layout, paginator.getPageItems(page), controls);
    }

    public void show(@NotNull Player player) {
        Gui.getInventories().add(gui);
        GuiPlayer.getGuiPlayer(player).addView(this);
        player.openInventory(gui.getInventory());
    }

    public void next(@NotNull Player player) {
        if (page < paginator.getNumberOfPages())
            page++;
        else
            return;
        gui = new Gui(title, layout, paginator.getPageItems(page), controls);
        player.openInventory(gui.getInventory());
    }

    public void previous(@NotNull Player player) {
        if (page > 0)
            page--;
        else
            return;
        gui = new Gui(title, layout, paginator.getPageItems(page), controls);
        player.openInventory(gui.getInventory());
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public Gui getGui() {
        return gui;
    }

}
