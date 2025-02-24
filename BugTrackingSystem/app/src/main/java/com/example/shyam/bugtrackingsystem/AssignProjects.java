package com.example.shyam.bugtrackingsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shyam.bugtrackingsystem.helper.Constants;
import com.example.shyam.bugtrackingsystem.helper.ToastHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shyam on 01-03-2018.
 */

public class AssignProjects extends Activity {

    ListView lvlist;
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_projects);
        lvlist=findViewById(R.id.lvlist);
        context=this;
        getProjects();
    }

    private void getProjects() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, Constants.getProjects, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               Log.e("response",response);
               List<ProjectModel> projectModelList=new ArrayList<>();
               if(response.isEmpty() || response==null){
                   ToastHelper.toastMsg(getApplicationContext(),"no projects added");
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
                ToastHelper.toastMsg(getApplicationContext(),error.toString());
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void addToList(List<ProjectModel> projectModelList) {

        ProjectsAdapter projectsAdapter=new ProjectsAdapter(context,projectModelList);
        lvlist.setAdapter(projectsAdapter);
    }
}

