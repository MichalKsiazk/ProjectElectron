package com.electron.entities;

import com.electron.electronics.Component;

import java.util.ArrayList;
import java.util.List;

public class ComponentManager {

    public List<Component> components = new ArrayList<Component>();
    public List<Component> componentsToAdd = new ArrayList<Component>();
    public int ID = 0;

    public boolean setting_wire = false;

    public ComponentManager() {

    }

    public List<Component> findByTag(String tag) {
        List<Component> componentBuffer = new ArrayList<Component>();
        for (Component c : components) {
            for (String s : c.tags) {
                if (s == tag)
                    componentBuffer.add(c);
            }
        }
        return componentBuffer;
    }

    public List<Component> findByName(String name) {
        for (Component c : components) {

        }
        return null;
    }
}
