package com.example.shyam.bugtrackingsystem;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shyam.bugtrackingsystem.helper.Constants;
import com.example.shyam.bugtrackingsystem.helper.Store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewAdminBugs extends Fragment {

ListView lvlist;
Context context;
    public ViewAdminBugs() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_view_admin_bugs, container, false);
        lvlist=view.findViewById(R.id.lvlist);
        context=getActivity();
        HashMap map= Store.getUserDetails(getActivity());
        getadminbugs(map.get("userid").toString());
        return  view;
    }

    public void getadminbugs(String userid) {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, Constants.viewadminbugs+"?userid="+userid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("reponse",response);
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    List<BugModel> bugModelList=new ArrayList();
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        BugModel bugModel=new BugModel();
                        bugModel.setProjectid("id");
                        JSONArray jsonArray1=jsonObject.getJSONArray("bugModel");
                        for(int j=0;j<jsonArray1.length();j++){
                            JSONObject jsonObject1=jsonArray1.getJSONObject(j);
                            bugModel.setId(jsonObject1.getInt("id"));
                            bugModel.setBugdesc(jsonObject1.getString("bugdesc"));
                            bugModel.setBugpriority(jsonObject1.getString("bugpriority"));

                        }
                      bugModelList.add(bugModel);

                    }

                    addToList(bugModelList);
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

    private void addToList(List<BugModel> bugModelList) {
        AdminBugAdapter adminBugAdapter=new AdminBugAdapter(context,bugModelList);
        lvlist.setAdapter(adminBugAdapter);
    }

}
