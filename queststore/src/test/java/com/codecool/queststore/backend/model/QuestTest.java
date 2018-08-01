package com.codecool.queststore.backend.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestTest {

    private Quest quest;

    @BeforeEach
    public void setUp() {
        quest = new Quest(1,
                "quest",
                "first",
                5);
    }

    @AfterEach
    public void tearDown() {
        quest = null;
    }

    @Test
    void getId() {
        assertEquals(1, quest.getId());
    }

    @Test
    void setId() {
        quest.setId(2);
        assertEquals(2, quest.getId());
    }

    @Test
    void getName() {
        assertEquals("quest", quest.getName());
    }

    @Test
    void setName() {
        quest.setName("big quest");
        assertEquals("big quest", quest.getName());
    }

    @Test
    void getDescription() {
        assertEquals("first", quest.getDescription());
    }

    @Test
    void setDescription() {
        quest.setDescription("second");
        assertEquals("second", quest.getDescription());
    }

    @Test
    void getValue() {
        assertEquals(5, quest.getValue());
    }

    @Test
    void setValue() {
        quest.setValue(9);
        assertEquals(9, quest.getValue());
    }
}