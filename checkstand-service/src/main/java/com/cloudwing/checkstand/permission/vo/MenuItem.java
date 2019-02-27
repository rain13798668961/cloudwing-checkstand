package com.cloudwing.checkstand.permission.vo;

/**
 * 菜单项
 * @author Administrator
 *
 */
public class MenuItem {

    private Integer menuId;

    private String name;

    private String url;

    public MenuItem() {

    }

    public MenuItem(String name, String url) {
        super();
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}
