package com.pizzatime.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Comanda extends ListFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    // JSON Node names
    private static final String TAG_OFERTA = "id_meniu";
    private static final String TAG_PRODUS = "id_prod";
    private static final String TAG_NUME = "nume";
    private static final String TAG_PRET = "pret";
    private static final String TAG_DESC = "descriere";
    private static final String TAG_POZA = "link_poza";
    private LayoutInflater mInflater;
    ProductAdapter2 pa = new ProductAdapter2();
    TextView pret;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Comanda newInstance() {
        Comanda fragment = new Comanda();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 4);
        fragment.setArguments(args);
        return fragment;
    }

    public Comanda() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_comanda, container, false);
        mInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setListAdapter(pa);
        Button btnComanda = (Button) rootView.findViewById(R.id.btnComanda);
        if(MainActivity.comanda.isEmpty())
        {
            btnComanda.setEnabled(false);
        }
        else {
            btnComanda.setEnabled(true);
            btnComanda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    Fragment fragment = Trimite.newInstance();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
        pret = (TextView) rootView.findViewById(R.id.pret);
        pret.setText("Pret = " + MainActivity.pret + " lei");
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    public class ProductAdapter2 extends BaseAdapter {

        @Override public int getCount() { return MainActivity.comanda.size(); }

        @Override public Object getItem(int position) { return position; }

        @Override
        public boolean isEnabled(int position) {
            return false;
        }

        @Override public long getItemId(int position) {
            final String[] mKeys;
            mKeys = MainActivity.comanda.keySet().toArray(new String[MainActivity.comanda.size()]);
            if(MainActivity.comanda.get(mKeys[position]).get("TIP").equals("OFERTA"))
                return Long.parseLong((String)MainActivity.comanda.get(mKeys[position]).get(TAG_OFERTA));
            else
                return Long.parseLong((String)MainActivity.comanda.get(mKeys[position]).get(TAG_PRODUS));
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            final String[] mKeys;
            mKeys = MainActivity.comanda.keySet().toArray(new String[MainActivity.comanda.size()]);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_comanda, null);
                holder = new ViewHolder();
                holder.nume = (TextView)convertView.findViewById(R.id.nume);
                holder.pret = (TextView)convertView.findViewById(R.id.pret);
                holder.desc = (TextView)convertView.findViewById(R.id.desc);
                holder.img = (ImageView)convertView.findViewById(R.id.imagine);
                holder.cant = (TextView) convertView.findViewById(R.id.cantitate);
                final int poz=position;
                ImageButton x=(ImageButton) convertView.findViewById(R.id.removeCart);
                x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(getActivity().getApplicationContext(),
                                MainActivity.comanda.get(mKeys[poz]).get(TAG_NUME) + " scos.",
                                Toast.LENGTH_SHORT).show();
                        MainActivity.pret -= (Integer) MainActivity.comanda.get(mKeys[poz]).get("CANTITATE") * Integer.parseInt((String)MainActivity.comanda.get(mKeys[poz]).get(TAG_PRET));
                        pret.setText("Pret = " + MainActivity.pret + " lei");
                        MainActivity.comanda.remove(mKeys[poz]);
                        setListAdapter(pa);
                    }
                });
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.nume.setText((String)MainActivity.comanda.get(mKeys[position]).get(TAG_NUME));
            holder.pret.setText(MainActivity.comanda.get(mKeys[position]).get(TAG_PRET)+ " lei");
            holder.desc.setText((String)MainActivity.comanda.get(mKeys[position]).get(TAG_DESC));
            holder.img.setImageBitmap((Bitmap)MainActivity.comanda.get(mKeys[position]).get(TAG_POZA));
            holder.cant.setText("Cantitate: " + MainActivity.comanda.get(mKeys[position]).get("CANTITATE"));
            return convertView;
        }

        private class ViewHolder {
            public TextView nume, pret, desc, cant;
            public ImageView img;
        }
    }
}