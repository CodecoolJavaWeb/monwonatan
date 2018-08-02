package com.codecool.queststore.backend.dao;

import static org.junit.jupiter.api.Assertions.*;
import com.codecool.queststore.backend.databaseConnection.PostgreSQLJDBC;
import com.codecool.queststore.backend.databaseConnection.SQLQueryHandler;
import com.codecool.queststore.backend.model.Artifact;
import com.codecool.queststore.backend.model.Backpack;
import com.codecool.queststore.backend.model.Student;
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
class StudentDAOTest {

    StudentDAO studentDAO;

    @Mock
    Connection mockConnection;
    PreparedStatement mockPreparedStatement;

    @Spy
    SQLQueryHandler mockSqlQueryHandler;
    ResultSet mockResultSet;

    @BeforeEach
    void setup() throws Exception {
        mockConnection = mock(Connection.class);
        PostgreSQLJDBC connectionEstablisher = mock(PostgreSQLJDBC.class);
        doReturn(mockConnection).when(connectionEstablisher).getConnection();
        mockSqlQueryHandler = PowerMockito.spy(SQLQueryHandler.getInstance());
        studentDAO = new StudentDAO(mockConnection, mockSqlQueryHandler);
        mockResultSet = spy(ResultSet.class);
        mockPreparedStatement = mock(PreparedStatement.class);
    }

    @Test
    public void TestLoadStudent() throws SQLException {
        Student student = new Student("Adam", "Kowalski", "Adam", "Adam", 1, "student", 10, 100);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockSqlQueryHandler.executeQuery(mockPreparedStatement.toString())).thenReturn(mockResultSet);
        when(mockResultSet.getString("first_name")).thenReturn("Adam");
        when(mockResultSet.getString("last_name")).thenReturn("Kowalski");

        assertEquals(student.getFirstName(), mockResultSet.getString("first_name"));
    }
}