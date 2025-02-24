package com.example.shyam.bugtrackingsystem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shyam.bugtrackingsystem.helper.Constants;
import com.example.shyam.bugtrackingsystem.helper.Store;
import com.example.shyam.bugtrackingsystem.helper.ToastHelper;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeveloperComplaints extends Fragment {


    Spinner spinner;
    EditText etcompldesc;
    Button btsubmit;


    public DeveloperComplaints() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_developer_complaints, container, false);
        spinner=view.findViewById(R.id.compltype);
        etcompldesc=view.findViewById(R.id.compldesc);
        btsubmit=view.findViewById(R.id.btsubmit);
        String[] s ={"HardWare","Software"};
        ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,s);
        spinner.setAdapter(arrayAdapter);

btsubmit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        HashMap map= Store.getUserDetails(getActivity());
        addComplaint(map.get("empid").toString(),spinner.getSelectedItem().toString(),etcompldesc.getText().toString());
    }
});
        return view;
    }

    private void addComplaint(final String developerid, final String compltype, final String compldesc) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.developerCompl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
   Log.e("response",response);
   if(response.isEmpty() || response==null){
       ToastHelper.toastMsg(getActivity(),"Failed to add");
   }else{
       etcompldesc.setText("");
       ToastHelper.toastMsg(getActivity(),"Complaint added successfully");
   }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastHelper.toastMsg(getActivity(),error.toString());
            }
        }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap map=new HashMap();
                map.put("developerid",developerid);
                map.put("comptype",compltype);
                map.put("compdesc",compldesc);
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
