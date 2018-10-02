package com.johnwaithaka.angel.models;

import com.johnwaithaka.angel.entities.Lesson;
import com.johnwaithaka.angel.entities.Level;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuItem {
    String text;
    String link;
    String icon;
    String id;
    List<MenuItem> menuItems = new ArrayList<>();

    public MenuItem(String text, String link, String id) {
        this.text = text;
        this.link = link;
        this.id = id;
    }

    public MenuItem(String text, String icon, List<MenuItem> menuItems, String id) {
        this.text = text;
        this.icon = icon;
        this.menuItems = menuItems;
        this.id = id;
    }

    public static List<MenuItem> levelsToMenuItems(List<Level> levels){
        List<MenuItem> menuItems = new ArrayList<>();
        for (int i = 0; i < levels.size(); i++) {
            if(levels.get(i).getLessons() != null){
                menuItems.add(new MenuItem(
                        "Level " + (i+1),
                        "",
                        lessonsToMenuItems(levels.get(i).getLessons(), levels.get(i)),
                        levels.get(i).getId()
                ));
            } else {
                menuItems.add(new MenuItem(
                        "Level" + (i+1),
                        levels.get(i).getId(), //For link
                        levels.get(i).getId()  //For MenuItem id
                ));
            }


        }
        return menuItems;
    }

    private static List<MenuItem> lessonsToMenuItems(List<Lesson> lessons, Level level){
        List<MenuItem> menuItems = new ArrayList<>();
        for(Lesson l : lessons){
            menuItems.add(new MenuItem(
                    l.getWord().getText(),
                    level.getId(),
                    level.getId()
            ));
        }
        return menuItems;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
