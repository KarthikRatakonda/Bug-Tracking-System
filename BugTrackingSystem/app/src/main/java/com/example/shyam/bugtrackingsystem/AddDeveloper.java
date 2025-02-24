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

public class AddDeveloper extends Activity {
    EditText  empid,empname,empmobile,empemail;
    Button btsubmit;
    Spinner spinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddeveloper);
        empid=findViewById(R.id.etempid);
        empname=findViewById(R.id.empname);
        empmobile=findViewById(R.id.empmobile);
        empemail=findViewById(R.id.empemail);
        btsubmit=findViewById(R.id.btsubmit);
        spinner=findViewById(R.id.sprole);
        String[] s ={"--select--","developer","tester"};
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,s);
        spinner.setAdapter(arrayAdapter);
        btsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=empid.getText().toString();
                String name=empid.getText().toString();
                String mobile=empmobile.getText().toString();
                String email=empemail.getText().toString();
                String role=spinner.getSelectedItem().toString();
                if(id.isEmpty() || id==null){
                    empid.setError("Employee id required");
                }else if(name.isEmpty() || name==null){
                    empname.setError("Employee name required");
                }
                else if(mobile.isEmpty() || mobile==null){
                    empmobile.setError("mobile number required");
                }
                else if(email.isEmpty() || email==null){
                    empemail.setError("Email  required");
                } else if(role.equalsIgnoreCase("--select--") ){
                   ToastHelper.toastMsg(getApplicationContext(),"select role");
                }

                else{
                    addDeveloper(id,name,mobile,email,role);
                }
            }
        });
    }

    private void addDeveloper(final String id, final String name, final String mobile, final String email,final String role) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.addDeveloper, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
   if(response.isEmpty() || response==null){
       ToastHelper.toastMsg(getApplicationContext(),"Failed to add Developer");
   }else if(response.equalsIgnoreCase("found")){
       ToastHelper.toastMsg(getApplicationContext(),"Employee id alredy Present");
   } else{
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
                map.put("empid",id);
                map.put("name",name);
                map.put("mobile",mobile);
                map.put("email",email);
                map.put("role",role);
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
