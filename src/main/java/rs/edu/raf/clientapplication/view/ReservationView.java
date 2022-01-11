package rs.edu.raf.clientapplication.view;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import rs.edu.raf.clientapplication.ClientApplication;
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
    private JButton jButtonDelete;

    public ReservationView() throws IllegalAccessException, NoSuchMethodException {
        super();
        this.setSize(400, 400);

        reservationTableModel = new ReservationTableModel();
        reservationServiceRestClient = new ReservationServiceRestClient();
        reservationTable = new JTable(reservationTableModel);
        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(reservationTable);
        this.add(scrollPane, BorderLayout.NORTH);
        jButtonDelete = new JButton("Delete reservation");
        this.add(jButtonDelete, BorderLayout.CENTER);

        jButtonDelete.addActionListener((event) -> {
            Long id = reservationTableModel.getReservationListDto().getContent().get(reservationTable.getSelectedRow()).getId();
            System.out.println(id);
            //TODO delete rezervation
            try {
                init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setVisible(false);
    }

    public void init() throws IOException {
        this.setVisible(true);
        String[] chunks = ClientApplication.getInstance().getToken().split("\\.");
        Claims claims = parseToken(chunks[0] + "." +chunks[1]);
        Long userId = claims.get("id", Long.class);
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

    public Claims parseToken(String jwt) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
        return claims;
    }

    public ReservationTableModel getReservationTableModel() {
        return reservationTableModel;
    }

    public JTable getReservationTable() {
        return reservationTable;
    }
}
