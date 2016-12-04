package com.fererlab.java8;

import java.util.*;

public class Panel {

    private String title;
    private int width;
    private int height;
    private Map<String, Panel> children = new TreeMap<>();

    public Panel(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addChild(Panel child) {
        this.children.put(child.getTitle(), child);
    }

    public Collection<Panel> getChildren() {
        return Collections.unmodifiableCollection(this.children.values());
    }

    public Panel removeChild(String title) {
        return this.children.remove(title);
    }

}
