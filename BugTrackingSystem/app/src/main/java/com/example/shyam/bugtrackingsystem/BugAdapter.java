package com.example.shyam.bugtrackingsystem;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by shyam on 17-03-2018.
 */

class BugAdapter extends BaseAdapter {
    Context context;
    List<BugModel> Bugmodels;
    public BugAdapter(Context context, List<BugModel> projectModelList) {
        this.context=context;
        this.Bugmodels=projectModelList;
    }

    @Override
    public int getCount() {
        return Bugmodels.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final BugModel pm=Bugmodels.get(i);
        view= LayoutInflater.from(context).inflate(R.layout.row3,null);
        TextView tvprojectid=view.findViewById(R.id.projectid);
        TextView tvprojectname=view.findViewById(R.id.projectname);
        TextView tvprojectdesc=view.findViewById(R.id.projectdesc);
        Button btsubmit=view.findViewById(R.id.btsubmit);
        tvprojectid.setText(String.valueOf(pm.getId()));
        tvprojectname.setText(pm.bugdesc);
        tvprojectdesc.setText(pm.bugpriority);

        btsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignProjectdialog(pm);
            }
        });


        return view;
    }


    private void assignProjectdialog(final BugModel pm) {
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom);
        dialog.setTitle("Title...");
        final Spinner spinner=dialog.findViewById(R.id.spemps);

        StringRequest stringRequest=new StringRequest(Request.Method.GET, Constants.getDeveloper+"?developer=developer", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response",response);
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    ArrayList al=new ArrayList<>();
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        al.add(jsonObject.getString("empid"));
                    }
                    ArrayAdapter arrayAdapter=new ArrayAdapter(context,android.R.layout.simple_spinner_dropdown_item,al);
                    spinner.setAdapter(arrayAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastHelper.toastMsg(context,error.toString());
            }
        });
        final RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);


        Button dialogButton = (Button) dialog.findViewById(R.id.btok);
        Button dialogcancel=dialog.findViewById(R.id.btcancel);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest1=new StringRequest(Request.Method.POST, Constants.assignProjects, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.isEmpty() || response==null){
                            ToastHelper.toastMsg(context,"Error occured");
                        }else{
                            ToastHelper.toastMsg(context,"Project assigned successfully");
                            dialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap map=new HashMap<>();
                        map.put("projectid",String.valueOf(pm.getId()));
                        map.put("developerid",spinner.getSelectedItem().toString());
                        return map;
                    }
                };

                RequestQueue requestQueue1=Volley.newRequestQueue(context);
                requestQueue1.add(stringRequest1);
            }
        });

        dialogcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
