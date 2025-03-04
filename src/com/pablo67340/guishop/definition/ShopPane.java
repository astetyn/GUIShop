package com.pablo67340.guishop.definition;

import com.github.stefvanschie.inventoryframework.Gui;
import com.github.stefvanschie.inventoryframework.GuiItem;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.pablo67340.guishop.GUIShop;
import lombok.Getter;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ShopPane extends Pane {

    private final Map<Integer, GuiItem> items;
    @Getter
    private final Map<Integer, ItemStack> dummies;
    public static ShopPane INSTANCE;
    private int increment;

    public ShopPane(int length, int height) {
        super(length, height);
        items = new HashMap<>();
        dummies = new HashMap<>();
        INSTANCE = this;
    }

    public void addItem(GuiItem item) {
        items.put(items.size() + increment, item);
    }

    public void setItem(GuiItem item, int slot) {
        if (slot > 53) {
            GUIShop.log("Item: " + item.getItem().getType() + " was in slot " + slot + ". The max slot is lower than that. Item ignored. Please delete this item from shops.yml!");
        } else {
            items.put(slot, item);
        }
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public boolean click(@NotNull Gui arg0, @NotNull InventoryClickEvent event, int arg2, int arg3, int arg4,
                         int arg5) {
        return false;
    }

    @Override
    public void display(@NotNull Gui gui, @NotNull Inventory inventory, @NotNull PlayerInventory playerInventory,
                        int paneOffsetX, int paneOffsetY, int maxLength, int maxHeight) {
        items.forEach((key, value) -> {
            if (key >= 0) inventory.setItem(key, value.getItem());
        });
        dummies.forEach(inventory::setItem);
    }

    @NotNull
    @Override
    public Collection<GuiItem> getItems() {
        return new ArrayList<>(items.values());
    }

    @NotNull
    @Override
    public Collection<Pane> getPanes() {
        return new HashSet<>();
    }

    public Map<Integer, GuiItem> getItemsMap() {
        return this.items;
    }

    public ShopPane getINSTANCE() {
        return INSTANCE;
    }
}
