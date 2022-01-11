package rs.edu.raf.clientapplication.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import rs.edu.raf.clientapplication.restclient.UserServiceRestClient;
import rs.edu.raf.clientapplication.restclient.dto.CreateManagerDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

public class RegisterManagerView extends JPanel {
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
    private JTextField hotelNameInput;
    private JTextField dateOfHireInput;
    private JTextField monthOfHireInput;
    private JTextField yearOfHireInput;

    private JButton registerButton;

    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();

    private ObjectMapper objectMapper = new ObjectMapper();

    public RegisterManagerView() {

        super();
        this.setSize(1000, 1000);
        this.setLayout(new BorderLayout());

        initInputPanel();

        registerButton = new JButton("Register manager");
        this.add(registerButton, BorderLayout.SOUTH);
        registerButton.addActionListener((event) -> {
            try {
                CreateManagerDto createManagerDto = new CreateManagerDto();
                createManagerDto.setEmail(emailInput.getText());
                createManagerDto.setPassword(passwordInput.getText());
                createManagerDto.setDateOfBirth(LocalDate.of(Integer.parseInt(yearOfBirthInput.getText()),
                        Integer.parseInt(monthOfBirthInput.getText()),
                        Integer.parseInt(dateOfBirthInput.getText())));
                createManagerDto.setUsername(usernameInput.getText());
                createManagerDto.setContact(contactInput.getText());
                createManagerDto.setFirstName(firstNameInput.getText());
                createManagerDto.setLastName(lastNameInput.getText());
                createManagerDto.setHotelName(hotelNameInput.getText());
                createManagerDto.setDateOfBirth(LocalDate.of(Integer.parseInt(dateOfHireInput.getText()),
                        Integer.parseInt(dateOfHireInput.getText()),
                        Integer.parseInt(dateOfHireInput.getText())));
                userServiceRestClient.registerManager(createManagerDto);
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
        hotelNameInput = new JTextField(30);
        dateOfHireInput = new JTextField(2);
        monthOfHireInput = new JTextField(2);
        yearOfHireInput = new JTextField(4);


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

        inputPanel.add(new JLabel("Hotel name"));
        inputPanel.add(hotelNameInput);

        inputPanel.add(new JLabel("Date of hire"));
        inputPanel.add(dateOfHireInput);

        inputPanel.add(new JLabel("Month of hire"));
        inputPanel.add(monthOfHireInput);

        inputPanel.add(new JLabel("Year of hire"));
        inputPanel.add(yearOfHireInput);

        this.add(inputPanel, BorderLayout.CENTER);
    }

    public void init(){
        this.setVisible(true);
    }
}
