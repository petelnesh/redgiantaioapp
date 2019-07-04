package com.keredgiantaio.techsavanna.redgiantaio.helpers;

import com.keredgiantaio.techsavanna.redgiantaio.methods.Sweep;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterfaceSweep {

    @GET("getSweep.php")
    Call<List<Sweep>> getSweep(
            @Query("item_type") String item_type,
            @Query("type") String type
    );
}
