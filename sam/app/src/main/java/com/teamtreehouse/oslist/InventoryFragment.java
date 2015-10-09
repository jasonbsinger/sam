package com.teamtreehouse.oslist;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;


public class InventoryFragment extends Fragment implements View.OnClickListener {

    ImageAdapter adapter;
    UserLocalStorage userLocalStorage;

    public InventoryFragment() {
        // Required empty public constructor
    }

    public static InventoryFragment newInstance(String param1, String param2) {
        InventoryFragment fragment = new InventoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_inventory);

        // Button input = (Button) findViewById(R.id.huntButton);
        // input.setOnClickListener(this);
    }

    public void onRetrieve() {


        EditText input = (EditText) getView().findViewById(R.id.etHuntCode);
        String code = input.getText().toString();

        System.out.println("The entered code is: " + code);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_inventory, container, false);

        userLocalStorage = new UserLocalStorage(getActivity());
        //adapter = new ImageAdapter(getActivity());

        final GridView gridview = (GridView) v.findViewById(R.id.inventoryGridview);
        gridview.setAdapter(new ImageAdapter(getActivity()));

        Button input = (Button) v.findViewById(R.id.huntButton);
        input.setOnClickListener(this);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                /*if(position == 1){

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.root, new InventoryFragment()).commit();
                }else if(position == 4){

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.root, new InventoryFragment()).commit();
                }else{
                    System.out.println("No at position  1, at position "+position);
                }*/
                Toast.makeText(getActivity(), "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        EditText input = (EditText) getView().findViewById(R.id.etHuntCode);
        String code = input.getText().toString();

        switch (code) {
            case "kidawuc": //k
                userLocalStorage.setInventoryItem(0, R.drawable.bronze_key);
                userLocalStorage.setInventoryItemSet(0, true);
                break;
            case "omibuh": //o
                if (userLocalStorage.isInventoryItemSet(0)) {
                    userLocalStorage.setInventoryItem(0, R.drawable.green_crystal);
                } else {
                    Toast.makeText(getActivity(), "You cannot use this code yet",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case "negimeg": //n
                userLocalStorage.setInventoryItem(3, R.drawable.food);
                userLocalStorage.setInventoryItemSet(3, true);
                break;
            case "amufew"://a
                userLocalStorage.setInventoryItem(1, R.drawable.gold_key);
                userLocalStorage.setInventoryItemSet(1, true);
                break;
            case "motopom"://m
                userLocalStorage.setInventoryItem(4, R.drawable.bow_and_arrow);
                userLocalStorage.setInventoryItemSet(4, true);
                break;
            case "mokopan"://m
                userLocalStorage.setInventoryItem(4, R.drawable.sword);
                userLocalStorage.setInventoryItemSet(4, true);
                break;
            case "motakoj"://m
                userLocalStorage.setInventoryItem(4, R.drawable.force_of_nature);
                userLocalStorage.setInventoryItemSet(4, true);
                break;
            case "itamuj"://i
                if (userLocalStorage.isInventoryItemSet(3) && userLocalStorage.isInventoryItemSet(1)) {
                    userLocalStorage.setInventoryItem(3, R.drawable.empty_inventory);
                    userLocalStorage.setInventoryItem(1, R.drawable.blue_crystal);
                } else {
                    Toast.makeText(getActivity(), "You need food & Key",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case "cujufiv"://c
                userLocalStorage.setInventoryItem(5, R.drawable.spell);
                userLocalStorage.setInventoryItemSet(5, true);
                break;
            case "oorugat"://o
                userLocalStorage.setInventoryItem(2, R.drawable.crystal_key);
                userLocalStorage.setInventoryItemSet(2, true);
                break;
            case "dopemuy"://d
                break;
            case "eebudeh"://e
                if (userLocalStorage.isInventoryItemSet(2)) {
                    userLocalStorage.setInventoryItem(2, R.drawable.purple_crystal);
                } else {
                    Toast.makeText(getActivity(), "You need the crystal key",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case "upupdowndownleftrightleftrightba":
            case "uuddlrlrba":
                break;
            case "upupdowndownleftrightleftrightab":
            case "uuddlrlrab":
                break;
            case "cl3@n":
                for (int i = 0; i < 6; i++) {
                    userLocalStorage.setInventoryItem(i, R.drawable.empty_inventory);
                    userLocalStorage.setInventoryItemSet(i, false);
                }

                break;
            default:
                Toast.makeText(getActivity(), "Invalid code entered",
                        Toast.LENGTH_SHORT).show();
                input.setText("");
                break;
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.root, new InventoryFragment()).commit();
    }
}
