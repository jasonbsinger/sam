package com.tuks.sam;

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

import com.tuks.sam.dummy.DummyContent;

public class ItemFragment extends Fragment implements AbsListView.OnItemClickListener {

    public static int SCAVENGER_PROGRESS;// = 1;
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
        SCAVENGER_PROGRESS = 1;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userLocalStorage = new UserLocalStorage(getActivity());
        SCAVENGER_PROGRESS = userLocalStorage.getScavengerHunt();

        mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);


    }

    @Override
    public void onStart() {
        super.onStart();
        SCAVENGER_PROGRESS = userLocalStorage.getScavengerHunt();
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
        int index = userLocalStorage.getScavengerHunt() - 1;
        switch (index) {
            case 0:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1 - IT Building"));
                break;
            case 1:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1 - IT Building"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart - Oom Gert"));
                break;
            case 2:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1 - IT Building"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart - Oom Gert"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie - Centenary"));
                break;
            case 3:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1 - IT Building"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart - Oom Gert"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie - Centenary"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Gate 2 - Visual Arts"));
                break;
            case 4:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1 - IT Building"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart - Oom Gert"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie - Centenary"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Gate 2 - Visual Arts"));
                DummyContent.addItem(new DummyContent.DummyItem("5", "Enchanted Merchant - Musion"));
                break;
            case 5:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1 - IT Building"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart - Oom Gert"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie - Centenary"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Gate 2 - Visual Arts"));
                DummyContent.addItem(new DummyContent.DummyItem("5", "Enchanted Merchant - Musion"));
                DummyContent.addItem(new DummyContent.DummyItem("6", "Forrest - Botanical Gardens"));
                break;
            case 6:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1 - IT Building"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart - Oom Gert"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie - Centenary"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Gate 2 - Visual Arts"));
                DummyContent.addItem(new DummyContent.DummyItem("5", "Enchanted Merchant - Musion"));
                DummyContent.addItem(new DummyContent.DummyItem("6", "Forrest - Botanical Gardens"));
                DummyContent.addItem(new DummyContent.DummyItem("7", "Gate 3 - Humanities Building"));
                break;
            case 7:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1 - IT Building"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart - Oom Gert"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie - Centenary"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Gate 2 - Visual Arts"));
                DummyContent.addItem(new DummyContent.DummyItem("5", "Enchanted Merchant - Musion"));
                DummyContent.addItem(new DummyContent.DummyItem("6", "Forrest - Botanical Gardens"));
                DummyContent.addItem(new DummyContent.DummyItem("7", "Gate 3 - Humanities Building"));
                DummyContent.addItem(new DummyContent.DummyItem("8", "Shield Generator - Piazza"));
                break;
            default:
                DummyContent.addItem(new DummyContent.DummyItem("1", "Gate 1 - IT Building"));
                DummyContent.addItem(new DummyContent.DummyItem("2", "Food Quart - Oom Gert"));
                DummyContent.addItem(new DummyContent.DummyItem("3", "Armourie - Centenary"));
                DummyContent.addItem(new DummyContent.DummyItem("4", "Gate 2 - Visual Arts"));
                DummyContent.addItem(new DummyContent.DummyItem("5", "Enchanted Merchant - Musion"));
                DummyContent.addItem(new DummyContent.DummyItem("6", "Forrest - Botanical Gardens"));
                DummyContent.addItem(new DummyContent.DummyItem("7", "Gate 3 - Humanities Building"));
                DummyContent.addItem(new DummyContent.DummyItem("8", "Shield Generator - Piazza"));
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int index = userLocalStorage.getScavengerHunt() - 1;
        if (id == index) {
            SCAVENGER_PROGRESS++;
        }

        userLocalStorage.setScavengerHunt(SCAVENGER_PROGRESS);
        userLocalStorage.setInstructionCoords(false);
        System.out.println("Position: " + position + " ID = " + id);
        switch ((int) id) {
            case 0:
                Toast.makeText(getActivity(), "Gate 1 - IT Building", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("IT Bldg, Pretoria,Gauteng 0083,South Africa");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 1:
                Toast.makeText(getActivity(), "Food Quart - Oom Gert", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Oom Gert se Plek, Pretoria,Gauteng 0083,South Africa");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 2:
                Toast.makeText(getActivity(), "Armourie - Centenary", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Centenary Bldg, Pretoria,Gauteng 0083,South Africa");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 3:
                Toast.makeText(getActivity(), "Gate 2 - Visual Arts", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Walkway around Arts Bldg");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 4:
                Toast.makeText(getActivity(), "Enchanted Murchant - Musion!", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Music Library - University of Pretoria\n" +
                        "Pretoria, 0132");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 5:
                Toast.makeText(getActivity(), "Forrest - Botanical Gardens", Toast.LENGTH_SHORT).show();
                //userLocalStorage.setInstructionCoords(true);
                //-25.752051, 28.228983
                userLocalStorage.setNewLocation("AE du Toit Annex");//,"-25.752051","28.228983");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 6:
                Toast.makeText(getActivity(), "Gate 3!", Toast.LENGTH_SHORT).show();
                //userLocalStorage.setInstructionCoords(true);
                userLocalStorage.setNewLocation("Humanities Bldg");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 7:
                Toast.makeText(getActivity(), "Shield Generator!", Toast.LENGTH_SHORT).show();
                userLocalStorage.setNewLocation("Piazza");
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            default:
                break;
        }
    }


}
