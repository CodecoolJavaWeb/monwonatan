package com.codecool.queststore.backend.databaseConnection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@RunWith(MockitoJUnitRunner.class)
class SQLQueryHandlerTest {


    @InjectMocks private SQLQueryHandler queryHandler;

    @Mock private Connection mockConnection;
    @Mock private Statement mockStatement;
    @Mock private ResultSet mockResultSet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetConnection() throws SQLException {

        doReturn(mockStatement).when(mockConnection).createStatement();
        doReturn(mockResultSet).when(mockStatement).executeQuery(any());
        ResultSet value = queryHandler.executeQuery("123465");
        assertEquals(mockResultSet, value);
        verify(mockConnection.createStatement(), times(1));
    }


}