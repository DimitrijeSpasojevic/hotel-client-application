package rs.edu.raf.clientapplication.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import rs.edu.raf.clientapplication.ClientApplication;
import rs.edu.raf.clientapplication.restclient.UserServiceRestClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginView extends JPanel {

	private JPanel inputPanel;
	private JPanel registerPanel;
	private JLabel emailLabel;
	private JLabel passwordLabel;
	private JTextField emailInput;
	private JPasswordField passwordInput;

	private JButton loginButton;
	private JButton registerCButton;
	private JButton registerMButton;

	private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();

	private ObjectMapper objectMapper = new ObjectMapper();

	public LoginView() {

		super();
		this.setSize(1000, 1000);

		initInputPanel();

		loginButton = new JButton("Login");
		registerCButton = new JButton("Register Client");
		registerMButton = new JButton("Register Manager");
		this.add(registerCButton,BorderLayout.EAST);
		this.add(registerMButton,BorderLayout.CENTER);
		this.add(loginButton, BorderLayout.SOUTH);

		registerMButton.addActionListener((event) -> {
			this.setVisible(false);
			ClientApplication.getInstance().getRegisterManagerView().init();
		});

		registerCButton.addActionListener((event) -> {
			this.setVisible(false);
			ClientApplication.getInstance().getRegisterClientView().init();
		});
		loginButton.addActionListener((event) -> {
			this.setVisible(false);

			try {
				String token = userServiceRestClient
					.login(emailInput.getText(), String.valueOf(passwordInput.getPassword()));
				ClientApplication.getInstance().setToken(token);
				ClientApplication.getInstance().getHomePageView().init();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private void initInputPanel() {

		inputPanel = new JPanel();

		emailLabel = new JLabel("Email: ");
		passwordLabel = new JLabel("Password: ");

		emailInput = new JTextField(20);
		passwordInput = new JPasswordField(20);

		inputPanel.add(emailLabel);
		inputPanel.add(emailInput);

		inputPanel.add(passwordLabel);
		inputPanel.add(passwordInput);

		this.add(inputPanel, BorderLayout.CENTER);
	}

	public void init() {
		this.setVisible(true);
	}
}
