package com.codecool.queststore.backend.model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class StudentTest {

    @Test
    public void testCreateConstructor(){
        Student student = new Student("Adam", "Kowalski", "Adam", "Adam", 1, "student", 5, 10);
        assertEquals("Adam", student.getFirstName());
        assertEquals("Kowalski", student.getLastName());
        assertEquals(10, student.getTotalCoins());
    }
}