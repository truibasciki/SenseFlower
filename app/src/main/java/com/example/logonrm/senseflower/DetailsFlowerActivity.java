package com.example.logonrm.senseflower;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.logonrm.senseflower.dao.FlowerDAO;
import com.example.logonrm.senseflower.endpoints.TemperaturaService;
import com.example.logonrm.senseflower.model.Flower;
import com.example.logonrm.senseflower.model.Temperatura;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsFlowerActivity extends AppCompatActivity {

    private String temperatura;
    private FlowerDAO flowerDAO;
    private Spinner spnTipo;
    private TextView txtTemperatura;
    private Flower flower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_flower);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle param = getIntent().getExtras();
        int id = param.getInt("id");

        spnTipo = (Spinner) findViewById(R.id.spn_Detail_Flores);
        txtTemperatura = (TextView) findViewById(R.id.txtValorTemperatura);

        flowerDAO = new FlowerDAO(this);
        flower = flowerDAO.selectById(id);

        spnTipo.setSelection((int)flower.getTipo());

        getTemperatura();
    }

    public void getTemperatura() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.59:3000/").addConverterFactory(GsonConverterFactory.create()).build();
        TemperaturaService service = retrofit.create(TemperaturaService.class);

        Call<Temperatura> call = service.getTemperatura();
        call.enqueue(new Callback<Temperatura>() {
            @Override
            public void onResponse(Call<Temperatura> call, Response<Temperatura> response) {
                txtTemperatura.setText(response.body().getTemperatura());
                Log.i("CallBack - ", response.body().toString());
            }

            @Override
            public void onFailure(Call<Temperatura> call, Throwable t) {
                Log.i("CallBack - ", t.getMessage());

            }
        });

    }
}
