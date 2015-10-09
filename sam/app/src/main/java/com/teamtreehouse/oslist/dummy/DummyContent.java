package com.teamtreehouse.oslist.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {

        addItem(new DummyItem("1", "Gate 1"));// IT
        /*addItem(new DummyItem("2", "Food Quart 1")); // OG's
        addItem(new DummyItem("3", "Armourie")); //  Centenary
        addItem(new DummyItem("4", "Weapon Shop")); // Sci-enza
        addItem(new DummyItem("5", "Magical Garden"));
        addItem(new DummyItem("6", "Food Quart 2"));
        addItem(new DummyItem("7", "Food Quart 3"));
        addItem(new DummyItem("8", "Enchantments"));
        addItem(new DummyItem("9", "Gate 2"));
        addItem(new DummyItem("10", "Gate 3"));
        addItem(new DummyItem("11", "Trade Center"));
        addItem(new DummyItem("12", "Sourcerer"));
        addItem(new DummyItem("13", "Fight the Dragon"));
        addItem(new DummyItem("14", "Shield Generator"));*/
    }

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
