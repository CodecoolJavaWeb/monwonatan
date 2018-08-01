package com.codecool.queststore.backend.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MentorTest {

    private Mentor mentor;

    @BeforeEach
    public void setUp() {
        mentor = new Mentor("John",
                "Doe",
                "mentor",
                "1234",
                3,
                "mentor",
                "doe@doe.ru",
                "Gomel");
    }
    @AfterEach
    public void tearDown() {
        mentor = null;
    }

    @Test
    void testGetEmail() {
        assertEquals("doe@doe.ru", mentor.getEmail());
    }

    @Test
    void testSetEmail() {
        mentor.setEmail("john@john.ru");
        assertEquals("john@john.ru", mentor.getEmail());
    }

    @Test
    void testGetAddress() {
        assertEquals("Gomel", mentor.getAddress());
    }

    @Test
    void testSetAddress() {
        mentor.setAddress("Krakow");
        assertEquals("Krakow", mentor.getAddress());
    }
}