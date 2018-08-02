package com.codecool.queststore.backend.webControllers.mentorController;

import com.codecool.queststore.backend.dao.MentorDAO;
import com.codecool.queststore.backend.model.Mentor;
import com.codecool.queststore.backend.webControllers.AbstractHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;

public class MentorIndex extends AbstractHandler implements HttpHandler {

    private Connection c;

    @Override
    public void handle(HttpExchange exchange) {
        try {
            String login = getLoginFromExchange(exchange);
            Mentor mentor = new MentorDAO(c).loadMentor(login);
            sendTemplateResponseIndex(exchange, "mentorindex", "Mentor Home", "Mentor's", mentor.getFirstName(), mentor.getLastName());
        }
        catch (SQLException e) {
            redirectToLocation(exchange, "logout");
        }
    }
}
