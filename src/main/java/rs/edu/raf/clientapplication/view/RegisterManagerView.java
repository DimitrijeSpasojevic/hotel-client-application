package rs.edu.raf.clientapplication.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import rs.edu.raf.clientapplication.ClientApplication;
import rs.edu.raf.clientapplication.restclient.HotelServiceRestClient;
import rs.edu.raf.clientapplication.restclient.UserServiceRestClient;
import rs.edu.raf.clientapplication.restclient.dto.CreateManagerDto;
import rs.edu.raf.clientapplication.restclient.dto.HotelDto;
import rs.edu.raf.clientapplication.restclient.dto.HotelListDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegisterManagerView extends JPanel {
    private JPanel inputPanel;
    private JTextField emailInput;
    private JPasswordField passwordInput;
    private JTextField dateOfBirthInput;
    private JTextField usernameInput;
    private JTextField contactInput;
    private JTextField firstNameInput;
    private JTextField lastNameInput;
    private JComboBox<String> hotelNameInput;
    private JTextField dateOfHireInput;

    private JButton registerButton;

    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();

    private HotelServiceRestClient hotelServiceRestClient = new HotelServiceRestClient();

    public RegisterManagerView() {
        super();
        this.setSize(1000, 1000);
        initInputPanel();
        registerButton = new JButton("Register manager");
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
                CreateManagerDto createManagerDto = new CreateManagerDto();
                createManagerDto.setEmail(emailInput.getText());
                createManagerDto.setPassword(passwordInput.getText());
                createManagerDto.setDateOfBirth(LocalDate.parse(dateOfBirthInput.getText(), DateTimeFormatter.ISO_LOCAL_DATE));
                createManagerDto.setUsername(usernameInput.getText());
                createManagerDto.setContact(contactInput.getText());
                createManagerDto.setFirstName(firstNameInput.getText());
                createManagerDto.setLastName(lastNameInput.getText());
                createManagerDto.setHotelName(hotelNameInput.getSelectedItem().toString());
                createManagerDto.setHireDate(LocalDate.parse(dateOfHireInput.getText(), DateTimeFormatter.ISO_LOCAL_DATE));
                userServiceRestClient.registerManager(createManagerDto);
                ClientApplication.getInstance().getLoginView().init();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception exception){
                exception.printStackTrace();
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
        hotelNameInput = new JComboBox<>();
        HotelListDto hotelListDto;
        try {
            hotelListDto = hotelServiceRestClient.getHotels();
            for (HotelDto hotelDto : hotelListDto.getContent())
                hotelNameInput.addItem(hotelDto.getIme());
            hotelNameInput.setSelectedIndex(0);
        } catch (IOException error) {
            error.printStackTrace();
        }


        dateOfHireInput = new JTextField(10);


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

        inputPanel.add(new JLabel("Hotel name"));
        inputPanel.add(hotelNameInput);

        inputPanel.add(new JLabel("Date of hire"));
        inputPanel.add(dateOfHireInput);


        this.add(inputPanel);
    }

    public void init(){
        this.setVisible(true);
    }
}
