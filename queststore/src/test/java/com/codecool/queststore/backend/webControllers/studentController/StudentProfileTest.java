package com.codecool.queststore.backend.webControllers.studentController;

import com.codecool.queststore.backend.model.Artifact;
import com.codecool.queststore.backend.model.Backpack;
import com.codecool.queststore.backend.model.Student;
import com.codecool.queststore.backend.webControllers.AbstractHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StudentProfileTest extends AbstractHandler {


    StudentProfile studentProfile;

    @BeforeEach
    public void setUp() {
        studentProfile = new StudentProfile();
    }

    @Test
    public void testIfFormDataIsParseCorrectly() {
        String data = "submit=Adam&value=Kowalski";
        Map<String, String> map = new HashMap<>();
        map.put("submit", "Adam");
        map.put("value", "Kowalski");
        assertEquals(map, studentProfile.parseStudentData(data));
    }
    @Test
    public void testIfIsAbleToCreateStudentWithEmptyBackPack() {
        Student student = new Student("abc", "abc", "abc",
                "abc", 0, "Php abc", 0, 0);
        Backpack emptyBackpack = new Backpack(student.getLogin());
        student.setBackpack(emptyBackpack);
        assertEquals(0, student.getBackpack().getStudentBackpack().size());
    }

    @Test
    public void testIfIsAbleToAddArtifactToStudentBackPack() {
        Student student = new Student("abc", "abc", "abc",
                "abc", 0, "Php abc", 0, 0);
        Backpack emptyBackpack = new Backpack(student.getLogin());
        student.setBackpack(emptyBackpack);
        student.getBackpack().getStudentBackpack().put(new Artifact(1, true, "abc", "abc", 1), "Abc");
        assertEquals(1, emptyBackpack.getStudentBackpack().size());
    }
}