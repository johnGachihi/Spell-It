package com.johnwaithaka.angel.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuItem {
    String text;
    String link;
    String icon;
    List<MenuItem> menuItems = new ArrayList<>();

    public MenuItem(String text, String link) {
        this.text = text;
        this.link = link;
    }

    public MenuItem(String text, String icon, List<MenuItem> menuItems) {
        this.text = text;
        this.icon = icon;
        this.menuItems = menuItems;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
