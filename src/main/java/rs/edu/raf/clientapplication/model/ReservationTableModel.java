package rs.edu.raf.clientapplication.model;

import rs.edu.raf.clientapplication.restclient.dto.ReservationDto;
import rs.edu.raf.clientapplication.restclient.dto.ReservationListDto;

import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationTableModel extends DefaultTableModel {
    public ReservationTableModel() throws IllegalAccessException, NoSuchMethodException {
        super(new String[]{"Tip Sobe", "Pocetni datum", "Krajnji Datum", "Ime korisnika", "Cena", "Id"}, 0);
    }

    private ReservationListDto reservationListDto = new ReservationListDto();

    @Override
    public void addRow(Object[] row) {
        super.addRow(row);
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setTipSobe(String.valueOf(row[0]));
        reservationDto.setPocetniTerminDatum(LocalDate.parse(String.valueOf(row[1]), DateTimeFormatter.ISO_LOCAL_DATE));
        reservationDto.setKrajnjiTerminDatum(LocalDate.parse(String.valueOf(row[2]), DateTimeFormatter.ISO_LOCAL_DATE));
        reservationDto.setUserName(String.valueOf(row[3]));
        reservationDto.setCena(new BigDecimal(String.valueOf(row[4])));
        reservationDto.setId(Long.valueOf(String.valueOf(row[5])));
        reservationListDto.getContent().add(reservationDto);
    }

    public ReservationListDto getReservationListDto() {
        return reservationListDto;
    }

    public void setReservationListDto(ReservationListDto reservationListDto) {
        this.reservationListDto = reservationListDto;
    }
}
