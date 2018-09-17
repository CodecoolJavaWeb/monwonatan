package com.codecool.queststore.backend.loginManager;
import com.codecool.queststore.backend.dao.LoginDAO;

public class LoginProcessHandler {

    private LoginDAO loginDAO;

    public LoginProcessHandler() {
        this.loginDAO = new LoginDAO();
    }

    public String loginProcess(String login, String password, PasswordManager passwordManager) {
        try {
            String hashedPassword = loginDAO.getPasswordBy(login);
            if (passwordManager.validatePassword(password, hashedPassword)) {
                return loginDAO.getTypeBy(login);
            }
        } catch (Exception e) {
            return "invalid password";
        }
        return null;
    }
}
