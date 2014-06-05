package com.pizzatime.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trimite extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText inputNume;
    EditText inputTel;
    EditText inputAdresa;
    EditText inputEmail;
    EditText inputObs;
    String id_client;
    String id_comanda;

    // url to create new product
    private static String url_client = "http://pizzatime.webege.com/add_client.php";
    private static String url_comanda = "http://pizzatime.webege.com/add_comanda.php";
    private static String url_oferta = "http://pizzatime.webege.com/add_oferta.php";
    private static String url_prod = "http://pizzatime.webege.com/add_prod.php";


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Trimite newInstance() {
        Trimite fragment = new Trimite();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 4);
        fragment.setArguments(args);
        return fragment;
    }

    public Trimite() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trimite, container, false);
        // Edit Text
        inputNume = (EditText) rootView.findViewById(R.id.inputNume);
        inputTel = (EditText) rootView.findViewById(R.id.inputTel);
        inputAdresa = (EditText) rootView.findViewById(R.id.inputAdresa);
        inputEmail = (EditText) rootView.findViewById(R.id.inputEmail);
        inputObs = (EditText) rootView.findViewById(R.id.inputObs);
        Button btnTrimite = (Button) rootView.findViewById(R.id.btnTrimite);
        btnTrimite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputNume.getText().toString().equals("") ||
                        inputTel.getText().toString().equals("") ||
                        inputAdresa.getText().toString().equals(""))
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Completati toate campurile obligatorii!",
                            Toast.LENGTH_LONG).show();
                else {
                    new CreateClient().execute();
                    new CreateComanda().execute();
                    new AddOferte().execute();
                }
            }
        });
        return rootView;
    }

    public void reset()
    {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, Home.newInstance())
                .commit();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
    /**
     * Background Async Task to Create/Get Client
     * */
    class CreateClient extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Se trimite comanda...\nVa rugam sa nu inchideti aplicatia!");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String nume = inputNume.getText().toString();
            String tel = inputTel.getText().toString();
            String adresa = inputAdresa.getText().toString();
            String email = inputEmail.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("nume", nume));
            params.add(new BasicNameValuePair("tel", tel));
            params.add(new BasicNameValuePair("adresa", adresa));
            params.add(new BasicNameValuePair("email", email));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_client,
                    "POST", params);

            try {
                id_client=json.getString("cid");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // check log cat fro response
            Log.d("Create Response", json.toString());
            return null;
        }



    }
    /**
     * Background Async Task to Create Comanda
     * */
    class CreateComanda extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {

            String obs = inputObs.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("pret", Integer.toString(MainActivity.pret)));
            params.add(new BasicNameValuePair("obs", obs));
            params.add(new BasicNameValuePair("id_client", id_client));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_comanda,
                    "POST", params);

            try {
                id_comanda=json.getString("cid");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // check log cat fro response
            Log.d("Create Response", json.toString());
            return null;
        }

    }

    /**
     * Background Async Task to Add Oferte
     * */
    class AddOferte extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * Creating oferte
         * */
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for(Map.Entry<String, HashMap<String, Object>> entry : MainActivity.comanda.entrySet()) {
                String id, url;
                if(entry.getValue().get("TIP").equals("OFERTA")) {
                    id = entry.getValue().get("id_meniu").toString();
                    params.add(new BasicNameValuePair("id_oferta", id));
                    url=url_oferta;
                }
                else {
                    id = entry.getValue().get("id_prod").toString();
                    params.add(new BasicNameValuePair("id_prod", id));
                    url=url_prod;
                }
                String cant=entry.getValue().get("CANTITATE").toString();
                // Building Parameters
                params.add(new BasicNameValuePair("id_comanda", id_comanda));
                params.add(new BasicNameValuePair("cantitate", cant));

                // getting JSON Object
                // Note that create product url accepts POST method
                JSONObject json = jsonParser.makeHttpRequest(url,
                        "POST", params);

                // check log cat fro response
                Log.d("Create Response", json.toString());
                pDialog.dismiss();
            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            MainActivity.pret=0;
            MainActivity.comanda.clear();
            reset();
        }

    }
}