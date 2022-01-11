package rs.edu.raf.clientapplication.model;

import rs.edu.raf.clientapplication.restclient.dto.HotelDto;
import rs.edu.raf.clientapplication.restclient.dto.HotelListDto;
import javax.swing.table.DefaultTableModel;

public class HotelTableModel extends DefaultTableModel {

    public HotelTableModel() throws IllegalAccessException, NoSuchMethodException {
        super(new String[]{"Naziv", "Opis","Grad"}, 0);
    }

    private HotelListDto hotelListDto = new HotelListDto();

    @Override
    public void addRow(Object[] row) {
        super.addRow(row);
        HotelDto dto = new HotelDto();
        dto.setIme(String.valueOf(row[0]));
        dto.setOpis(String.valueOf(row[1]));
        dto.setGrad(String.valueOf(row[2]));
        dto.setId(Long.valueOf(String.valueOf(row[3])));

        hotelListDto.getContent().add(dto);
    }

    public HotelListDto getHotelListDto() {
        return hotelListDto;
    }

    public void setHotelListDto(HotelListDto hotelListDto) {
        this.hotelListDto = hotelListDto;
    }
}
