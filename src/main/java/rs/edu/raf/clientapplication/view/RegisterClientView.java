package rs.edu.raf.clientapplication.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import rs.edu.raf.clientapplication.restclient.UserServiceRestClient;
import rs.edu.raf.clientapplication.restclient.dto.CreateClientDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

public class RegisterClientView extends JPanel {
    private JPanel inputPanel;
    private JTextField emailInput;
    private JPasswordField passwordInput;
    private JTextField dateOfBirthInput;
    private JTextField monthOfBirthInput;
    private JTextField yearOfBirthInput;
    private JTextField usernameInput;
    private JTextField contactInput;
    private JTextField firstNameInput;
    private JTextField lastNameInput;
    private JTextField passportInput;

    private JButton registerButton;

    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();

    private ObjectMapper objectMapper = new ObjectMapper();

    public RegisterClientView() {

        super();
        this.setSize(400, 400);

        this.setLayout(new BorderLayout());

        initInputPanel();

        registerButton = new JButton("Register client");
        this.add(registerButton, BorderLayout.SOUTH);
        registerButton.addActionListener((event) -> {
            try {
                CreateClientDto createClientDto = new CreateClientDto();
                createClientDto.setEmail(emailInput.getText());
                createClientDto.setPassword(passwordInput.getText());
                createClientDto.setDateOfBirth(LocalDate.of(Integer.parseInt(yearOfBirthInput.getText()),
                        Integer.parseInt(monthOfBirthInput.getText()),
                        Integer.parseInt(dateOfBirthInput.getText())));
                createClientDto.setUsername(usernameInput.getText());
                createClientDto.setContact(contactInput.getText());
                createClientDto.setFirstName(firstNameInput.getText());
                createClientDto.setLastName(lastNameInput.getText());
                createClientDto.setPassportId(Long.valueOf(passportInput.getText()));
                userServiceRestClient.registerClient(createClientDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        this.setVisible(false);
    }

    private void initInputPanel() {
        inputPanel = new JPanel();

        emailInput = new JTextField(20);
        passwordInput = new JPasswordField(20);
        dateOfBirthInput = new JTextField(2);
        monthOfBirthInput = new JTextField(2);
        yearOfBirthInput = new JTextField(4);
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

        inputPanel.add(new JLabel("Month of birth"));
        inputPanel.add(monthOfBirthInput);

        inputPanel.add(new JLabel("Year of birth"));
        inputPanel.add(yearOfBirthInput);

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

        this.add(inputPanel, BorderLayout.CENTER);
    }
    public void init(){
        this.setVisible(true);
    }
}
