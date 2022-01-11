package rs.edu.raf.clientapplication.model;


import rs.edu.raf.clientapplication.restclient.dto.TerminDto;
import rs.edu.raf.clientapplication.restclient.dto.TerminListDto;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

public class TerminTableModel extends DefaultTableModel {


	public TerminTableModel() throws IllegalAccessException, NoSuchMethodException {
		super(new String[]{"TipSobeId", "Hotel","BrojSlobodnihSoba","Datum","TipSobe"}, 0);
	}

	private TerminListDto terminListDto = new TerminListDto();

	@Override
	public void addRow(Object[] row) {
		super.addRow(row);
		TerminDto dto = new TerminDto();
		dto.setTipSobeId(Long.valueOf(String.valueOf(row[0])));
		dto.setHotel(String.valueOf(row[1]));
		dto.setBrojSlobodnihSoba(Integer.valueOf(String.valueOf(row[2])));
		dto.setDatum((LocalDate)row[3]);
		dto.setTipSobe(String.valueOf(row[4]));
		dto.setId(Long.valueOf(String.valueOf(row[5])));

		terminListDto.getContent().add(dto);
	}

	public TerminListDto getTerminListDto() {
		return terminListDto;
	}

	public void setTerminListDto(TerminListDto terminListDto) {
		this.terminListDto = terminListDto;
	}
}
