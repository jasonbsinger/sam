package com.teamtreehouse.oslist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Jason.Singer on 09/10/2015.
 */
public class ImageAdapter extends BaseAdapter {

    UserLocalStorage userLocalStorage;
    private Context mContext;
    private Integer[] mThumbIds = {
            R.drawable.empty_inventory, R.drawable.empty_inventory, R.drawable.empty_inventory,
            R.drawable.empty_inventory, R.drawable.empty_inventory, R.drawable.empty_inventory
    };

    public ImageAdapter(Context c) {
        mContext = c;

        //for(int i = 0; i < 6; i++)
        //    userLocalStorage.setInventoryItem(i,R.drawable.empty_inventory);
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    public void setmThumbId(int id, int image) {
        userLocalStorage.setInventoryItem(id, image);
        mThumbIds[id] = userLocalStorage.getInventoryItem(id);
        //mThumbIds[id] = image;
    }

    // references to our images

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        userLocalStorage = new UserLocalStorage(mContext);

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        if (userLocalStorage.isInventoryItemSet(position)) {
            System.out.println("Item (" + position + ") is set");
        } else {
            System.out.println("Item (" + position + ") is not set");
        }

        imageView.setImageResource(getMThumbs()[position]);
        return imageView;
    }

    public Integer[] getMThumbs() {

        for (int i = 0; i < 6; i++) {
            System.out.println(userLocalStorage.getInventoryItem(i));
            mThumbIds[i] = userLocalStorage.getInventoryItem(i);
        }

        return mThumbIds;
    }

}
