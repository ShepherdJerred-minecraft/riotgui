package com.shepherdjerred.riotgui.components;

import org.apache.commons.lang.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Layout {

    private Character itemChar;
    private Character controlChar;
    private String[] lines;
    private List<Integer> itemSlots;
    private List<Integer> controlSlots;

    public Layout(@NotNull Character itemChar, @NotNull String... lines) {
        Validate.isTrue(lines.length <= 6);
        this.itemChar = itemChar;
        controlChar = null;
        this.lines = lines;
        itemSlots = new ArrayList<>();
        parseLines();
    }

    public Layout(@NotNull Character itemChar, @NotNull Character controlChar, @NotNull String... lines) {
        Validate.isTrue(lines.length <= 6);
        this.itemChar = itemChar;
        this.controlChar = controlChar;
        this.lines = lines;
        itemSlots = new ArrayList<>();
        controlSlots = new ArrayList<>();
        parseLines();
    }

    private void parseLines() {
        int slot = 0;

        for (String line : lines) {
            line = line.replace(" ", "");
            Validate.isTrue(line.length() == 9);

            for (char c : line.toCharArray()) {
                if (c == itemChar)
                    itemSlots.add(slot);

                if (controlChar != null && c == controlChar)
                    controlSlots.add(slot);

                slot++;
            }
        }
    }

    @NotNull
    public String[] getLines() {
        return lines;
    }

    @NotNull
    public List<Integer> getItemSlots() {
        return itemSlots;
    }

    @Nullable
    public List<Integer> getControlSlots() {
        return controlSlots;
    }

    @NotNull
    public Character getItemChar() {
        return itemChar;
    }

    @Nullable
    public Character getControlChar() {
        return controlChar;
    }

}
