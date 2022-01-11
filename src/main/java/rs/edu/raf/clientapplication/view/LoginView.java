package rs.edu.raf.clientapplication.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import rs.edu.raf.clientapplication.ClientApplication;
import rs.edu.raf.clientapplication.restclient.UserServiceRestClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginView extends JPanel {

	private JPanel inputPanel;

	private JLabel emailLabel;
	private JLabel passwordLabel;
	private JTextField emailInput;
	private JPasswordField passwordInput;

	private JButton loginButton;

	private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();

	private ObjectMapper objectMapper = new ObjectMapper();

	public LoginView() {

		super();
		this.setSize(400, 400);

		this.setLayout(new BorderLayout());

		initInputPanel();

		loginButton = new JButton("Login");
		this.add(loginButton, BorderLayout.SOUTH);
		loginButton.addActionListener((event) -> {

			try {
				String token = userServiceRestClient
					.login(emailInput.getText(), String.valueOf(passwordInput.getPassword()));
				this.setVisible(false);
				ClientApplication.getInstance().setToken(token);
				System.out.println(token);
				ClientApplication.getInstance().getTerminsView().init();

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
}
