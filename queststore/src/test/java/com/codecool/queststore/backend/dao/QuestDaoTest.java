package com.codecool.queststore.backend.dao;

import com.codecool.queststore.backend.databaseConnection.PostgreSQLJDBC;
import com.codecool.queststore.backend.databaseConnection.SQLQueryHandler;
import com.codecool.queststore.backend.model.Artifact;
import com.codecool.queststore.backend.model.Quest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SQLQueryHandler.class)
public class QuestDaoTest {

    QuestDAO questDAO;

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
        questDAO = new QuestDAO(mockConnection, mockSqlQueryHandler);
        mockResultSet = spy(ResultSet.class);
        mockPreparedStatement = mock(PreparedStatement.class);
    }

    @Test
    public void testLoadQuest() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockSqlQueryHandler.executeQuery(mockPreparedStatement.toString())).
                thenReturn(mockResultSet);

        when(mockResultSet.getString("name")).thenReturn("Quest1");
        when(mockResultSet.getString("description")).thenReturn("something");
        when(mockResultSet.getInt("value")).thenReturn(1000);

        Quest quest = questDAO.loadQuest(1);
        String actual = quest.getName();
        String expected = mockResultSet.getString("name");

        assertEquals(expected, actual);

    }
}
