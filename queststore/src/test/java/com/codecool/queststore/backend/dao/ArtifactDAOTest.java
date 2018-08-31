package com.codecool.queststore.backend.dao;

import com.codecool.queststore.backend.databaseConnection.PostgreSQLJDBC;
import com.codecool.queststore.backend.databaseConnection.SQLQueryHandler;
import com.codecool.queststore.backend.model.Artifact;
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
class ArtifactDAOTest {

    ArtifactDAO artifactDAO;

    @Mock
    Connection mockConnection;
    PreparedStatement mockPreparedStatement;

    @Spy
    SQLQueryHandler mockSqlQueryHandler;
    ResultSet mockResultSet;

    @BeforeEach
    void setup() throws Exception{
        mockConnection = mock(Connection.class);
        PostgreSQLJDBC connectionEstablisher = mock(PostgreSQLJDBC.class);

        doReturn(mockConnection).when(connectionEstablisher).getConnection();
        mockSqlQueryHandler = PowerMockito.spy(SQLQueryHandler.getInstance());
        artifactDAO = new ArtifactDAO(mockConnection, mockSqlQueryHandler);
        mockResultSet = spy(ResultSet.class);
        mockPreparedStatement = mock(PreparedStatement.class);
    }


    @Test
    public void testLoadArtifact() throws SQLException {

        Artifact artifact = new Artifact(1, true,
                "Dragon", "something", 1000);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        when(mockSqlQueryHandler.executeQuery(mockPreparedStatement.toString())).
                thenReturn(mockResultSet);
        when(mockResultSet.getBoolean("available_for_groups")).thenReturn(true);
        when(mockResultSet.getString("name")).thenReturn("Dragon");
        when(mockResultSet.getString("description")).thenReturn("something");
        when(mockResultSet.getInt("price")).thenReturn(1000);
        artifactDAO.loadArtifact(1);

        assertEquals(artifact.getName(), mockResultSet.getString("name"));

    }
}