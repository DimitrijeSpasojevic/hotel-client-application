package rs.edu.raf.clientapplication.view;

import rs.edu.raf.clientapplication.restclient.dto.ReservationListDto;
import rs.edu.raf.clientapplication.model.ReservationTableModel;
import rs.edu.raf.clientapplication.restclient.ReservationServiceRestClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ReservationView extends JPanel {
    private ReservationTableModel reservationTableModel;
    private JTable reservationTable;
    private ReservationServiceRestClient reservationServiceRestClient;
    private JButton jButton;

    public ReservationView() throws IllegalAccessException, NoSuchMethodException {
        super();
        this.setSize(400, 400);

        reservationTableModel = new ReservationTableModel();
        reservationServiceRestClient = new ReservationServiceRestClient();
        reservationTable = new JTable(reservationTableModel);
        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(reservationTable);
        this.add(scrollPane, BorderLayout.NORTH);

        jButton = new JButton("Create Order");
        this.add(jButton, BorderLayout.CENTER);

        jButton.addActionListener((event) -> {
            System.out.println(reservationTableModel.getReservationListDto().getContent().get(reservationTable.getSelectedRow()).getId());
        });

        setVisible(false);
    }

    public void init(Long userId) throws IOException {
        this.setVisible(true);

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
