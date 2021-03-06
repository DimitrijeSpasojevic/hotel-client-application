package rs.edu.raf.clientapplication.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import rs.edu.raf.clientapplication.ClientApplication;
import rs.edu.raf.clientapplication.model.HotelTableModel;
import rs.edu.raf.clientapplication.restclient.HotelServiceRestClient;
import rs.edu.raf.clientapplication.restclient.dto.HotelListDto;
import rs.edu.raf.clientapplication.restclient.dto.PayloadDto;
import rs.edu.raf.clientapplication.restclient.dto.TokenResponseDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Base64;

public class HotelsView extends JPanel {

    private HotelTableModel hotelTableModel;
    private JTable hotelTable;
    private HotelServiceRestClient hotelServiceRestClient;
    private JButton jButtonShow;
    private JButton jButtonChangeHotel;
    private String role;

    public HotelsView() throws IllegalAccessException, NoSuchMethodException {
        super();
        this.setSize(1000, 1000);
        this.setVisible(false);

        hotelTableModel = new HotelTableModel();
        hotelServiceRestClient = new HotelServiceRestClient();
        hotelTable = new JTable(hotelTableModel);
        JScrollPane scrollPane = new JScrollPane(hotelTable);
        this.add(scrollPane);

        jButtonShow = new JButton("Show hotel");
        jButtonChangeHotel = new JButton("Change hotel");
        this.add(jButtonShow);
        this.add(jButtonChangeHotel);

        JButton backToHomeButton = new JButton("Back to home");
        backToHomeButton.addActionListener((event) -> {
            this.setVisible(false);
            ClientApplication.getInstance().getHomePageView().init();
        });
        this.add(backToHomeButton);

        jButtonShow.addActionListener((event) -> {
            this.setVisible(false);
            Long id = hotelTableModel.getHotelListDto().getContent().get(hotelTable.getSelectedRow()).getId();
            System.out.println(id);
            try {
                ClientApplication.getInstance().getTerminsView().init(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jButtonChangeHotel.addActionListener((event) -> {
            this.setVisible(false);
            Long id = hotelTableModel.getHotelListDto().getContent().get(hotelTable.getSelectedRow()).getId();
            System.out.println(id);
            ClientApplication.getInstance().getHotelChangeView().setHotelId(id);
            try {
                ClientApplication.getInstance().getHotelChangeView().init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setVisible(false);
    }

    public void init() throws IOException {
        PayloadDto payloadDto = ClientApplication.getPayload();
        role = payloadDto.getRole();

        if(role.equals("ROLE_CLIENT")){
            jButtonChangeHotel.setVisible(false);
        }
        this.setVisible(true);
        hotelTableModel.setRowCount(0);
        HotelListDto hotelListDto = hotelServiceRestClient.getHotels();
        hotelListDto.getContent().forEach(hotelDto -> {
            System.out.println(hotelDto);
            hotelTableModel.addRow(new Object[]{hotelDto.getIme(), hotelDto.getOpis(),hotelDto.getGrad(),hotelDto.getId()});
        });

    }
}
