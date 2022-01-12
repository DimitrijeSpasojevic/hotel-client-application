package rs.edu.raf.clientapplication.view;

import rs.edu.raf.clientapplication.ClientApplication;
import rs.edu.raf.clientapplication.model.HotelTableModel;
import rs.edu.raf.clientapplication.restclient.HotelServiceRestClient;
import rs.edu.raf.clientapplication.restclient.dto.HotelListDto;
import rs.edu.raf.clientapplication.restclient.dto.PayloadDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class HomePageView extends JPanel {
    private JButton btnHotel;
    private JButton btnUserChange;
    private JButton btnReservation;

    public HomePageView() {
        super();
        this.setSize(1000, 1000);
        this.setVisible(false);
        this.setLayout(new GridLayout(0,1));

        btnHotel = new JButton("Show Hotels");
        btnReservation = new JButton("Show Reservations");
        btnUserChange = new JButton("Edit Profile");
        this.add(btnHotel);
        this.add(btnReservation);
        this.add(btnUserChange);

        btnHotel.addActionListener((event) -> {
            this.setVisible(false);

            try {
                ClientApplication.getInstance().getHotelsView().init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnReservation.addActionListener((event) -> {
            this.setVisible(false);

            try {
                ClientApplication.getInstance().getReservationView().init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnUserChange.addActionListener((event) -> {
            this.setVisible(false);

            try {
                ClientApplication.getInstance().getUserChangeView().init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setVisible(false);
    }

    public void init() {
        this.setVisible(true);
    }
}
