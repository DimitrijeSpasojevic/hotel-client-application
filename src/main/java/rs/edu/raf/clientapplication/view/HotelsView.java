package rs.edu.raf.clientapplication.view;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import rs.edu.raf.clientapplication.ClientApplication;
import rs.edu.raf.clientapplication.model.HotelTableModel;
import rs.edu.raf.clientapplication.restclient.HotelServiceRestClient;
import rs.edu.raf.clientapplication.restclient.dto.HotelListDto;

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
        this.setSize(400, 400);

        hotelTableModel = new HotelTableModel();
        hotelServiceRestClient = new HotelServiceRestClient();
        hotelTable = new JTable(hotelTableModel);
        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(hotelTable);
        this.add(scrollPane, BorderLayout.NORTH);

        jButtonShow = new JButton("Show hotel");
        jButtonChangeHotel = new JButton("Change hotel");
        this.add(jButtonShow, BorderLayout.CENTER);
        this.add(jButtonChangeHotel, BorderLayout.CENTER);


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
            ClientApplication.getInstance().getHotelChangeView().init();
        });

        setVisible(false);
    }

    public void init() throws IOException {
        String[] chunks = ClientApplication.getInstance().getToken().split("\\.");
        Claims claims = parseToken(chunks[0] + "." +chunks[1]);
        role = claims.get("role",String.class);

        if(role.equals("ROLE_CLIENT")){
            jButtonChangeHotel.setVisible(false);
        }
        this.setVisible(true);
        HotelListDto hotelListDto = hotelServiceRestClient.getHotels();
        hotelListDto.getContent().forEach(hotelDto -> {
            System.out.println(hotelDto);
            //TODO remove rows
            hotelTableModel.addRow(new Object[]{hotelDto.getIme(), hotelDto.getOpis(),hotelDto.getGrad(),hotelDto.getId()});
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
}
