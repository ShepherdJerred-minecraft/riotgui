package com.shepherdjerred.riotgui.components;

import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.lang.Math.min;
import static java.util.stream.Collectors.toMap;

public class Paginator {

    private Layout layout;
    private List<Item> items;
    private Map<Integer, List<Item>> itemPages;
    private Integer itemsPerPage;
    private Integer numberOfPages;

    public Paginator(@NotNull Layout layout, @NotNull List<Item> items) {
        this.layout = layout;
        this.items = items;
        itemsPerPage = 0;
        numberOfPages = 0;
        findItemsPerPage();
        paginate();
    }

    private void findItemsPerPage() {
        for (String line : layout.getLines())
            itemsPerPage += StringUtils.countMatches(line, String.valueOf(layout.getItemChar()));
    }

    private void paginate() {
        itemPages = IntStream.iterate(0, i -> i + itemsPerPage)
                .limit((items.size() + itemsPerPage - 1) / itemsPerPage)
                .boxed()
                .collect(toMap(i -> i / itemsPerPage,
                        i -> items.subList(i, min(i + itemsPerPage, items.size()))));

        numberOfPages = itemPages.size();
    }

    @NotNull
    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    @NotNull
    public List<Item> getPageItems(Integer page) {
        return itemPages.get(page);
    }

}
