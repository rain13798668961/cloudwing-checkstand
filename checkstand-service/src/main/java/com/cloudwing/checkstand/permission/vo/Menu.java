package com.cloudwing.checkstand.permission.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单
 */
public class Menu {

    private Integer id;

    private String name;

    private List<Menu> menus = new ArrayList<Menu>();

    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
