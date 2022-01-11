package rs.edu.raf.clientapplication.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class TerminListDto {

    private List<TerminDto> content = new ArrayList<>();

    public TerminListDto() {

    }

    public TerminListDto(List<TerminDto> content) {
        this.content = content;
    }

    public List<TerminDto> getContent() {
        return content;
    }

    public void setContent(List<TerminDto> content) {
        this.content = content;
    }
}
