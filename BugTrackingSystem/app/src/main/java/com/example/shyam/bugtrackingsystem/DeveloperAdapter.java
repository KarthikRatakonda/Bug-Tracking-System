package com.example.shyam.bugtrackingsystem;

import android.content.Context;

import java.util.List;

/**
 * Created by shyam on 17-03-2018.
 */



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by shyam on 01-03-2018.
 */

class DeveloperAdapter  extends BaseAdapter{
    Context context;
    List<DeveloperModel> complaintModels;
    public DeveloperAdapter(Context context, List<DeveloperModel> projectModelList) {
        this.context=context;
        this.complaintModels=projectModelList;
    }

    @Override
    public int getCount() {
        return complaintModels.size();
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
        DeveloperModel cm=complaintModels.get(i);
        view= LayoutInflater.from(context).inflate(R.layout.row4,null);
        TextView tvcompid=view.findViewById(R.id.compid);
        TextView tvcomptype=view.findViewById(R.id.comptype);
        TextView tvcompdes=view.findViewById(R.id.compdesc);
        TextView tvstatus=view.findViewById(R.id.status);
        TextView tvmobile=view.findViewById(R.id.mobile);


        tvcompid.setText(cm.getEmail());
        tvcomptype.setText(cm.getEmpid());
        tvcompdes.setText(cm.getName());
        tvstatus.setText(cm.getRole());
        tvmobile.setText(cm.getMobile());




        return view;
    }
}

