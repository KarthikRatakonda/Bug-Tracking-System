package com.example.shyam.bugtrackingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shyam on 20-03-2018.
 */

class AdminBugAdapter extends BaseAdapter {
    Context context;
    List<BugModel> bugModels;
    public AdminBugAdapter(Context context, List<BugModel> bugModelList) {
        this.context=context;
        this.bugModels=bugModelList;
    }

    @Override
    public int getCount() {
        return bugModels.size();
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
        BugModel bugModel=bugModels.get(i);
        view= LayoutInflater.from(context).inflate(R.layout.row5,null);
        TextView tvprojectid=view.findViewById(R.id.projectid);
        TextView tvprojectname=view.findViewById(R.id.projectname);
        TextView tvprojectdesc=view.findViewById(R.id.projectdesc);
        TextView tvstart=view.findViewById(R.id.startdate);
        tvprojectid.setText(String.valueOf(bugModel.getProjectid()));
        tvprojectname.setText(String.valueOf(bugModel.getId()));
        tvprojectdesc.setText(bugModel.getBugdesc());
        tvstart.setText(bugModel.getBugpriority());

        return view;
    }
}
