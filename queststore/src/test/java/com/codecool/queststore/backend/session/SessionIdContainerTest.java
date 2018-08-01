package com.codecool.queststore.backend.session;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SessionIdContainerTest {

    private SessionIdContainer session;
    private Map<String, String> container;

    @BeforeEach
    void setUp() {
        session = SessionIdContainer.getSessionIdContainer();
        container = new HashMap<>();
        container.put("firstId", "cuckoo");
    }

    @AfterEach
    void tearDown() {
        container = null;
        session.getContainer().clear();
    }

    @Test
    void testGetContainer() {
        session.add("firstId", "cuckoo");
        assertEquals(container, session.getContainer());
    }

    @Test
    void testGetUserLogin() {
        session.add("firstId", "cuckoo");
        assertEquals("cuckoo", session.getUserLogin("firstId"));
    }

    @Test
    void testAddToContainer() {
        session.add("secondId", "hi");
        container.remove("firstId");
        container.put("secondId", "hi");
        assertEquals(container, session.getContainer());
    }

    @Test
    void testRemoveFromContainer() {
        container.remove("firstId");
        session.add("123" , "123");
        session.remove("123");
        assertEquals(container, session.getContainer());
    }

    @Test
    void testIfContainerHasId() {
        session.add("tenthId", "contained");
        assertTrue(session.contains("tenthId"));
    }
}