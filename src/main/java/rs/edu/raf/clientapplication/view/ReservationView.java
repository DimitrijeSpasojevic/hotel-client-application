package rs.edu.raf.clientapplication.view;

import rs.edu.raf.clientapplication.ClientApplication;
import rs.edu.raf.clientapplication.restclient.dto.PayloadDto;
import rs.edu.raf.clientapplication.restclient.dto.ReservationListDto;
import rs.edu.raf.clientapplication.model.ReservationTableModel;
import rs.edu.raf.clientapplication.restclient.ReservationServiceRestClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class ReservationView extends JPanel {
    private ReservationTableModel reservationTableModel;
    private JTable reservationTable;
    private ReservationServiceRestClient reservationServiceRestClient;
    private JButton jButtonDelete;

    public ReservationView() throws IllegalAccessException, NoSuchMethodException {
        super();
        this.setSize(1000, 1000);

        reservationTableModel = new ReservationTableModel();
        reservationServiceRestClient = new ReservationServiceRestClient();
        reservationTable = new JTable(reservationTableModel);
        
        JScrollPane scrollPane = new JScrollPane(reservationTable);
        this.add(scrollPane);
        jButtonDelete = new JButton("Delete reservation");
        this.add(jButtonDelete);

        JButton backToHomeButton = new JButton("Back to home");
        backToHomeButton.addActionListener((event) -> {
            this.setVisible(false);
            ClientApplication.getInstance().getHomePageView().init();
        });
        this.add(backToHomeButton);

        jButtonDelete.addActionListener((event) -> {
            Long id = reservationTableModel.getReservationListDto().getContent().get(reservationTable.getSelectedRow()).getId();
            System.out.println(id);
            try {
                reservationServiceRestClient.deleteRezervacija(id);
                init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setVisible(false);
    }

    public void init() throws IOException {
        this.setVisible(true);
        reservationTableModel.setRowCount(0);
        PayloadDto payloadDto = ClientApplication.getPayload();
        Long userId = payloadDto.getId();
        ReservationListDto reservationListDto = reservationServiceRestClient.getReservations(userId);
        reservationListDto.getContent().forEach(reservationDto -> {
            System.out.println(reservationDto);
            reservationTableModel.addRow(new Object[] {
                    reservationDto.getTipSobe(),
                    reservationDto.getPocetniTerminDatum().format(DateTimeFormatter.ISO_LOCAL_DATE),
                    reservationDto.getKrajnjiTerminDatum().format(DateTimeFormatter.ISO_LOCAL_DATE),
                    reservationDto.getUserName(),
                    reservationDto.getCena()
            });
        });
    }



    public ReservationTableModel getReservationTableModel() {
        return reservationTableModel;
    }

    public JTable getReservationTable() {
        return reservationTable;
    }
}
