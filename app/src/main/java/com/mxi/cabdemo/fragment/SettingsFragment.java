package com.mxi.cabdemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.mxi.cabdemo.CommanClass;
import com.mxi.cabdemo.R;

import java.util.Locale;


public class SettingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Switch gps;
    //GPS Enable
    Context con;
    LocationManager lm;
    boolean gpsStatus;

    Spinner languageChange;
    private static Locale myLocale;

    //Shared Preferences Variables
    private static final String Locale_Preference = "Locale Preference";
    private static final String Locale_KeyValue = "Saved Locale";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    TextView txtGps;

    CommanClass cc;

    public SettingsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        cc = new CommanClass(getActivity());

        /*sharedPreferences =getActivity().getSharedPreferences(Locale_Preference, Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();*/


        txtGps = (TextView)view.findViewById(R.id.txtGps);

        languageChange = (Spinner)view.findViewById(R.id.sp_language);

        con = getActivity().getApplicationContext();

        CheckGpsStatus();

        gps = (Switch)view.findViewById(R.id.sw_gps);

        gps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {


                    gps.setText("On   ");
                    gps.setTextColor(Color.parseColor("#317f2c"));

                    Intent intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent1);

                    Log.d("You are :", "Checked");
                }
                else {

                    gps.setText("Off   ");
                    gps.setTextColor(Color.parseColor("#ff0000"));

                    Log.d("You are :", " Not Checked");
                }

            }
        });

        // language spinner adapter
        ArrayAdapter state_adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.languagechange, android.R.layout.simple_spinner_item);
        state_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageChange.setAdapter(state_adapter);


        languageChange.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String lang = "en";//Default Language
                switch (position) {
                    case 0:
                        lang = "en";
                        break;
                    case 1:
                        lang = "hi";
                        break;
                    case 2:
                        lang = "gu";

                        break;
                }

                changeLocale(lang);//Change Locale on selection basis

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loadLocale();

        return view;
    }

    private void changeLocale(String lang) {

        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);//Set Selected Locale
        saveLocale(lang);//Save the selected locale
        Locale.setDefault(myLocale);//set new locale as default
        Configuration config = new Configuration();//get Configuration
        config.locale = myLocale;//set config locale as selected locale
        getActivity().getBaseContext().getResources().updateConfiguration(config,getActivity().getBaseContext().getResources().getDisplayMetrics());//Update the config
        updateTexts();//Update texts according to locale

    }

    private void updateTexts() {

        txtGps.setText(R.string.gps_enable);
    }

    //Save locale method in preferences
    public void saveLocale(String lang) {

        cc.savePrefString("Saved Locale",lang);

       /* editor.putString(Locale_KeyValue, lang);
        editor.commit();*/
    }
    //Get locale method in preferences
    public void loadLocale() {
//        String language = sharedPreferences.getString(Locale_KeyValue, "");

        String language = cc.loadPrefString("Saved Locale");
        changeLocale(language);
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }


    //GPS Enable method
    public void CheckGpsStatus(){

        lm = (LocationManager)con.getSystemService(Context.LOCATION_SERVICE);

        gpsStatus = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(gpsStatus == true)
        {

            Log.i("Gps","........Enable");

        }else {

            Log.i("Gps","........Disable");
        }

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
