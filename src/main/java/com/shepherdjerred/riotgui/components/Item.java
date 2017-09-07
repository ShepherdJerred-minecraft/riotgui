package com.shepherdjerred.riotgui.components;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class Item {

    private String name;
    private List<String> lore;
    private Material material;
    private Integer quantity;
    private ItemStack itemStack;

    public Item(@NotNull String name, @NotNull Material material, @NotNull Integer quantity, @Nullable String... lore) {
        this.name = name;

        if (lore != null)
            this.lore = Arrays.asList(lore);
        else
            this.lore = null;

        this.material = material;
        this.quantity = quantity;
        createItemStack();
    }

    public Item(@Nullable String name, @NotNull ItemStack itemStack, @Nullable String... lore) {

        this.name = name;

        if (lore != null)
            this.lore = Arrays.asList(lore);
        else
            this.lore = null;

        this.itemStack = itemStack;

        if (itemStack.getType() != Material.AIR)
            setItemMeta();
    }

    private void createItemStack() {
        itemStack = new ItemStack(material, quantity);
        if (itemStack.getType() != Material.AIR)
            setItemMeta();
    }

    private void setItemMeta() {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (name != null)
            itemMeta.setDisplayName(name);
        if (lore != null)
            itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public Material getMaterial() {
        return material;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void run(InventoryClickEvent event) {
    }
}
