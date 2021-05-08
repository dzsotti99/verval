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

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

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

    @AfterEach
    void tearDown() {
        System.out.println("Test done, GG.");
    }

    @Test
    void findAllStudents() {
        Iterator<Student> students = serviceBefore.findAllStudents().iterator();
        assertTrue(students.hasNext());
        Student current = students.next();
        assertEquals(current.getID(), "2");
        assertEquals(current.getName(), "Maria");
        assertEquals(current.getGroup(), 222);
        assertTrue(students.hasNext());
        current = students.next();
        assertEquals(current.getID(), "4");
        assertEquals(current.getName(), "Ion");
        assertEquals(current.getGroup(), 227);
        assertFalse(students.hasNext());

        serviceBefore.saveStudent("5", "Jacobson", 533);

        students = serviceBefore.findAllStudents().iterator();
        assertTrue(students.hasNext());
        current = students.next();
        assertEquals(current.getID(), "2");
        assertEquals(current.getName(), "Maria");
        assertNotEquals(current.getName(), "Ion");
        assertEquals(current.getGroup(), 222);
        assertTrue(students.hasNext());
        current = students.next();
        assertEquals(current.getID(), "4");
        assertEquals(current.getName(), "Ion");
        assertEquals(current.getGroup(), 227);
        assertTrue(students.hasNext());
        current = students.next();
        assertEquals(current.getID(), "5");
        assertEquals(current.getName(), "Jacobson");
        assertEquals(current.getGroup(), 533);
        assertFalse(students.hasNext());

        serviceBefore.deleteStudent("5");
    }

    @Test
    void findAllHomework() {
        Collection<Homework> homeworks = StreamSupport.stream(serviceBefore.findAllHomework().spliterator(), false).collect(Collectors.toList());

        assertEquals(homeworks.size(), 3);

        serviceBefore.saveHomework("10", "Nenorocire mare.", 10, 9);

        homeworks = StreamSupport.stream(serviceBefore.findAllHomework().spliterator(), false).collect(Collectors.toList());

        assertEquals(homeworks.size(), 4);

        serviceBefore.deleteHomework("10");

        homeworks = StreamSupport.stream(serviceBefore.findAllHomework().spliterator(), false).collect(Collectors.toList());

        assertEquals(homeworks.size(), 3);
    }

    @Test
    void findAllGrades() {

    }

    @Test
    void saveStudent() {
    }

    @Test
    void saveHomework() {
    }

    @Test
    void saveGrade() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void deleteHomework() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void updateHomework() {
    }

    @Test
    void extendDeadline() {
    }

    @Test
    void createStudentFile() {
    }
}