package com.prakash.nestedhorizontalrecyclerview.Interface;

import com.prakash.nestedhorizontalrecyclerview.itemGroup;

import java.util.List;

public interface IFirebaseLoadListener {

    void onFirebaseLoadSuccess(List<itemGroup> itemGroupList);
    void onFirebaseLoadFailed(String message);


}
