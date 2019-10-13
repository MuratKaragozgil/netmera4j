package com.github.muratkaragozgil.netmera4j.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;
import java.util.Map;

/**
 * @author Murat Karagözgil
 */
public interface EventService {

    @POST("/rest/3.0/fireEvents")
    Call<Void> fireEvent(@Body List<Map<String, Object>> singleEventList);
}
