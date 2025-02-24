package com.example.shyam.bugtrackingsystem;

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

class ComplaintAdapter  extends BaseAdapter{
    Context context;
    List<ComplaintModel> complaintModels;
    public ComplaintAdapter(Context context, List<ComplaintModel> projectModelList) {
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
        ComplaintModel cm=complaintModels.get(i);
        view= LayoutInflater.from(context).inflate(R.layout.row1,null);
        TextView tvcompid=view.findViewById(R.id.compid);
        TextView tvcomptype=view.findViewById(R.id.comptype);
        TextView tvcompdes=view.findViewById(R.id.compdesc);
        TextView tvstatus=view.findViewById(R.id.status);
        Button btsubmit=view.findViewById(R.id.btsubmit);

        tvcompid.setText(String.valueOf(cm.getId()));
        tvcomptype.setText(cm.getCompltype());
        tvcompdes.setText(cm.getCompldesc());
        tvstatus.setText(cm.getStatus());
        btsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        return view;
    }
}
