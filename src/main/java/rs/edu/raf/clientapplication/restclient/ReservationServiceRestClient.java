package rs.edu.raf.clientapplication.restclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import okhttp3.*;
import rs.edu.raf.clientapplication.ClientApplication;
import rs.edu.raf.clientapplication.restclient.dto.*;

import java.io.IOException;
import java.time.LocalDate;

public class ReservationServiceRestClient {
    public static final String URL = "http://localhost:8084";

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public ReservationServiceRestClient() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
    }

    private String getUserNameById(Long userId) throws IOException {
        Request request = new Request.Builder()
                .url(URL + "/hotel-user-service/api/user/" + userId)
                .header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (!response.isSuccessful()) {
            throw new RuntimeException("Nije uspelo citanje korisnika");
        }
        String json = response.body().string();
        ClientDto userDto = objectMapper.readValue(json, ClientDto.class);
        return userDto.getFirstName() + " " + userDto.getLastName();
    }

    private LocalDate getDateByTerminId(Long terminId) throws IOException {
        Request request = new Request.Builder()
                .url(URL + "/hotel-reservation-service/api/termin/" + terminId)
                .header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (!response.isSuccessful()) {
            throw new RuntimeException("Nije uspelo citanje rezervacija");
        }
        String json = response.body().string();
        TerminDto terminDto = objectMapper.readValue(json, TerminDto.class);
        return terminDto.getDatum();
    }

    private String getTipSobeNameById(Long tipSobeId) throws IOException {
        Request requestTipSobe = new Request.Builder()
                .url(URL + "/hotel-reservation-service/api/tipsobe/" + tipSobeId)
                .get()
                .build();

        Call callTipSobe = client.newCall(requestTipSobe);
        Response responseTipSobe = callTipSobe.execute();
        if (responseTipSobe.isSuccessful()) {
            String json = responseTipSobe.body().string();
            return objectMapper.readValue(json, TipSobeDto.class).getIme();
        }
        throw new RuntimeException("Nijee uspelo citanje tipa sobe iz termina");
    }

    public ReservationListDto getReservations(Long userId) throws IOException {

        Request request = new Request.Builder()
                .url(URL + "/hotel-reservation-service/api/rezervacija/user/" + userId)
                .header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        if (!response.isSuccessful()) {
            throw new RuntimeException("Nije uspelo citanje rezervacija");
        }
        String json = response.body().string();
        ReservationListDto reservationListDto = objectMapper.readValue(json, ReservationListDto.class);
        for (ReservationDto reservationDto : reservationListDto.getContent()) {
            reservationDto.setTipSobe(getTipSobeNameById(reservationDto.getTipSobeId()));
            reservationDto.setUserName(getUserNameById(reservationDto.getUserId()));
            reservationDto.setPocetniTerminDatum(getDateByTerminId(reservationDto.getPocetniTerminId()));
            reservationDto.setKrajnjiTerminDatum(getDateByTerminId(reservationDto.getKrajnjiTerminId()));
        }
        return reservationListDto;
    }

    public ReservationDto createRezervacija(CreateRezervacijaDto createRezervacijaDto)  throws IOException{
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(createRezervacijaDto));
        Request request = new Request.Builder()
                .url(URL + "/hotel-reservation-service/api/rezervacija")
                .header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.isSuccessful()) {
            String json = response.body().string();

            return objectMapper.readValue(json, ReservationDto.class);
        }

        throw new RuntimeException("Nije uspelo kreiranje rezervacije od klijenta");
    }

    public ReservationDto deleteRezervacija(Long rezervacijaId) throws IOException {
        Request request = new Request.Builder()
                .url(URL + "/hotel-reservation-service/api/rezervacija/" + rezervacijaId)
                .header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
                .delete()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.isSuccessful()) {
            String json = response.body().string();

            return objectMapper.readValue(json, ReservationDto.class);
        }

        throw new RuntimeException("Nije uspelo brisanje rezervacije od klijenta");
    }
}
