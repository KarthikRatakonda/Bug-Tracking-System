package com.example.shyam.bugtrackingsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.shyam.bugtrackingsystem.helper.Store;

/**
 * Created by shyam on 01-03-2018.
 */

public class AdminSuccessLogin extends AppCompatActivity implements View.OnClickListener {
    ImageView  addDeveloper,addProjects,assignProjects,viewCompl;
    ImageView viewbugs,viewdevelopers;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_success);
        addDeveloper=findViewById(R.id.addDeveloper);
        addProjects=findViewById(R.id.addProjects);
        assignProjects=findViewById(R.id.assignProjects);
        viewCompl=findViewById(R.id.viewCompl);
        viewbugs=findViewById(R.id.viewBugs);
        viewdevelopers=findViewById(R.id.viewDevelopers);
        viewbugs.setOnClickListener(this);
        viewdevelopers.setOnClickListener(this);
        addDeveloper.setOnClickListener(this);
        addProjects.setOnClickListener(this);
        assignProjects.setOnClickListener(this);
        viewCompl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addDeveloper:{
                Intent i=new Intent(this,AddDeveloper.class);
                startActivity(i);
                break;
            }
            case R.id.addProjects:{
                Intent i=new Intent(this,AddProjects.class);
                startActivity(i);

                break;
            }
            case R.id.assignProjects:{
                Intent i=new Intent(this,AssignProjects.class);
                startActivity(i);
                break;
            }
            case R.id.viewCompl:{
                Intent i=new Intent(this,ViewComplaints.class);
                startActivity(i);
                break;
            }
            case R.id.viewBugs:{
                Intent i=new Intent(this,ViewBugs.class);
                startActivity(i);
                break;
            }
            case R.id.viewDevelopers:{
                Intent i=new Intent(this,ViewDevelopers.class);
                startActivity(i);
                break;
            }



        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if (id == R.id.logout) {
            Store.logout(getApplicationContext());
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
