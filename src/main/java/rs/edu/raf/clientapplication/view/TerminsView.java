package rs.edu.raf.clientapplication.view;

import rs.edu.raf.clientapplication.restclient.dto.HotelDto;
import rs.edu.raf.clientapplication.restclient.dto.TerminListDto;
import rs.edu.raf.clientapplication.model.TerminTableModel;
import rs.edu.raf.clientapplication.restclient.TerminServiceRestClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TerminsView extends JPanel {

	private TerminTableModel terminTableModel;
	private JTable terminTable;
	private TerminServiceRestClient terminServiceRestClient;
	private JButton jButton;

	public TerminsView() throws IllegalAccessException, NoSuchMethodException {
		super();
		this.setSize(400, 400);

		terminTableModel = new TerminTableModel();
		terminServiceRestClient = new TerminServiceRestClient();
		terminTable = new JTable(terminTableModel);
		this.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(terminTable);
		this.add(scrollPane, BorderLayout.NORTH);

		jButton = new JButton("Create Order");
		this.add(jButton, BorderLayout.CENTER);

		jButton.addActionListener((event) -> {
			System.out.println(terminTableModel.getTerminListDto().getContent().get(terminTable.getSelectedRow()).getId());
		});

		setVisible(false);
	}

	public void init() throws IOException {
		this.setVisible(true);
//TODO SALJE SE NEKI ID HOTELA
		TerminListDto terminListDto = terminServiceRestClient.getTermins(Long.valueOf(0));
		terminListDto.getContent().forEach(terminDto -> {
			System.out.println(terminDto);
			terminTableModel.addRow(new Object[]{terminDto.getTipSobeId(), terminDto.getHotel(),terminDto.getBrojSlobodnihSoba(),
					terminDto.getDatum(),terminDto.getTipSobe(), terminDto.getId()});
		});

	}

	public TerminTableModel getTerminTableModel() {
		return terminTableModel;
	}

	public JTable getTerminTable() {
		return terminTable;
	}
}
