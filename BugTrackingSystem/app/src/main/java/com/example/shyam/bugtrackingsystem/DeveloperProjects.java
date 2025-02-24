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
import com.example.shyam.bugtrackingsystem.helper.ToastHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeveloperProjects extends Fragment {
      ListView lvlist;
      Context context;

    public DeveloperProjects() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_developer_projects, container, false);
        lvlist=view.findViewById(R.id.lvlist);
        context=getActivity();
        HashMap map= Store.getUserDetails(getActivity());
        getProjects(Integer.parseInt(map.get("userid").toString()));

        return  view;
    }

    private void getProjects(int userid) {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, Constants.getAssignedProjects+"?developerid="+userid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response",response);
                List<ProjectModel> projectModelList=new ArrayList<>();
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
                            projectModelList.add(pm);

                        }

                        addToList(projectModelList);

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

    private void addToList(List<ProjectModel> projectModelList) {

        DeveloperProjectsAdapter projectsAdapter=new DeveloperProjectsAdapter(context,projectModelList);
        lvlist.setAdapter(projectsAdapter);
    }
    }


