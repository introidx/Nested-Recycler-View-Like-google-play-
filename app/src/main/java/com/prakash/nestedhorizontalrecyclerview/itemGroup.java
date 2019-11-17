package com.prakash.nestedhorizontalrecyclerview;

import java.util.ArrayList;

public class itemGroup {
    private String headerTitle;
    private ArrayList<itemData> listItem;

    public itemGroup() {
    }

    public itemGroup(String headerTitle, ArrayList<itemData> listItem) {
        this.headerTitle = headerTitle;
        this.listItem = listItem;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<itemData> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<itemData> listItem) {
        this.listItem = listItem;
    }
}
