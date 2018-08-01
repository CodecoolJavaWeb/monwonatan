package com.codecool.queststore.backend.loginManager;

import com.codecool.queststore.backend.dao.LoginDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LoginProcessHandlerTest {

    @InjectMocks private LoginProcessHandler mockLoginHandler;

    @Mock private LoginDAO mockLoginDAO;
    @Mock private PasswordManager mockPasswordManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginProcessFailure() throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException {
        when(mockLoginDAO.getPasswordBy("1")).thenReturn("hashedPass");
        when(mockPasswordManager.validatePassword("pass",
                "hashedPass")).thenReturn(false);
        when(mockLoginDAO.getTypeBy("1")).thenReturn("mentor");
        String loginResult = mockLoginHandler.loginProcess("1",
                "pass", mockPasswordManager);
        assertNull(loginResult);
    }

    @Test
    public void testLoginProcessSuccessful() throws InvalidKeySpecException, NoSuchAlgorithmException, SQLException {
        when(mockLoginDAO.getPasswordBy("1")).thenReturn("hashedPass");
        when(mockPasswordManager.validatePassword("pass",
                "hashedPass")).thenReturn(true);
        when(mockLoginDAO.getTypeBy("1")).thenReturn("mentor");
        String loginResult = mockLoginHandler.loginProcess("1",
                "pass", mockPasswordManager);
        assertEquals("mentor", loginResult);
    }
}