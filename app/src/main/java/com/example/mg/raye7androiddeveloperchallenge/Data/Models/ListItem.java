package com.example.mg.raye7androiddeveloperchallenge.Data.Models;

public abstract class ListItem {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_EVENT = 1;

    abstract public int getType();

}