package rs.edu.raf.clientapplication.view;

import rs.edu.raf.clientapplication.model.HotelTableModel;
import rs.edu.raf.clientapplication.model.TerminTableModel;
import rs.edu.raf.clientapplication.restclient.HotelServiceRestClient;
import rs.edu.raf.clientapplication.restclient.TerminServiceRestClient;
import rs.edu.raf.clientapplication.restclient.dto.HotelListDto;
import rs.edu.raf.clientapplication.restclient.dto.TerminListDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class HotelsView extends JPanel {

    private HotelTableModel hotelTableModel;
    private JTable hotelTable;
    private HotelServiceRestClient hotelServiceRestClient;
    private JButton jButton;

    public HotelsView() throws IllegalAccessException, NoSuchMethodException {
        super();
        this.setSize(400, 400);

        hotelTableModel = new HotelTableModel();
        hotelServiceRestClient = new HotelServiceRestClient();
        hotelTable = new JTable(hotelTableModel);
        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(hotelTable);
        this.add(scrollPane, BorderLayout.NORTH);

        jButton = new JButton("Create Order");
        this.add(jButton, BorderLayout.CENTER);

        jButton.addActionListener((event) -> {
            System.out.println(hotelTableModel.getHotelListDto().getContent().get(hotelTable.getSelectedRow()).getId());
        });

        setVisible(false);
    }

    public void init() throws IOException {
        this.setVisible(true);
        HotelListDto hotelListDto = hotelServiceRestClient.getHotels();
        hotelListDto.getContent().forEach(hotelDto -> {
            System.out.println(hotelDto);
            hotelTableModel.addRow(new Object[]{hotelDto.getIme(), hotelDto.getOpis(),hotelDto.getGrad(),hotelDto.getId()});
        });

    }

}
