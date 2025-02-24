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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddBug extends Fragment {

    EditText etbug;
    Spinner spinner,spproject;
    Button btsubmit;

    public AddBug() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_bug, container, false);
        etbug=view.findViewById(R.id.etbug);
        spinner=view.findViewById(R.id.sppriority);
        spproject=view.findViewById(R.id.spproject);
        HashMap map1= Store.getUserDetails(getActivity());
        getProjects(Integer.parseInt(map1.get("userid").toString()));
        btsubmit=view.findViewById(R.id.btsubmit);
        String[] s ={"Low","medium","High"};
        ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,s);
        spinner.setAdapter(arrayAdapter);

        btsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bug=etbug.getText().toString();
                String priority=spinner.getSelectedItem().toString();
                String projectid=spproject.getSelectedItem().toString();
                HashMap map= Store.getUserDetails(getActivity());
                addBug(Integer.parseInt(projectid),bug,priority,Integer.parseInt(map.get("userid").toString()));
            }
        });

        return view;
    }

    private void getProjects(int userid) {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, Constants.getAssignedProjects+"?developerid="+userid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response",response);
                List<ProjectModel> projectModelList=new ArrayList<>();
                ArrayList projectlist=new ArrayList();
                if(response.isEmpty() || response==null){
                    ToastHelper.toastMsg(getActivity(),"no projects assigned");
                }else{
                    try {


                        JSONArray jsonArray=new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            ProjectModel pm=new ProjectModel();
                            pm.setId(jsonObject.getInt("id"));
                            pm.setProjectname(jsonObject.getString("projectname"));
                            pm.setProjectdesc(jsonObject.getString("projectdesc"));
                            pm.setStartdate(jsonObject.getString("startdate"));
                            pm.setEnddate(jsonObject.getString("enddate"));
                            projectlist.add(pm.getId());

                        }
ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,projectlist);
                        spproject.setAdapter(arrayAdapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastHelper.toastMsg(getActivity(),error.toString());
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);




    }

    private void addBug(final int projectid,final String bug, final String priority, final int userid) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.addBug, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
Log.e("response",response);
if(response.isEmpty() || response==null){
    ToastHelper.toastMsg(getActivity(),"Failed to Add?");
}else{
    ToastHelper.toastMsg(getActivity(),"Added successfully");
    etbug.setText("");
}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastHelper.toastMsg(getActivity(),error.toString());
            }
        }){
            @Override
           public  Map<String, String> getParams() throws AuthFailureError {
                HashMap map=new HashMap();
                map.put("project id",String.valueOf(projectid));
                map.put("buddesc",bug);
                map.put("priority",priority);
                map.put("developerid",String.valueOf(userid));
                return map;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

}
