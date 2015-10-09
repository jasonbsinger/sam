package com.teamtreehouse.oslist;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.teamtreehouse.oslist.dummy.DummyContent;

public class ItemFragment extends Fragment implements AbsListView.OnItemClickListener {

    public static int SCAVENGER_PROGRESS = 1;
    UserLocalStorage userLocalStorage;
    /**
     * The fragment's ListView/GridView.
     */
    private ListView mListView;
    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userLocalStorage = new UserLocalStorage(getActivity());

        mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        // Set the adapter
        mListView = (ListView) view.findViewById(android.R.id.list);
        mListView.setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        System.out.println("Child Count = " + mListView.getChildCount());
        System.out.println("Count = " + mListView.getCount());

        userLocalStorage = new UserLocalStorage(getActivity());
        //System.out.println(userLocalStorage.getScavengerHunt());
        getMenuItems();

        mListView.setOnItemClickListener(this);
        //userLocalStorage = new UserLocalStorage(getActivity());

        return view;
    }

    public void getMenuItems() {
        DummyContent.ITEMS.clear();
        DummyContent.ITEM_MAP.clear();
        //SCAVENGER_PROGRESS = (int)userLocalStorage.getScavengerHunt();
        switch (SCAVENGER_PROGRESS - 1) {
            case 0:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1"));
                break;
            case 1:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart 1"));
                break;
            case 2:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart 1"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie"));
                break;
            case 3:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart 1"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Weapon Shop"));
                break;
            case 4:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart 1"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Weapon Shop"));
                DummyContent.addItem(new DummyContent.DummyItem("5", "Magical Garden"));
                break;
            case 5:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart 1"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Weapon Shop"));
                DummyContent.addItem(new DummyContent.DummyItem("5", "Magical Garden"));
                DummyContent.addItem(new DummyContent.DummyItem("6", "Food Quart 2"));
                break;
            case 6:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart 1"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Weapon Shop"));
                DummyContent.addItem(new DummyContent.DummyItem("5", "Magical Garden"));
                DummyContent.addItem(new DummyContent.DummyItem("6", "Food Quart 2"));
                DummyContent.addItem(new DummyContent.DummyItem("7", "Food Quart 3"));
                break;
            case 7:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart 1"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Weapon Shop"));
                DummyContent.addItem(new DummyContent.DummyItem("5", "Magical Garden"));
                DummyContent.addItem(new DummyContent.DummyItem("6", "Food Quart 2"));
                DummyContent.addItem(new DummyContent.DummyItem("7", "Food Quart 3"));
                DummyContent.addItem(new DummyContent.DummyItem("8", "Enchantments"));
                break;
            case 8:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart 1"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Weapon Shop"));
                DummyContent.addItem(new DummyContent.DummyItem("5", "Magical Garden"));
                DummyContent.addItem(new DummyContent.DummyItem("6", "Food Quart 2"));
                DummyContent.addItem(new DummyContent.DummyItem("7", "Food Quart 3"));
                DummyContent.addItem(new DummyContent.DummyItem("8", "Enchantments"));
                DummyContent.addItem(new DummyContent.DummyItem("9", "Gate 2"));
                break;
            case 9:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart 1"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Weapon Shop"));
                DummyContent.addItem(new DummyContent.DummyItem("5", "Magical Garden"));
                DummyContent.addItem(new DummyContent.DummyItem("6", "Food Quart 2"));
                DummyContent.addItem(new DummyContent.DummyItem("7", "Food Quart 3"));
                DummyContent.addItem(new DummyContent.DummyItem("8", "Enchantments"));
                DummyContent.addItem(new DummyContent.DummyItem("9", "Gate 2"));
                DummyContent.addItem(new DummyContent.DummyItem("10", "Gate 3"));
                break;
            case 10:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart 1"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Weapon Shop"));
                DummyContent.addItem(new DummyContent.DummyItem("5", "Magical Garden"));
                DummyContent.addItem(new DummyContent.DummyItem("6", "Food Quart 2"));
                DummyContent.addItem(new DummyContent.DummyItem("7", "Food Quart 3"));
                DummyContent.addItem(new DummyContent.DummyItem("8", "Enchantments"));
                DummyContent.addItem(new DummyContent.DummyItem("9", "Gate 2"));
                DummyContent.addItem(new DummyContent.DummyItem("10", "Gate 3"));
                DummyContent.addItem(new DummyContent.DummyItem("11", "Trade Center"));
                break;
            case 11:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart 1"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Weapon Shop"));
                DummyContent.addItem(new DummyContent.DummyItem("5", "Magical Garden"));
                DummyContent.addItem(new DummyContent.DummyItem("6", "Food Quart 2"));
                DummyContent.addItem(new DummyContent.DummyItem("7", "Food Quart 3"));
                DummyContent.addItem(new DummyContent.DummyItem("8", "Enchantments"));
                DummyContent.addItem(new DummyContent.DummyItem("9", "Gate 2"));
                DummyContent.addItem(new DummyContent.DummyItem("10", "Gate 3"));
                DummyContent.addItem(new DummyContent.DummyItem("11", "Trade Center"));
                DummyContent.addItem(new DummyContent.DummyItem("12", "Sourcerer"));
                break;
            case 12:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart 1"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Weapon Shop"));
                DummyContent.addItem(new DummyContent.DummyItem("5", "Magical Garden"));
                DummyContent.addItem(new DummyContent.DummyItem("6", "Food Quart 2"));
                DummyContent.addItem(new DummyContent.DummyItem("7", "Food Quart 3"));
                DummyContent.addItem(new DummyContent.DummyItem("8", "Enchantments"));
                DummyContent.addItem(new DummyContent.DummyItem("9", "Gate 2"));
                DummyContent.addItem(new DummyContent.DummyItem("10", "Gate 3"));
                DummyContent.addItem(new DummyContent.DummyItem("11", "Trade Center"));
                DummyContent.addItem(new DummyContent.DummyItem("12", "Sourcerer"));
                DummyContent.addItem(new DummyContent.DummyItem("13", "Fight the Dragon"));
                DummyContent.addItem(new DummyContent.DummyItem("14", "Shield Generator"));
                break;
            case 13:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart 1"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Weapon Shop"));
                DummyContent.addItem(new DummyContent.DummyItem("5", "Magical Garden"));
                DummyContent.addItem(new DummyContent.DummyItem("6", "Food Quart 2"));
                DummyContent.addItem(new DummyContent.DummyItem("7", "Food Quart 3"));
                DummyContent.addItem(new DummyContent.DummyItem("8", "Enchantments"));
                DummyContent.addItem(new DummyContent.DummyItem("9", "Gate 2"));
                DummyContent.addItem(new DummyContent.DummyItem("10", "Gate 3"));
                DummyContent.addItem(new DummyContent.DummyItem("11", "Trade Center"));
                DummyContent.addItem(new DummyContent.DummyItem("12", "Sourcerer"));
                DummyContent.addItem(new DummyContent.DummyItem("13", "Fight the Dragon"));
                DummyContent.addItem(new DummyContent.DummyItem("14", "Shield Generator"));
                break;
            case 14:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart 1"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Weapon Shop"));
                DummyContent.addItem(new DummyContent.DummyItem("5", "Magical Garden"));
                DummyContent.addItem(new DummyContent.DummyItem("6", "Food Quart 2"));
                DummyContent.addItem(new DummyContent.DummyItem("7", "Food Quart 3"));
                DummyContent.addItem(new DummyContent.DummyItem("8", "Enchantments"));
                DummyContent.addItem(new DummyContent.DummyItem("9", "Gate 2"));
                DummyContent.addItem(new DummyContent.DummyItem("10", "Gate 3"));
                DummyContent.addItem(new DummyContent.DummyItem("11", "Trade Center"));
                DummyContent.addItem(new DummyContent.DummyItem("12", "Sourcerer"));
                DummyContent.addItem(new DummyContent.DummyItem("13", "Fight the Dragon"));
                DummyContent.addItem(new DummyContent.DummyItem("14", "Shield Generator"));
                DummyContent.addItem(new DummyContent.DummyItem("15", "End Credits"));
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if ((int) id == (SCAVENGER_PROGRESS - 1)) {
            SCAVENGER_PROGRESS++;
            userLocalStorage.setScavengerHunt(SCAVENGER_PROGRESS);
        }

        System.out.println("Position: " + position + " ID = " + id);
        switch ((int) id) {
            case 0:
                Toast.makeText(getActivity(), "Gate 1!", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("IT Bldg, Pretoria,Gauteng 0083,South Africa");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 1:
                Toast.makeText(getActivity(), "Food Quart 1!", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Oom Gert se Plek, Pretoria,Gauteng 0083,South Africa");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 2:
                Toast.makeText(getActivity(), "Armourie!", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Centenary Bldg, Pretoria,Gauteng 0083,South Africa");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 3:
                Toast.makeText(getActivity(), "Weapon Shop!", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Sci-Enza, Pretoria,Gauteng 0083,South Africa");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 4:
                Toast.makeText(getActivity(), "Magical Garden!", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Fairy Garden\n" +
                        "                University Rd, Pretoria, 0132");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 5:
                Toast.makeText(getActivity(), "Food Quart 2!", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Steers - Tukkies");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 6:
                Toast.makeText(getActivity(), "Food Quart 3!", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Aula Pretoria,Gauteng 0083,South Africa");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 7:
                Toast.makeText(getActivity(), "Enchantments!", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Music Library - University of Pretoria\n" +
                        "Pretoria, 0132");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 8:
                Toast.makeText(getActivity(), "Gate 2!", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Walkway around Arts Bldg");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 9:
                Toast.makeText(getActivity(), "Gate 3!", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Human Sciences Bldg", "-25.755344", "28.230726");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 10:
                Toast.makeText(getActivity(), "Trade Center!", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Trade Center", "-25.755228", " 28.231249");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 11:
                Toast.makeText(getActivity(), "Sourcerer!", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Merensky Library\n" +
                        "Pretoria, 0132");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 12:
                Toast.makeText(getActivity(), "Fight the Dragon!", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Human Sciences Bldg");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 13:
                Toast.makeText(getActivity(), "Shield Generator!", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Piazza, Pretoria,Gauteng 0083,South Africa");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            default:
                break;
        }
    }


}
