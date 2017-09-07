package com.shepherdjerred.riotgui;

import com.shepherdjerred.riotgui.components.Item;
import com.shepherdjerred.riotgui.components.Paginator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.concurrent.Callable;

public class ButtonCreator {

    public static Item createBackButton(Player player) {
        if (GuiPlayer.getGuiPlayer(player).getViews().isEmpty())
            return new Item("", Material.AIR, 1);
        else
            return new Item("Back", Material.BARRIER, 1, "Go back to " + GuiPlayer.getGuiPlayer(player).getViews().peek().getGui().getTitle()) {
                @Override
                public void run(InventoryClickEvent event) {
                    GuiPlayer.getGuiPlayer(player).getViews().pop();
                    GuiPlayer.getGuiPlayer(player).getViews().pop().show(player);
                }
            };
    }

    public static Item createNextPageButton(Paginator paginator, int page, Callable function) {
        if (page - 1 > paginator.getNumberOfPages())
            return new Item("Next", Material.SIGN, 1, "Go to page " + (page + 1)) {
                @Override
                public void run(InventoryClickEvent event) {
                    try {
                        function.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        else
            return new Item("", Material.AIR, 1);
    }

    public static Item createPreviousPageButton(int page, Callable function) {
        if (page > 0)
            return new Item("Next", Material.SIGN, 1, "Go to page " + (page - 1)) {
                @Override
                public void run(InventoryClickEvent event) {
                    try {
                        function.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        else
            return new Item("", Material.AIR, 1);
    }

    public static Item createCurrentPageButton(Paginator paginator, int page) {
        return new Item("Current Page", Material.PAPER, 1, String.valueOf(page + 1) + " of " + String.valueOf(paginator.getNumberOfPages()));
    }

}
