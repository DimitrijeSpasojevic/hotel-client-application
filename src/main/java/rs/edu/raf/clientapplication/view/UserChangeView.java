package rs.edu.raf.clientapplication.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.internal.org.objectweb.asm.tree.JumpInsnNode;
import rs.edu.raf.clientapplication.ClientApplication;
import rs.edu.raf.clientapplication.restclient.UserServiceRestClient;
import rs.edu.raf.clientapplication.restclient.dto.ClientDto;
import rs.edu.raf.clientapplication.restclient.dto.HotelDto;
import rs.edu.raf.clientapplication.restclient.dto.PayloadDto;
import rs.edu.raf.clientapplication.restclient.dto.UpdateClientDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class UserChangeView extends JPanel {
    private JPanel inputPanel;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JTextField emailInput;
    private JTextField contactInput;
    private JTextField firstNameInput;
    private JTextField lastNameInput;
    private JTextField passportInput;
    private JButton changeButton;

    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    public UserChangeView() {
        this.setSize(1000, 1000);
        this.setVisible(false);

        initInputPanel();

        changeButton = new JButton("Change client");

        this.add(changeButton, BorderLayout.SOUTH);
        changeButton.addActionListener((event) -> {
            try {
                UpdateClientDto updateClientDto = new UpdateClientDto();
                updateClientDto.setUsername(usernameInput.getText());
                updateClientDto.setEmail(emailInput.getText());
                updateClientDto.setPassword(String.valueOf(passwordInput.getPassword()));
                updateClientDto.setFirstName(firstNameInput.getText());
                updateClientDto.setLastName(lastNameInput.getText());
                updateClientDto.setPassportId(Long.valueOf(passportInput.getText()));
                updateClientDto.setContact(contactInput.getText());
                userServiceRestClient.updateClient(updateClientDto);
                this.setVisible(false);
                ClientApplication.getInstance().getHomePageView().init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initInputPanel()  {
        inputPanel = new JPanel();
        usernameInput = new JTextField(30);
        emailInput = new JTextField(20);
        passwordInput = new JPasswordField(20);
        contactInput = new JTextField(30);
        firstNameInput = new JTextField(30);
        lastNameInput = new JTextField(30);
        passportInput = new JTextField(30);

        inputPanel.add(new JLabel("Username"));
        inputPanel.add(usernameInput);

        inputPanel.add(new JLabel("Email"));
        inputPanel.add(emailInput);

        inputPanel.add(new JLabel("Password"));
        inputPanel.add(passwordInput);

        inputPanel.add(new JLabel("Contact"));
        inputPanel.add(contactInput);

        inputPanel.add(new JLabel("First name"));
        inputPanel.add(firstNameInput);

        inputPanel.add(new JLabel("Last name"));
        inputPanel.add(lastNameInput);

        inputPanel.add(new JLabel("Passport"));
        inputPanel.add(passportInput);

    }

    public void init() throws IOException {
        ClientDto clientDto = userServiceRestClient.getClientDto();
        usernameInput.setText(clientDto.getUsername());
        emailInput.setText(clientDto.getEmail());
        passwordInput.setText(clientDto.getPassword());
        contactInput.setText(clientDto.getContact());
        firstNameInput.setText(clientDto.getFirstName());
        lastNameInput.setText(clientDto.getLastName());
        passportInput.setText(String.valueOf(clientDto.getPassportId()));
        this.setVisible(true);
    }
}
