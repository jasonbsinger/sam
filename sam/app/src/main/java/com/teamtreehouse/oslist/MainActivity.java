package com.teamtreehouse.oslist;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final double EARTH_RADIUS = 6378100.0;
    private static String GMS_SEARCH_ACTION = "com.google.android.gms.actions.SEARCH_ACTION";
    RelativeLayout root;
    UserLocalStorage userLocalStorage;
    FloatingActionButton fab_directions;
    HashMap<String, String> listOfDirections = new HashMap<>();
    String instructions = "";
    boolean isBottom = false;
    private String mQuery;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private TextView tv_directions;
    private GoogleMap mMap;

    /*private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }*/
    private int offset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLocalStorage = new UserLocalStorage(this);
        //userLocalStorage.setLoggedInStatus(false);

        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        root = (RelativeLayout) findViewById(R.id.root);

        fab_directions = (FloatingActionButton) findViewById(R.id.fab_directions);

        //tv_directions = (TextView)findViewById(R.id.tv_directions);

        addDrawerItems();
        setupDrawer();
        setUpMapIfNeeded();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(false);


        instructions = "Please search for a place first";


        fab_directions.setOnClickListener(this);
        onNewIntent(getIntent());
    }

    protected void onNewIntent(Intent intent) {
        System.out.println("##################################################################################################");
        System.out.println("New Intent");
        String action = intent.getAction();
        System.out.println("New Intent = " + action);
        System.out.println("##################################################################################################");

        if (action != null) {
            if (action.equals(Intent.ACTION_SEARCH) || action.equals(GMS_SEARCH_ACTION)) {
                mQuery = intent.getStringExtra(SearchManager.QUERY);
                //mQuery = intent.getStringExtra(SearchManager.QUERY);
                System.out.println("Bubbles = " + mQuery);
                setupDirectionSearch(mQuery);
            } else {
                System.out.print("Not aciton serach");
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!authenticate()) {
            startActivity(new Intent(MainActivity.this, Login.class));
        } else {
            if (userLocalStorage.isInstructionClicked()) {
                boolean coords = userLocalStorage.isInstructionCoords();

                userLocalStorage.setInstructionCoords(false);
                userLocalStorage.setInstructionClicked(false);

                if (coords) {
                    setupDirectionSearch(userLocalStorage.getInstructionLocation(), coords, Double.parseDouble(userLocalStorage.getInstructionLocationLongitude()), Double.parseDouble(userLocalStorage.getInstructionLocationLatitude()));
                } else {
                    setupDirectionSearch(userLocalStorage.getInstructionLocation(), coords, 0, 0);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private boolean authenticate() {
        return userLocalStorage.isLoggedIn();
    }

    private void addDrawerItems() {
        String[] osArray = {"Map", "Inventory", "Scavenger Hunt", "Send Feedback"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                displayView(position);

                switch ((int) id) {
                    case 0:
                        Toast.makeText(MainActivity.this, "Map!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "Inventory!", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "Scavenger Hunt!", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "Send Feedback", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Not Implimented Yet!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void displayView(int position) {
        Fragment fragment = null;
        fab_directions.hide();
        switch (position) {
            case 0:
                this.onResume();
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                fab_directions.show();
                break;
            case 1:
                this.onPause();
                fragment = new InventoryFragment();
                break;
            case 2:
                this.onPause();
                fragment = new ItemFragment();
                break;
            default:
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.root, fragment).commit();

            // Update selected item and title, then close the drawer
            //mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle("Testing 1 2 3 ");
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // Log error
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Menu");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout_settings) {
            System.out.println("LOGOUT");
            userLocalStorage.setLoggedInStatus(false);
            startActivity(new Intent(MainActivity.this, Login.class));
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int convertMetersToPixels(double lat, double lng, double radiusInMeters) {

        double lat1 = radiusInMeters / EARTH_RADIUS;
        double lng1 = radiusInMeters / (EARTH_RADIUS * Math.cos((Math.PI * lat / 180)));

        double lat2 = lat + lat1 * 180 / Math.PI;
        double lng2 = lng + lng1 * 180 / Math.PI;

        Point p1 = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap().getProjection().toScreenLocation(new LatLng(lat, lng));
        Point p2 = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap().getProjection().toScreenLocation(new LatLng(lat2, lng2));

        return Math.abs(p1.x - p2.x);
    }

    private Bitmap getBitmap(double lat, double lng, int size, int image) {

        // fill color
        Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setColor(0x110000FF);
        paint1.setStyle(Paint.Style.FILL);

        // stroke color
        Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setColor(0xFF0000FF);
        paint2.setStyle(Paint.Style.STROKE);

        // icon
        Bitmap icon = BitmapFactory.decodeResource(getResources(), image);

        // circle radius - 200 meters
        int radius = offset = convertMetersToPixels(lat, lng, size);

        // if zoom too small
        if (radius < icon.getWidth() / 2) {

            radius = icon.getWidth() / 2;
        }

        // create empty bitmap
        Bitmap b = Bitmap.createBitmap(radius * 2, radius * 2, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);

        // draw blue area if area > icon size
        if (radius != icon.getWidth() / 2) {

            c.drawCircle(radius, radius, radius, paint1);
            c.drawCircle(radius, radius, radius, paint2);
        }

        // draw icon
        c.drawBitmap(icon, radius - icon.getWidth() / 2, radius - icon.getHeight() / 2, new Paint());

        return b;
    }

    public void setupDirectionSearch(String location, boolean coords, double longitude, double latitude) {
        mMap.clear();
        System.out.println("location = " + location + "\nLongitude = " + longitude + "\nLatitude = " + latitude);

        //EditText input = (EditText) findViewById(R.id.editText);
        List<Address> addressList = null;
        userLocalStorage.addDirections("");
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                location += ",Pretoria,Gauteng 0083,South Africa";
                if (coords) {
                    addressList = geocoder.getFromLocation(longitude, latitude, 1);
                } else {
                    addressList = geocoder.getFromLocationName(location, 1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addressList.get(0);
            LatLng origin = new LatLng(getMyLocation().getLatitude(), getMyLocation().getLongitude());
            mMap.addMarker(new MarkerOptions().position(origin).title("You are here."));

            LatLng destination = new LatLng(address.getLatitude(), address.getLongitude());
            MarkerOptions marker = new MarkerOptions().position(destination).title("X marks the spot");
            //LevelListDrawable drawer = (LevelListDrawable) getResources().getDrawable(R.drawable.x);
            // BitmapDrawable img = (BitmapDrawable) drawer.getCurrent();
            // Bitmap x_marker = Bitmap.createScaledBitmap(img.getBitmap(), img.getBitmap().getWidth()/2, img.getBitmap().getHeight()/2, false);
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.x_small));
            marker.draggable(true);

            mMap.addMarker(marker);


            //mMap.addMarker(new MarkerOptions().position(destination).title("Destination"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination, 17));


            System.out.println("\n--------------------------------------------------\n");
            System.out.println(getDirectionsUrl(origin, destination));
            System.out.println("\n--------------------------------------------------\n");
        } else {
            Toast.makeText(this, "Location not found!", Toast.LENGTH_SHORT).show();
        }

        getCongestion();
        //input.setText("");
    }

    public void setupDirectionSearch(String location) {
        mMap.clear();

        EditText input = (EditText) findViewById(R.id.editText);
        List<Address> addressList = null;
        userLocalStorage.addDirections("");
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                input.setText(location);
                location += ", Pretoria, Gauteng 0083, South Africa";
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addressList.get(0);
            LatLng origin = new LatLng(getMyLocation().getLatitude(), getMyLocation().getLongitude());
            mMap.addMarker(new MarkerOptions().position(origin).title("You are here."));

            LatLng destination = new LatLng(address.getLatitude(), address.getLongitude());
            MarkerOptions marker = new MarkerOptions().position(destination).title("X marks the spot");
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.x_small));
            marker.draggable(true);

            mMap.addMarker(marker);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination, 17));

            System.out.println("\n--------------------------------------------------\n");
            System.out.println(getDirectionsUrl(origin, destination));
            System.out.println("\n--------------------------------------------------\n");

        } else {
            Toast.makeText(this, "Location not found!", Toast.LENGTH_SHORT).show();
        }
        getCongestion();
    }

    public void onSearch(View view) {

        mMap.clear();
        EditText input = (EditText) findViewById(R.id.editText);
        String location = input.getText().toString();

        List<Address> addressList = null;
        userLocalStorage.addDirections("");
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                location += ",Pretoria,Gauteng 0083,South Africa";
                addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addressList.get(0);

            LatLng origin = new LatLng(getMyLocation().getLatitude(), getMyLocation().getLongitude());
            mMap.addMarker(new MarkerOptions().position(origin).title("You are here."));

            LatLng destination = new LatLng(address.getLatitude(), address.getLongitude());
            MarkerOptions marker = new MarkerOptions().position(destination).title("X marks the spot");
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.x_small));
            marker.draggable(true);

            mMap.addMarker(marker);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination, 17));


            System.out.println("\n--------------------------------------------------\n");
            System.out.println(getDirectionsUrl(origin, destination));
            System.out.println("\n--------------------------------------------------\n");


        } else {
            Toast.makeText(this, "Location not found!", Toast.LENGTH_SHORT).show();
        }

        getCongestion();
        //input.setText("");
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }

        getCongestion();
    }

    public void getCongestion() {
        // MarkerOptions options = new MarkerOptions();
        LatLng p = new LatLng(-25.754711, 28.231280);
        mMap.addCircle(new CircleOptions()
                .center(new LatLng(p.latitude, p.longitude))
                .radius(50)
                .strokeColor(Color.WHITE)
                .strokeWidth(1.0f)
                .fillColor(Color.parseColor("#337f64c5")));

        p = new LatLng(-25.755622, 28.232935);
        mMap.addCircle(new CircleOptions()
                .center(new LatLng(p.latitude, p.longitude))
                .radius(30)
                .strokeColor(Color.WHITE)
                .strokeWidth(1.0f)
                .fillColor(Color.parseColor("#334287c8")));

        /*mMap.addCircle(new CircleOptions()
                .center(new LatLng(-25.755146, 28.228099))
                .radius(5)
                .strokeColor(Color.WHITE)
                .strokeWidth(1.0f)
                .fillColor(Color.parseColor("#337f64c5")));*/
    }


    private void setUpMap() {
        //LatLng latlng = new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude());
        LatLng latlng = new LatLng(-25.755755, 28.231209);
        mMap.addMarker(new MarkerOptions().position(latlng).title("You Are Here"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 17));

        //LatLngBounds bounds = boundsWithCenterAndLatLngDistance(new LatLng(51.0, 19.0), 1000, 2000);
        //mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0));

        //mMap.setMyLocationEnabled(true);

    }

    private Location getMyLocation() {
        LatLng latlng = new LatLng(-25.755755, 28.231209);
        mMap.setMyLocationEnabled(true);
        Location location = new Location("My Position");
        location.setLatitude(-25.755755);
        location.setLongitude(28.231209);

        //location =  mMap.getMyLocation();
        return location;
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        EditText input = (EditText) findViewById(R.id.editText);
        String loc = input.getText().toString();

        if (loc == "" || loc == null) {
            loc = userLocalStorage.getInstructionLocation();
        }


        final String location = loc;


        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Mode of route
        String mode = "mode=walking";

        // Alternative Routes enabled
        String alternatives = "alternatives=true";

        // Units of measurement for route
        String units = "units=metric";

        String avoid = "avoid=tolls|highways|ferries";

        String transit_routing_preference = "transit_routing_preference=less_walking";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode + "&" + alternatives + "&" + units + "&" + avoid + "&" + transit_routing_preference;/*+"&"+key;*/

        // Output format
        String output = "json";

        String urlString = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.getDirectionsInBackground(urlString, new GetJsonCallback() {
            @Override
            public void done(JSONObject pathContent) {
                instructions = "Directions to " + location + "\n";
                if (pathContent == null) {
                    System.out.println("Path Content is null");
                } else {
                    System.out.println("SUCCESSSSSSSSSSSSSSSSSSSSSS \n\n" + pathContent);
                    List<List<HashMap<String, String>>> routes = new ArrayList<List<HashMap<String, String>>>();
                    JSONArray jRoutes = null, jLegs = null, jSteps = null;
                    try {
                        jRoutes = pathContent.getJSONArray("routes");
                        System.out.println("Number of routes: " + jRoutes.length());

                        for (int i = 0; i < jRoutes.length(); i++) {
                            jLegs = ((JSONObject) jRoutes.get(i)).getJSONArray("legs");
                            List path = new ArrayList<HashMap<String, String>>();

                            for (int j = 0; j < jLegs.length(); j++) {
                                jSteps = ((JSONObject) jLegs.get(j)).getJSONArray("steps");

                                for (int k = 0; k < jSteps.length(); k++) {
                                    String polyline = "";
                                    polyline = (String) ((JSONObject) ((JSONObject) jSteps.get(k)).get("polyline")).get("points");
                                    List<LatLng> list = decodePoly(polyline);

                                    for (int l = 0; l < list.size(); l++) {
                                        HashMap<String, String> hm = new HashMap<String, String>();
                                        hm.put("lat", Double.toString(list.get(l).latitude));
                                        hm.put("lng", Double.toString(list.get(l).longitude));
                                        path.add(hm);
                                    }
                                }
                                routes.add(path);
                            }
                        }
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    //return routes;

                    Random r = new Random();
                    int Low = 0;
                    int High = routes.size();

                    int leastCongestedRoute = r.nextInt(High - Low) + Low;

                    //String colours[] = {"#558C89", "#74AFAD", "#D9853B", "#ECECEA"};
                    PolylineOptions options = new PolylineOptions();


                    System.out.println(routes);


                    instructions = "";
                    instructions = "Directions to " + location + "\n\n";
                    for (int j = 0; j < routes.size(); j++) {
                        if (j == leastCongestedRoute) {
                            options.color(Color.parseColor("#67BCDB")); ///*colours[j % colours.length]*/
                            options.zIndex(routes.size() + 1);
                            options.width(10);
                            options.visible(true);

                            //JSONArray steps = ((JSONObject)(((JSONObject) pathContent.getJSONArray("routes").get(leastCongestedRoute)).getJSONArray("legs").get(leastCongestedRoute))).getJSONArray("steps");
                            try {
                                jRoutes = pathContent.getJSONArray("routes");
                                System.out.println("Number of routes2: " + jRoutes.length());

                                jLegs = ((JSONObject) jRoutes.get(leastCongestedRoute)).getJSONArray("legs");
                                List path = new ArrayList<HashMap<String, String>>();

                                for (int i = 0; i < jLegs.length(); i++) {
                                    jSteps = ((JSONObject) jLegs.get(i)).getJSONArray("steps");

                                    for (int k = 0; k < jSteps.length(); k++) {
                                        String distance = (String) ((JSONObject) ((JSONObject) jSteps.get(k)).get("distance")).get("text");
                                        String duration = (String) ((JSONObject) ((JSONObject) jSteps.get(k)).get("duration")).get("text");

                                        System.out.println(k + ") For " + distance + " (" + duration + ") - " + Html.fromHtml(((JSONObject) jSteps.get(k)).get("html_instructions").toString()));
                                        instructions += "\n For " + distance + " (" + duration + ") - " + Html.fromHtml(((JSONObject) jSteps.get(k)).get("html_instructions").toString());
                                    }
                                }
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }


                        } else {
                            options.color(Color.parseColor("#dcdbcf")); ///*colours[j % colours.length]*/
                            options.zIndex(j);
                            //options.visible(false);
                        }

                        options.width(5);
                        options.visible(true);


                        for (int i = 0; i < routes.get(j).size(); i++) {
                            options.add(new LatLng(Double.parseDouble(routes.get(j).get(i).get("lat")), Double.parseDouble(routes.get(j).get(i).get("lng"))));
                        }

                        mMap.addPolyline(options);
                        options = new PolylineOptions();

                    }

                    userLocalStorage.addDirections(instructions);

                }
            }
        });

        return "BUBBLES";
    }

    /**
     * Method to decode polyline points
     * Courtesy : http://jeffreysambells.com/2010/05/27/decoding-polylines-from-google-maps-direction-api-with-java
     */
    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.fab_location:
                //System.out.println("Directions = \n\n"+userLocalStorage.getSearchHostory()+"\n\n");
                mMap.setMyLocationEnabled(true);
                //mMap.getMyLocation();
                break;*/
            case R.id.fab_directions:
                Toast.makeText(MainActivity.this, instructions, Toast.LENGTH_LONG).show();
                /*if (isBottom) {
                    SlideUp();
                    isBottom = false;
                } else {
                    //tv_directions.setPadding(0, 0, 0, 10);
                    SlideDown();
                    isBottom = true;
                }*/
            //tv_directions.setText(userLocalStorage.getDirections());
                break;
        }
    }
}

