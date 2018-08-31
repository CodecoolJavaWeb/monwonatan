package com.codecool.queststore.backend.webControllers.adminController;

import com.codecool.queststore.backend.dao.MentorDAO;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AdminProfileTest {

    private static HttpExchange httpExchange;

    @BeforeEach
    void beforeEach() {
        httpExchange = mock(HttpExchange.class);
    }



    @Test
    void readAdminData() {



    }
}