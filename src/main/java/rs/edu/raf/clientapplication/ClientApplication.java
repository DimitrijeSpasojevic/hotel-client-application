package rs.edu.raf.clientapplication;

import rs.edu.raf.clientapplication.view.LoginView;
import rs.edu.raf.clientapplication.view.TerminsView;

import javax.swing.*;
import java.awt.*;

public class ClientApplication extends JFrame{
    private String token;
    private LoginView loginView;
    private TerminsView terminsView;

    private ClientApplication() throws IllegalAccessException, NoSuchMethodException {
        this.setTitle("Client Application");
        this.setSize(1200, 1200);
        this.setLayout(new BorderLayout());

        loginView = new LoginView();
        this.add(loginView, BorderLayout.NORTH);

        terminsView = new TerminsView();
        this.add(terminsView, BorderLayout.CENTER);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static class InstanceHolder {
        private static ClientApplication instance;

        static {
            try {
                instance = new ClientApplication();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public TerminsView getTerminsView() {
        return terminsView;
    }

    public void setTerminsView(TerminsView terminsView) {
        this.terminsView = terminsView;
    }

    public static ClientApplication getInstance() {
        return InstanceHolder.instance;
    }

}
