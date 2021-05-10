package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTestHomework {

    private static Service serviceBefore;

    @BeforeEach
    void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "mock/xml/students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "mock/xml/homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "mock/xml/grades.xml");

        serviceBefore = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    void addHomework1() {
        serviceBefore.saveHomework("100", "Foarte greu", 9, 7);

        Iterable<Homework> homes = serviceBefore.findAllHomework();
        Homework helper = new Homework("101", "Foarte usor", 9, 8);
        for (Homework home : homes) {
            if(home.getID().equals("100")) {
                helper = home;
            }
        }

        assertEquals(helper.getID(), "100");
        assertEquals(helper.getDescription(), "Foarte greu");
        assertEquals(helper.getDeadline(), 9);
        assertEquals(helper.getStartline(), 7);
        serviceBefore.deleteHomework("100");
    }

    @Test
    void addHomework2() {
        serviceBefore.saveHomework("100", "Foarte greu", 9, 7);

        Iterable<Homework> homes = serviceBefore.findAllHomework();
        Homework helper = new Homework("101", "Foarte usor", 9, 8);
        for (Homework home : homes) {
            if(home.getID().equals("100")) {
                helper = home;
            }
        }

        assertEquals(helper.getID(), "100");
        assertEquals(helper.getDescription(), "Foarte greu");
        assertEquals(helper.getDeadline(), 9);
        assertEquals(helper.getStartline(), 7);
        serviceBefore.deleteHomework("100");

        homes = serviceBefore.findAllHomework();
        helper = new Homework("101", "Foarte usor", 9, 8);
        for (Homework home : homes) {
            if(home.getID().equals("100")) {
                helper = home;
            }
        }

        assertEquals(helper.getID(), "101");
        assertEquals(helper.getDescription(), "Foarte usor");
        assertEquals(helper.getDeadline(), 9);
        assertEquals(helper.getStartline(), 8);
    }

    @Test
    void addHomework3() {
        Iterable<Homework> homes = serviceBefore.findAllHomework();
        int counter1 = 0;
        for (Homework home: homes) {
            counter1++;
        }
        serviceBefore.saveHomework("102", "Foarte greu", 9, 7);
        serviceBefore.saveHomework("102", "Foarte greu", 9, 7);
        homes = serviceBefore.findAllHomework();
        int counter2 = 0;
        for (Homework home: homes) {
            counter2++;
        }
        assertEquals(counter2, counter1 + 1);
        serviceBefore.deleteHomework("102");
    }
}