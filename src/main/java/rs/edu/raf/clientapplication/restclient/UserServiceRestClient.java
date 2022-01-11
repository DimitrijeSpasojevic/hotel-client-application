package rs.edu.raf.clientapplication.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import rs.edu.raf.clientapplication.ClientApplication;
import rs.edu.raf.clientapplication.restclient.dto.*;

import java.io.IOException;

public class UserServiceRestClient {

	public static final String URL = "http://localhost:8084/hotel-user-service/api";

	public static final MediaType JSON
		= MediaType.get("application/json; charset=utf-8");

	OkHttpClient client = new OkHttpClient();
	ObjectMapper objectMapper = new ObjectMapper();

	public String login(String email, String password) throws IOException {

		TokenRequestDto tokenRequestDto = new TokenRequestDto(email, password);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(tokenRequestDto));

		Request request = new Request.Builder()
			.url(URL + "/user/login")
			.post(body)
			.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.isSuccessful()) {
			String json = response.body().string();
			TokenResponseDto dto = objectMapper.readValue(json, TokenResponseDto.class);

			return dto.getToken();
		}

		throw new RuntimeException("Invalid email or password");
	}

	public ClientDto registerClient(CreateClientDto createClientDto) throws IOException {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(createClientDto));
		Request request = new Request.Builder()
				.url(URL + "/register/client")
				.post(body)
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.isSuccessful()) {
			String json = response.body().string();

			return objectMapper.readValue(json, ClientDto.class);
		}

		throw new RuntimeException("Nije uspelo registrovanje klijenata");
	}

	public ManagerDto registerManager(CreateManagerDto createManagerDto) throws IOException {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(createManagerDto));
		Request request = new Request.Builder()
				.url(URL + "/register/manager")
				.post(body)
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.isSuccessful()) {
			String json = response.body().string();

			return objectMapper.readValue(json, ManagerDto.class);
		}

		throw new RuntimeException("Nije uspelo registrovanje managera");
	}
}
