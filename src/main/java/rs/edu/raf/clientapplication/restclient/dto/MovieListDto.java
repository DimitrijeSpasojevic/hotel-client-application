package rs.edu.raf.clientapplication.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class MovieListDto {

	private List<MovieDto> content = new ArrayList<>();

	public MovieListDto() {

	}

	public MovieListDto(List<MovieDto> content) {
		this.content = content;
	}

	public List<MovieDto> getContent() {
		return content;
	}

	public void setContent(List<MovieDto> content) {
		this.content = content;
	}
}
