package rs.edu.raf.clientapplication.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class HotelListDto {

    List<HotelDto> content = new ArrayList<>();

    public HotelListDto(){
    }

    public HotelListDto(List<HotelDto> content) {
        this.content = content;
    }

    public List<HotelDto> getContent() {
        return content;
    }

    public void setContent(List<HotelDto> content) {
        this.content = content;
    }
}
