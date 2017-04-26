package com.example.logonrm.senseflower;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;

import com.example.logonrm.senseflower.dao.FlowerDAO;
import com.example.logonrm.senseflower.model.Flower;

public class AddFlowerActivity extends AppCompatActivity {

    private FlowerDAO flowerDAO;
    private Spinner spnTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flower);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spnTipo = (Spinner) findViewById(R.id.spn_Add_Flores);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_Save_Flower);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Flower flower = new Flower();
                flower.setId(1);
                flower.setTipo(spnTipo.getSelectedItemId());
                flowerDAO = new FlowerDAO(AddFlowerActivity.this);
                flowerDAO.insert(flower);


                finish();

            }
        });
    }

}
