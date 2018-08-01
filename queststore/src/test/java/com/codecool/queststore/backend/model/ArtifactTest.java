package com.codecool.queststore.backend.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtifactTest {

    private Artifact artifact;

    @BeforeEach
    void setUp() {
        artifact = new Artifact(1, true, "gold", "golden", 3);
    }

    @AfterEach
    void tearDown() {
        artifact = null;
    }

    @Test
    void testGetArtifactId() {
        assertEquals(1, artifact.getArtifactId());
    }

    @Test
    void testIsAvailableForGroups() {
        assertTrue(artifact.isAvailableForGroups());
    }

    @Test
    void testSetAvailableForGroups() {
        artifact.setAvailableForGroups(false);
        assertFalse(artifact.isAvailableForGroups());
    }

    @Test
    void testGetName() {
        assertEquals("gold", artifact.getName());
    }

    @Test
    void testSetName() {
        artifact.setName("silver");
        assertEquals("silver", artifact.getName());
    }

    @Test
    void testGetDescription() {
        assertEquals("golden", artifact.getDescription());
    }

    @Test
    void testSetDescription() {
        artifact.setDescription("of silver");
        assertEquals("of silver", artifact.getDescription());
    }

    @Test
    void testGetPrice() {
        assertEquals(3, artifact.getPrice());
    }

    @Test
    void testSetPrice() {
        artifact.setPrice(5);
        assertEquals(5, artifact.getPrice());
    }

}