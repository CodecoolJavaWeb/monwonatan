package com.codecool.queststore.backend.dao;


import com.codecool.queststore.backend.databaseConnection.SQLQueryHandler;
import com.codecool.queststore.backend.model.Mentor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SQLQueryHandler.class)
class MentorDAOTest {

    MentorDAO mentorDAO;

    @Mock
    Connection mockConnection;
    PreparedStatement mockPreparedStatement;

    @Spy
    SQLQueryHandler mockSqlQueryHandler;
    ResultSet mockResultSet;

    @BeforeEach
    void setup() {
        mockConnection = mock(Connection.class);
        mockSqlQueryHandler = PowerMockito.spy(SQLQueryHandler.getInstance());
        mentorDAO = new MentorDAO(mockConnection, mockSqlQueryHandler);
        mockResultSet = spy(ResultSet.class);
        mockPreparedStatement = mock(PreparedStatement.class);
    }

    @Test
    void TestLoadMentor() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockSqlQueryHandler.executeQuery(mockPreparedStatement.toString())).thenReturn(mockResultSet);
        when(mockResultSet.getString("login")).thenReturn("mentor");
        when(mockResultSet.getString("email")).thenReturn("mentor@gmail.com");
        when(mockResultSet.getString("address")).thenReturn("adresik");

        Mentor mentor = mentorDAO.loadMentor("mentor");

        String expectedlogin = mentor.getLogin();
        String actualLogin = mockResultSet.getString("login");

        String expectedEmail = mentor.getEmail();
        String actualEmail = mockResultSet.getString("email");

        String expectedAdress = mentor.getAddress();
        String actualAddress = mockResultSet.getString("address");

        assertEquals(expectedlogin, actualLogin);
        assertEquals(expectedEmail, actualEmail);
        assertEquals(expectedAdress, actualAddress);
    }
}


