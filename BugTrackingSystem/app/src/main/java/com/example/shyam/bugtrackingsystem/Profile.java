package com.example.shyam.bugtrackingsystem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shyam.bugtrackingsystem.helper.Constants;
import com.example.shyam.bugtrackingsystem.helper.Store;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {

EditText etusername,etmobile,etemail,etaddress,etadhar;

    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view=inflater.inflate(R.layout.fragment_profile, container, false);
         etusername=view.findViewById(R.id.etusername);
       etmobile=view.findViewById(R.id.etmobile);
       etemail=view.findViewById(R.id.etemail);
       etaddress=view.findViewById(R.id.etaddress);
       etadhar=view.findViewById(R.id.etadhar);
        etusername.setKeyListener(null);
        etmobile.setKeyListener(null);
        etemail.setKeyListener(null);
        etadhar.setKeyListener(null);
        etaddress.setKeyListener(null);

        HashMap map= Store.getUserDetails(getActivity());
      getProfile(Integer.parseInt(map.get("userid").toString()));
      return view;
    }

    private void getProfile(int userid) {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, Constants.getProfile+"?userid="+userid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response",response);
                try {
                    JSONObject jsonObject=new JSONObject(response);

                    etusername.setText(jsonObject.getString("name"));
                    etmobile.setText(jsonObject.getString("mobile"));
                    etemail.setText(jsonObject.getString("email"));
                    etaddress.setText(jsonObject.getString("empid"));
                    etadhar.setText(jsonObject.getString("role"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
