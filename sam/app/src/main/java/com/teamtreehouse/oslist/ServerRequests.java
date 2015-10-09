package com.teamtreehouse.oslist;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Jason.Singer on 09/10/2015.
 */
public class ServerRequests {

    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://sammie.webatu.com/";
    ProgressDialog progressDialog;

    public ServerRequests(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("... Please Wait ...");
    }

    public static String convertinputStreamToString(InputStream ists)
            throws IOException {

        System.out.println("TESTING CONVERT INPUT STREAM TO STRING");
        if (ists != null) {
            System.out.println("\n-------------------   NOT   EMPTY    ------------\n");
            System.out.println(ists.toString());
            System.out.println("\n-------------------   NOT   EMPTY    ------------\n");
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader r1 = new BufferedReader(new InputStreamReader(
                        ists, "UTF-8"));
                while ((line = r1.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                ists.close();
            }
            return sb.toString();
        } else {
            System.out.println("\n-------------------   EMPTY    ------------\n");
            return "";
        }
    }

    public void storeUserDataInBackground(User user, GetUserCallback callback) {
        progressDialog.show();
        new StoreUserDataAsyncTask(user, callback).execute();
    }

    public void fetchUserDataInBackground(User user, GetUserCallback callback) {
        progressDialog.show();
        new FetchUserDataAsyncTask(user, callback).execute();

    }

    public void getDirectionsInBackground(String urlString, GetJsonCallback callback) {
        progressDialog.show();
        new GetDirectionsAsyncTask(urlString, callback).execute();

    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void> {

        User user;
        GetUserCallback callback;

        public StoreUserDataAsyncTask(User user, GetUserCallback callback) {
            this.user = user;
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            System.out.println("STORE USER DATA: \t\t Name: " + user.name + "\nAge: " + user.age + "\nUsername: " + user.username + " \nPassword: " + user.password);

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("name", user.name));
            dataToSend.add(new BasicNameValuePair("age", user.age + ""));
            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestPrams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestPrams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestPrams, CONNECTION_TIMEOUT);

            //HttpClient client = new DefaultHttpClient();
            HttpClient client = new DefaultHttpClient(httpRequestPrams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "Register.php");
            //HttpRequest req = new HttpRequest(SERVER_ADDRESS);

            try {

                //System.out.println("\n\nTest\n\n" + post.toString() + "\n\n");
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                //System.out.println("\n\nTest\n\n" + post.toString() + "\n\n");

                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            callback.done(null);
            super.onPostExecute(aVoid);
        }
    }

    public class FetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {

        User user;
        GetUserCallback callback;

        public FetchUserDataAsyncTask(User user, GetUserCallback callback) {
            this.user = user;
            this.callback = callback;
        }

        @Override
        protected User doInBackground(Void... params) {
            /*ContentValues dataToSend=new ContentValues();
            dataToSend.put("username",user.username);
            dataToSend.put("password", user.password);*/

           /* HttpURLConnection urlConnection = null;
            URL url = null;
            try {
                url = new URL(SERVER_ADDRESS + "FetchUserData.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.connect();
                InputStream is =  urlConnection.getInputStream();
                pathContent = convertinputStreamToString(is);

            } catch (Exception e) {
                e.printStackTrace();
            }*/

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestPrams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestPrams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestPrams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestPrams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchUserData.php");

            User returnedUser = null;
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);

                System.out.println("Result: = : " + result);
                JSONObject jObject = new JSONObject(result);

                System.out.println(jObject);
                //JSONArray jArray = jObject.getJSONArray();

                if (jObject.length() == 0) {
                    System.out.println("User is NULL");
                    returnedUser = null;
                } else {

                    String name = jObject.getString("name");
                    int age = jObject.getInt("age");
                    returnedUser = new User(name, age, user.username, user.password);

                    System.out.println("Returned USER:\n\nName: " + returnedUser.name + "\nAge: " + returnedUser.age + "\nUsername: " + returnedUser.username + "\nPassword: " + returnedUser.password);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser) {
            super.onPostExecute(returnedUser);
            progressDialog.dismiss();
            callback.done(returnedUser);
        }
    }

    public class GetDirectionsAsyncTask extends AsyncTask<Void, Void, JSONObject> {

        String urlString, pathContent;
        GetJsonCallback callback;

        public GetDirectionsAsyncTask(String urlString, GetJsonCallback callback) {
            this.urlString = urlString;
            this.callback = callback;
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            URL url = null;
            try {
                url = new URL(urlString);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.connect();
                InputStream is = urlConnection.getInputStream();
                pathContent = convertinputStreamToString(is);

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                return new JSONObject(pathContent);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject pathContent) {
            super.onPostExecute(pathContent);
            progressDialog.dismiss();
            callback.done(pathContent);
        }
    }
}
