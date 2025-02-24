package com.example.shyam.bugtrackingsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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
import com.example.shyam.bugtrackingsystem.helper.ToastHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shyam on 01-03-2018.
 */

public class AddProjects extends Activity {
    EditText  etprojectname,etprojectdesc,etstartdate,etenddate;
    Button btsubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_projects);
        etprojectname=findViewById(R.id.etprojectname);
        etprojectdesc=findViewById(R.id.etprojectdesc);
        etstartdate=findViewById(R.id.etstartdate);
        etenddate=findViewById(R.id.etenddate);
        btsubmit=findViewById(R.id.btsubmit);


        btsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String projectname=etprojectname.getText().toString();
                String projectdesc=etprojectdesc.getText().toString();
                String startdate=etstartdate.getText().toString();
                String enddate=etenddate.getText().toString();

                if(projectname.isEmpty() || projectname==null){
                    etprojectname.setError("Employee id required");
                }else if(projectdesc.isEmpty() || projectdesc==null){
                    etprojectdesc.setError("Employee name required");
                }
                else if(startdate.isEmpty() || startdate==null){
                    etstartdate.setError("mobile number required");
                }
                else if(enddate.isEmpty() || enddate==null){
                    etenddate.setError("Email  required");
                }

                else{
                    addProjects(projectname,projectdesc,startdate,enddate);
                }
            }
        });
    }

    private void addProjects(final String id, final String name, final String mobile, final String email) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.addProjects, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.isEmpty() || response==null){
                    ToastHelper.toastMsg(getApplicationContext(),"Failed to add Developer");
                }else{
                    ToastHelper.toastMsg(getApplicationContext(),"Added successfully");
                    Intent i=new Intent(getApplicationContext(),AdminSuccessLogin.class);
                    finish();
                    startActivity(i);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastHelper.toastMsg(getApplicationContext(),error.toString());
            }
        }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap map=new HashMap<>();
                map.put("projectname",id);
                map.put("projectdesc",name);
                map.put("startdate",mobile);
                map.put("enddate",email);

                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
