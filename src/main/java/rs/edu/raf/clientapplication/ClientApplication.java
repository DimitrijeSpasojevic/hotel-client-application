package rs.edu.raf.clientapplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import rs.edu.raf.clientapplication.restclient.dto.PayloadDto;
import rs.edu.raf.clientapplication.view.*;

import javax.swing.*;
import java.awt.*;
import java.util.Base64;

public class ClientApplication extends JFrame{
    private String token;
    private LoginView loginView;
    private TerminsView terminsView;
    private HotelChangeView hotelChangeView;
    private HotelsView hotelsView;
    private RegisterClientView registerClientView;
    private RegisterManagerView registerManagerView;
    private ReservationView reservationView;
    private UserChangeView userChangeView;

    private ClientApplication() throws IllegalAccessException, NoSuchMethodException {
        this.setTitle("Client Application");
        this.setSize(1200, 1200);
        this.setLayout(new BorderLayout());

        loginView = new LoginView();
        this.add(loginView, BorderLayout.NORTH);

        hotelChangeView = new HotelChangeView();
        this.add(hotelChangeView, BorderLayout.CENTER);

        terminsView = new TerminsView();
        this.add(terminsView, BorderLayout.CENTER);

        hotelsView = new HotelsView();
        this.add(hotelsView, BorderLayout.CENTER);
        
        registerClientView = new RegisterClientView();
        this.add(registerClientView, BorderLayout.CENTER);

        registerManagerView = new RegisterManagerView();
        this.add(registerManagerView, BorderLayout.CENTER);
        
        userChangeView = new UserChangeView();
        this.add(userChangeView, BorderLayout.CENTER);
        
        reservationView = new ReservationView();
        this.add(reservationView, BorderLayout.CENTER);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }

    public static PayloadDto getPayload() throws JsonProcessingException {
        String payload = ClientApplication.getInstance().token.split("\\.")[1];
        String payloadDecoded = decode(payload);
        PayloadDto payloadDto = new ObjectMapper().readValue(payloadDecoded, PayloadDto.class);
        return payloadDto;
    }

    private static class InstanceHolder {
        private static ClientApplication instance;

        static {
            try {
                instance = new ClientApplication();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public TerminsView getTerminsView() {
        return terminsView;
    }

    public void setTerminsView(TerminsView terminsView) {
        this.terminsView = terminsView;
    }

    public static ClientApplication getInstance() {
        return InstanceHolder.instance;
    }

    public HotelChangeView getHotelChangeView() {
        return hotelChangeView;
    }

    public void setHotelChangeView(HotelChangeView hotelChangeView) {
        this.hotelChangeView = hotelChangeView;
    }

    public HotelsView getHotelsView() {
        return hotelsView;
    }

    public void setHotelsView(HotelsView hotelsView) {
        this.hotelsView = hotelsView;
    }

    public RegisterClientView getRegisterClientView() {
        return registerClientView;
    }

    public void setRegisterClientView(RegisterClientView registerClientView) {
        this.registerClientView = registerClientView;
    }

    public RegisterManagerView getRegisterManagerView() {
        return registerManagerView;
    }

    public void setRegisterManagerView(RegisterManagerView registerManagerView) {
        this.registerManagerView = registerManagerView;
    }

    public ReservationView getReservationView() {
        return reservationView;
    }

    public void setReservationView(ReservationView reservationView) {
        this.reservationView = reservationView;
    }

    public UserChangeView getUserChangeView() {
        return userChangeView;
    }

    public void setUserChangeView(UserChangeView userChangeView) {
        this.userChangeView = userChangeView;
    }


}
