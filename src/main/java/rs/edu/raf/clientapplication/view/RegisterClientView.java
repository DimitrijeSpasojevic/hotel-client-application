package rs.edu.raf.clientapplication.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import rs.edu.raf.clientapplication.ClientApplication;
import rs.edu.raf.clientapplication.restclient.UserServiceRestClient;
import rs.edu.raf.clientapplication.restclient.dto.CreateClientDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegisterClientView extends JPanel {
    private JPanel inputPanel;
    private JTextField emailInput;
    private JPasswordField passwordInput;
    private JTextField dateOfBirthInput;
    private JTextField usernameInput;
    private JTextField contactInput;
    private JTextField firstNameInput;
    private JTextField lastNameInput;
    private JTextField passportInput;

    private JButton registerButton;

    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();

    public RegisterClientView() {
        super();
        this.setSize(1000, 1000);

        initInputPanel();

        registerButton = new JButton("Register client");
        this.add(registerButton);

        JButton backToLogin = new JButton("Back to login");
        backToLogin.addActionListener((event) -> {
            this.setVisible(false);
            ClientApplication.getInstance().getLoginView().init();
        });
        this.add(backToLogin);

        registerButton.addActionListener((event) -> {
            this.setVisible(false);
            try {
                CreateClientDto createClientDto = new CreateClientDto();
                createClientDto.setEmail(emailInput.getText());
                createClientDto.setPassword(passwordInput.getText());
                createClientDto.setDateOfBirth(LocalDate.parse(dateOfBirthInput.getText(), DateTimeFormatter.ISO_LOCAL_DATE));
                createClientDto.setUsername(usernameInput.getText());
                createClientDto.setContact(contactInput.getText());
                createClientDto.setFirstName(firstNameInput.getText());
                createClientDto.setLastName(lastNameInput.getText());
                createClientDto.setPassportId(Long.valueOf(passportInput.getText()));
                userServiceRestClient.registerClient(createClientDto);
                ClientApplication.getInstance().getLoginView().init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        this.setVisible(false);
    }

    private void initInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0,1));
        emailInput = new JTextField(20);
        passwordInput = new JPasswordField(20);
        dateOfBirthInput = new JTextField(2);
        usernameInput = new JTextField(30);
        contactInput = new JTextField(30);
        firstNameInput = new JTextField(30);
        lastNameInput = new JTextField(30);
        passportInput = new JTextField(30);

        inputPanel.add(new JLabel("Email: "));
        inputPanel.add(emailInput);

        inputPanel.add(new JLabel("Password: "));
        inputPanel.add(passwordInput);

        inputPanel.add(new JLabel("Date of birth"));
        inputPanel.add(dateOfBirthInput);


        inputPanel.add(new JLabel("Username"));
        inputPanel.add(usernameInput);

        inputPanel.add(new JLabel("Contact"));
        inputPanel.add(contactInput);

        inputPanel.add(new JLabel("First name"));
        inputPanel.add(firstNameInput);

        inputPanel.add(new JLabel("Last name"));
        inputPanel.add(lastNameInput);

        inputPanel.add(new JLabel("Passport id"));
        inputPanel.add(passportInput);

        this.add(inputPanel);
    }
    public void init(){
        this.setVisible(true);
    }
}
