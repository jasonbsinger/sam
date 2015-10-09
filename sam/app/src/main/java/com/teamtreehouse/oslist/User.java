package com.teamtreehouse.oslist;

import java.util.HashSet;
import java.util.Set;

public class User {
    String name, username, password;
    int age;
    Set<String> searchHistory = null;

    public User(String name, int age, String username, String password) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.searchHistory = new HashSet<>();
    }

    public User(String username, String password) {
        this.name = "";
        this.age = -1;
        this.username = username;
        this.password = password;
        this.searchHistory = new HashSet<>();
    }
}
