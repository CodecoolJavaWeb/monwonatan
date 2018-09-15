package com.codecool.queststore.backend.webControllers.mentorController;

import com.codecool.queststore.backend.dao.QuestDAO;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuestManagerTest {

    private String testResponseBodyString;
    private Map<String, String> expectedData;

    @BeforeEach
    void initTestData() {
        this.testResponseBodyString = "submit=dupa&name=zadanko&description=lol&value=3";
        this.expectedData = createMapForParse();
    }

    @Test
    void testReadQuestData() {
        QuestManager questManager = new QuestManager(new QuestDAO());
        InputStream targetStream = new ByteArrayInputStream(testResponseBodyString.getBytes());
        HttpExchange mockedHttpExchange = mock(HttpExchange.class);

        when(mockedHttpExchange.getRequestBody()).thenReturn(targetStream);

        Map<String, String> questData = questManager.readQuestData(mockedHttpExchange);
        assertEquals(expectedData, questData);
    }


    //Testing if parseQuestData behaviour during execution is preserved.
    @Test
    void testParseQuestData() {
        QuestManager questManager = new QuestManager(new QuestDAO());
        Map<String, String> parsedData = questManager.parseQuestData(testResponseBodyString);

        assertEquals(expectedData, parsedData);
    }



    private Map<String, String> createMapForParse() {
        Map<String, String> testMap = new HashMap<>();

        testMap.put("submit", "dupa");
        testMap.put("name", "zadanko");
        testMap.put("description", "lol");
        testMap.put("value", "3");

        return testMap;
    }
}