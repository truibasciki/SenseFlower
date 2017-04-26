package com.example.logonrm.senseflower.endpoints;

import com.example.logonrm.senseflower.model.Temperatura;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by asus on 01/04/2017.
 */

public interface TemperaturaService {

    @GET("/temperatura")
    Call<Temperatura> getTemperatura();

}
