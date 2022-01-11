package rs.edu.raf.clientapplication.restclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import rs.edu.raf.clientapplication.ClientApplication;
import rs.edu.raf.clientapplication.restclient.dto.*;

import java.io.IOException;

public class HotelServiceRestClient {


    public static final String URL = "http://localhost:8084/hotel-reservation-service/api";

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public HotelListDto getHotels() throws IOException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Request request = new Request.Builder()
                .url(URL + "/hotel")
                .header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.isSuccessful()) {
            String json = response.body().string();

            return objectMapper.readValue(json, HotelListDto.class);
        }

        throw new RuntimeException("Ne uspelo citanje svih hotela");
    }

    public HotelDto changeHotel(HotelDto hotelDto) throws IOException  {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(hotelDto));
        Request request = new Request.Builder()
                .url(URL + "/hotel")
                .header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
                .put(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.isSuccessful()) {
            String json = response.body().string();

            return objectMapper.readValue(json, HotelDto.class);
        }

        throw new RuntimeException("Ne uspela promena polja za hotel");
    }
}
