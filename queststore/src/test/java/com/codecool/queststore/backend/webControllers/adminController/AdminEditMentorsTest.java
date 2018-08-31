package com.codecool.queststore.backend.webControllers.adminController;
import com.codecool.queststore.backend.dao.MentorDAO;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;





class AdminEditMentorsTest {


    private static MentorDAO mentorDAO;
    private static HttpExchange httpExchange;

    @BeforeEach
    void beforeEach() {
        httpExchange = mock(HttpExchange.class);
        mentorDAO = mock(MentorDAO.class);
    }

    @Test
    void testAdminIsAbleToAddNewMentor() {


        URI addMentorEndpoint = URI.create("admin/classroom/add");

        when(httpExchange.getRequestURI()).thenReturn(addMentorEndpoint);
        when(httpExchange.getRequestMethod()).thenReturn("POST");

        String postString = "firstname=imie" +
                "&lastname=nazwisko" +
                "&email=imie@nazwisko" +
                "&address=adresik" +
                "&classroom=132" +
                "&login=michal" +
                "&password1=password" +
                "&password2=password";

        InputStream stream = new ByteArrayInputStream(postString.getBytes(StandardCharsets.UTF_8));
        when(httpExchange.getRequestBody()).thenReturn(stream);
        assertTrue((new AdminEditMentors()).submitMentorCreationPage(httpExchange, mentorDAO));
    }


    @Test
    void testAdminIsAbleToDeleteMentor() {
        URI addMentorEndpoint = URI.create("/admin/classroom/delete/mentor-name");
        when(httpExchange.getRequestURI()).thenReturn(addMentorEndpoint);
        when(httpExchange.getRequestMethod()).thenReturn("POST");
        when(mentorDAO.deleteMentor("mentor-name")).thenReturn(true);
        AdminEditMentors aem = new AdminEditMentors();
        assertTrue(aem.submitMentorDeletionPage(httpExchange, mentorDAO));
    }


    @Test
    void testAdminIsUnableToAddNewMentorWhenPasswordsDoNotMatch() {
        URI addMentorEndpoint = URI.create("admin/classroom/add");
        when(httpExchange.getRequestURI()).thenReturn(addMentorEndpoint);
        when(httpExchange.getRequestMethod()).thenReturn("POST");
        // different passowrds
        String postString = "firstname=imie" +
                "&lastname=nazwisko" +
                "&email=imie@nazwisko" +
                "&address=adresik" +
                "&classroom=1" +
                "&login=michal" +
                "&password1=123" +
                "&password2=234";

        InputStream stream = new ByteArrayInputStream(postString.getBytes(StandardCharsets.UTF_8));
        when(httpExchange.getRequestBody()).thenReturn(stream);
        assertFalse((new AdminEditMentors()).submitMentorCreationPage(httpExchange, mentorDAO));
    }
}