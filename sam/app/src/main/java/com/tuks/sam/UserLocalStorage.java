package com.tuks.sam;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStorage {
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStorage(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putInt("age", user.age);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.commit();
    }

    public User getUserData() {
        String name = userLocalDatabase.getString("name", "");
        int age = userLocalDatabase.getInt("age", -1);
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");

        return new User(name, age, username, password);
    }

    public void setLoggedInStatus(boolean loggedIn) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

    public void addSearchToHistory(String location) {
        String str = "";

       /* str += getSearchHostory()+"\n"+location;
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("myLocations", str);
        spEditor.commit();*/
    }

    public boolean isLoggedIn() {
        return userLocalDatabase.getBoolean("loggedIn", false);
    }

    public String getDirections() {
        return userLocalDatabase.getString("myLocations", "");
    }

    public void addDirections(String location) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("myLocations", location);
        spEditor.commit();
    }

    public void setNewLocation(String location, String latitude, String longitude) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("instructionLongitude", longitude);
        spEditor.putString("instructionLatitude", latitude);
        spEditor.putBoolean("instructionCoords", true);
        spEditor.putBoolean("instructionClicked", true);
        spEditor.putString("instructionLocation", location);
        spEditor.commit();
    }

    public void setNewLocation(String location) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("instructionClicked", true);
        spEditor.putString("instructionLocation", location);
        spEditor.commit();
    }

    public boolean isInstructionClicked() {
        return userLocalDatabase.getBoolean("instructionClicked", false);
    }

    public void setInstructionClicked(boolean val) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("instructionClicked", val);
        spEditor.commit();
    }

    public String getInstructionLocation() {
        return userLocalDatabase.getString("instructionLocation", null);
    }

    public String getInstructionLocationLongitude() {
        return userLocalDatabase.getString("instructionLongitude", null);
    }

    public String getInstructionLocationLatitude() {
        return userLocalDatabase.getString("instructionLatitude", null);
    }

    public boolean isInstructionCoords() {
        return userLocalDatabase.getBoolean("instructionCoords", false);
    }

    public void setInstructionCoords(boolean val) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("instructionCoords", val);
        spEditor.commit();
    }

    public int getScavengerHunt() {
        return userLocalDatabase.getInt("scavengerHunt", 1);
    }

    public void setScavengerHunt(int index) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("instructionClicked", true);
        spEditor.putInt("scavengerHunt", index);
        spEditor.commit();
    }

    public void setInventoryItem(int id, int image) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        String name = "inventoryItem_" + id;
        spEditor.putBoolean(name, true);
        spEditor.putInt(name, image);
        spEditor.commit();
    }

    public int getInventoryItem(int id) {
        String name = "inventoryItem_" + id;
        return userLocalDatabase.getInt(name, R.drawable.empty_inventory);
    }

    public void setInventoryItemSet(int id, boolean val) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        String name = "isInventoryItemSet_" + id;
        spEditor.putBoolean(name, val);
        spEditor.commit();
    }

    public boolean isInventoryItemSet(int id) {
        String name = "isInventoryItemSet_" + id;
        return userLocalDatabase.getBoolean(name, false);
    }
}
