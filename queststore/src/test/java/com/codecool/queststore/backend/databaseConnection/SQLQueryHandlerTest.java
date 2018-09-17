package com.codecool.queststore.backend.databaseConnection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
//@Disabled
class SQLQueryHandlerTest {


    @InjectMocks private SQLQueryHandler queryHandler;

    @Mock private Connection mockConnection;
    @Mock private PreparedStatement mockStatement;
    @Mock private ResultSet mockResultSet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecuteQUery() throws SQLException {

        doReturn(mockStatement).when(mockConnection).createStatement();
        doReturn(mockResultSet).when(mockStatement).executeQuery("123465");
        ResultSet value = queryHandler.executeQuery("123465");
        assertEquals(value, mockResultSet);
    }


}