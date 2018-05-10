package com.mxi.cabdemo.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mxi.cabdemo.R;


public class DashboardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Button btnChangeRate;
    EditText edtRateMinimum,edtRateKm,edtRateHour,edtRateDay;


    public DashboardFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);


        //Edittext initialization
        edtRateMinimum = (EditText)view.findViewById(R.id.edt_rate_minimum);
        edtRateKm = (EditText)view.findViewById(R.id.edt_rate_km);
        edtRateHour = (EditText)view.findViewById(R.id.edt_rate_hour);
        edtRateDay = (EditText)view.findViewById(R.id.edt_rate_day);

        //Button initialization
        btnChangeRate = (Button)view.findViewById(R.id.btn_change_rate);
        btnChangeRate.setText("Edit");

        //Edittext not Focusable
        edtRateMinimum.setFocusable(false);
        edtRateKm.setFocusable(false);
        edtRateHour.setFocusable(false);
        edtRateDay.setFocusable(false);

        //Button clickevent
        btnChangeRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnChangeRate.setText("Update");

                edtRateMinimum.setFocusableInTouchMode(true);
                edtRateKm.setFocusableInTouchMode(true);
                edtRateHour.setFocusableInTouchMode(true);
                edtRateDay.setFocusableInTouchMode(true);

            }
        });

        return view;
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
