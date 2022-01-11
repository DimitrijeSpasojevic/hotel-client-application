package rs.edu.raf.clientapplication.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import rs.edu.raf.clientapplication.ClientApplication;
import rs.edu.raf.clientapplication.restclient.HotelServiceRestClient;
import rs.edu.raf.clientapplication.restclient.dto.HotelDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class HotelChangeView extends JPanel{

    private JPanel inputPanel;
    private JTextField nameInput;
    private JTextField descriptionInput;
    private JTextField cityInput;
    private Long hotelId;
    private JButton changeHotelBtn;

    private HotelServiceRestClient hotelServiceRestClient = new HotelServiceRestClient();

    private ObjectMapper objectMapper = new ObjectMapper();

    public HotelChangeView() {

        super();
        this.setSize(1000, 1000);

        this.setLayout(new BorderLayout());

        initInputPanel();

        changeHotelBtn = new JButton("Submit");
        this.add(changeHotelBtn, BorderLayout.SOUTH);
        changeHotelBtn.addActionListener((event) -> {
            try {
                HotelDto hotelDto = new HotelDto();
                hotelDto.setIme(nameInput.getText());
                hotelDto.setOpis(descriptionInput.getText());
                hotelDto.setGrad(cityInput.getText());
                hotelDto.setId(hotelId);
                HotelDto hotelDtoUpdated = hotelServiceRestClient.changeHotel(hotelDto);
                this.setVisible(false);
                ClientApplication.getInstance().getHotelsView().init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        this.setVisible(false);
    }

    private void initInputPanel() {
        inputPanel = new JPanel();

        nameInput = new JTextField(20);
        descriptionInput = new JTextField(2);
        cityInput = new JTextField(2);

        inputPanel.add(new JLabel("Name: "));
        inputPanel.add(nameInput);

        inputPanel.add(new JLabel("City: "));
        inputPanel.add(cityInput);

        inputPanel.add(new JLabel("Description"));
        inputPanel.add(descriptionInput);

        this.add(inputPanel, BorderLayout.CENTER);
    }

    public void init(){
        this.setVisible(true);
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
}
