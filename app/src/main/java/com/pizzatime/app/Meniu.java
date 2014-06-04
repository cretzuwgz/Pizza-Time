package com.pizzatime.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Meniu extends ListFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    // url to get all products list
    private static String url_not = "http://pizzatime.webege.com/get_tipuri.php";

    // JSON Node names
    private static final String TAG_TIP = "tipuri";
    private static final String TAG_ID = "id_tip";
    private static final String TAG_NUME = "nume_tip";
    private static final String TAG_POZA = "link_poza";
    static long tip_ales=0;

    // products JSONArray
    JSONArray products = null;
    private LayoutInflater mInflater;
    ProductAdapter pa = new ProductAdapter();
    ListView lista;


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Meniu newInstance() {
        Meniu fragment = new Meniu();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 3);
        fragment.setArguments(args);
        return fragment;
    }

    public Meniu() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_oferte, container, false);
        mInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new LoadAllProducts().execute();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Incarcare meniu...\nVa rugam asteptati.");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_not, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                // products found
                // Getting Array of Products
                products = json.getJSONArray(TAG_TIP);

                // looping through All Products
                for (int i = 0; i < products.length(); i++) {
                    JSONObject c = products.getJSONObject(i);

                    // Storing each json item in variable
                    String id = c.getString(TAG_ID);
                    String nume = c.getString(TAG_NUME);
                    String link = c.getString(TAG_POZA);


                    // creating new HashMap
                    HashMap<String, Object> map = new HashMap<String, Object>();

                    // adding each child node to HashMap key => value
                    map.put(TAG_ID, id);
                    map.put(TAG_NUME, nume);
                    Bitmap bitmap;
                    try
                    {
                        URL url = new URL(link);
                        HttpGet httpRequest;
                        httpRequest = new HttpGet(url.toURI());
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpResponse response = httpclient.execute(httpRequest);
                        HttpEntity entity = response.getEntity();
                        BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
                        InputStream input = b_entity.getContent();
                        bitmap = BitmapFactory.decodeStream(input);
                    }
                    catch(Exception e)
                    {
                        bitmap=Bitmap.createBitmap(null);
                    }

                    map.put(TAG_POZA,bitmap);


                    // adding HashList to ArrayList
                    pa.productsList.add(map);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/

        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    // updating listview
                    setListAdapter(pa);
                }
            });

        }

    }

    public class ProductAdapter extends BaseAdapter {
        // Hashmap for ListView
        ArrayList<HashMap<String, Object>> productsList = new ArrayList<HashMap<String, Object>>();


        @Override public int getCount() { return productsList.size(); }

        @Override public Object getItem(int position) { return position; }

        @Override
        public boolean isEnabled(int position) {
            return true;
        }

        @Override public long getItemId(int position) { return Long.parseLong((String)productsList.get(position).get(TAG_ID)); }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            final int poz=position;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_tip, null);
                holder = new ViewHolder();
                holder.id = (TextView)convertView.findViewById(R.id.id_tip);
                holder.nume = (TextView)convertView.findViewById(R.id.nume);
                holder.img = (ImageView)convertView.findViewById(R.id.imagine);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.id.setText((String)productsList.get(position).get(TAG_ID));
            holder.nume.setText((String)productsList.get(position).get(TAG_NUME));
            holder.img.setImageBitmap((Bitmap)productsList.get(position).get(TAG_POZA));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tip_ales = pa.getItemId(poz);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, Produse.newInstance())
                            .addToBackStack(null)
                            .commit();
                }
            });
            return convertView;
        }

        private class ViewHolder {
            public TextView id, nume;
            public ImageView img;
        }
    }
}