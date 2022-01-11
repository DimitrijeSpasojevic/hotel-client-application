package rs.edu.raf.clientapplication.restclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import rs.edu.raf.clientapplication.ClientApplication;
import rs.edu.raf.clientapplication.restclient.dto.HotelDto;
import rs.edu.raf.clientapplication.restclient.dto.TerminDto;
import rs.edu.raf.clientapplication.restclient.dto.TerminListDto;
import rs.edu.raf.clientapplication.restclient.dto.TipSobeDto;

import java.io.IOException;

public class TerminServiceRestClient {

	public static final String URL = "http://localhost:8084/";

	public static final MediaType JSON
		= MediaType.get("application/json; charset=utf-8");

	OkHttpClient client = new OkHttpClient();
	ObjectMapper objectMapper = new ObjectMapper();

	public TerminListDto getTermins(Long hotelId) throws IOException {

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
			.url(URL + "/hotel-reservation-service/api/termin/hotel/" + hotelId)
			.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
			.get()
			.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.isSuccessful()) {
			String json = response.body().string();

			TerminListDto termins = objectMapper.readValue(json, TerminListDto.class);
			for (TerminDto terminDto : termins.getContent()) {
				TipSobeDto tipSobeDto = getTipSobeById(terminDto.getTipSobeId());
				terminDto.setHotel(getHotelNameById(tipSobeDto.getHotelId()));
				terminDto.setTipSobe(tipSobeDto.getIme());
			}
		}

		throw new RuntimeException("Ne uspelo citanje termina");
	}

	private String getHotelNameById(Long hotelId) throws IOException {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
				.url(URL + "/hotel-reservation-service/api/hotel" + hotelId)
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.isSuccessful()) {
			String json = response.body().string();

			return objectMapper.readValue(json, HotelDto.class).getIme();
		}
		throw new RuntimeException("Ne uspelo citanje imena hotela");
	}

	private TipSobeDto getTipSobeById(Long tipSobeId) throws IOException {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request requestTipSobe = new Request.Builder()
				.url(URL + "/hotel-reservation-service/api/tipsobe/" + tipSobeId)
				.get()
				.build();

		Call callTipSobe = client.newCall(requestTipSobe);

		Response responseTipSobe = callTipSobe.execute();

		if (responseTipSobe.isSuccessful()) {
			String json = responseTipSobe.body().string();

			return objectMapper.readValue(json, TipSobeDto.class);
		}
		throw new RuntimeException("Ne uspelo citanje tipa sobe iz termina");
	}
}

