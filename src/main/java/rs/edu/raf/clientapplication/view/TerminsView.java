package rs.edu.raf.clientapplication.view;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import rs.edu.raf.clientapplication.ClientApplication;
import rs.edu.raf.clientapplication.restclient.ReservationServiceRestClient;
import rs.edu.raf.clientapplication.restclient.dto.CreateRezervacijaDto;
import rs.edu.raf.clientapplication.restclient.dto.HotelDto;
import rs.edu.raf.clientapplication.restclient.dto.PayloadDto;
import rs.edu.raf.clientapplication.restclient.dto.TerminListDto;
import rs.edu.raf.clientapplication.model.TerminTableModel;
import rs.edu.raf.clientapplication.restclient.TerminServiceRestClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TerminsView extends JPanel {
	private JPanel inputPanel;
	private TerminTableModel terminTableModel;
	private JTable terminTable;
	private TerminServiceRestClient terminServiceRestClient;
	private ReservationServiceRestClient reservationServiceRestClient;
	private JButton jButton;
	private JButton jButtonCreateReservation;
	private JTextField pocetniTerminInput;
	private JTextField krajnjiTerminInput;
	private JTextField tipSobeIdInput;
	private Long userId;


	public TerminsView() throws IllegalAccessException, NoSuchMethodException {
		super();
		this.setSize(1000, 1000);
		//this.setLayout(new BorderLayout());

		initInputPanel();
		terminTableModel = new TerminTableModel();
		terminServiceRestClient = new TerminServiceRestClient();
		reservationServiceRestClient = new ReservationServiceRestClient();
		terminTable = new JTable(terminTableModel);
		//this.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(terminTable);
		this.add(scrollPane);

		jButtonCreateReservation = new JButton("Create Reservation");
		this.add(jButtonCreateReservation);

		jButton = new JButton("Create Order");
		this.add(jButton);

		jButtonCreateReservation.addActionListener((event) -> {
			this.setVisible(false);
			CreateRezervacijaDto createRezervacijaDto = new CreateRezervacijaDto();
			createRezervacijaDto.setPocetniTerminId(Long.valueOf(pocetniTerminInput.getText()));
			createRezervacijaDto.setKrajnjiTerminId(Long.valueOf(krajnjiTerminInput.getText()));
			createRezervacijaDto.setTipSobeId(Long.valueOf(tipSobeIdInput.getText()));
			createRezervacijaDto.setUserId(userId);
			try {
				reservationServiceRestClient.createRezervacija(createRezervacijaDto);
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				ClientApplication.getInstance().getReservationView().init();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		jButton.addActionListener((event) -> {
			System.out.println(terminTableModel.getTerminListDto().getContent().get(terminTable.getSelectedRow()).getId());
		});

		setVisible(false);
	}

	public void init(Long hotelId) throws IOException {
		this.setVisible(true);
		PayloadDto payloadDto = ClientApplication.getPayload();
		userId = payloadDto.getId();
		TerminListDto terminListDto = terminServiceRestClient.getTermins(Long.valueOf(hotelId));
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

	private void initInputPanel(){
		inputPanel = new JPanel();

		pocetniTerminInput = new JTextField(10);
		krajnjiTerminInput = new JTextField(10);
		tipSobeIdInput = new JTextField(10);


		inputPanel.add(new JLabel("Pocetni termin"));
		inputPanel.add(pocetniTerminInput);

		inputPanel.add(new JLabel("Krajnji termin"));
		inputPanel.add(krajnjiTerminInput);

		inputPanel.add(new JLabel("Tip Sobe ID"));
		inputPanel.add(tipSobeIdInput);

		this.add(inputPanel, BorderLayout.CENTER);
	}

}
