package com.neko.androidexamples.common.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.neko.androidexamples.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    private static OnFragmentFirstCallback listener;
    private View vMain;


    public FirstFragment() {
        // Required empty public constructor
    }

    public void setListener(OnFragmentFirstCallback listener){
        FirstFragment.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vMain = inflater.inflate(R.layout.fragment_fisrt, container, false);

        // Say Hello button
        ((Button) vMain.findViewById(R.id.btnSayHello)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null)
                    listener.fragmentSayHello();
            }
        });

        return vMain;
    }

    public interface OnFragmentFirstCallback {
        void fragmentSayHello();
    }

}
