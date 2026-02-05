package com.service;

import com.model.*;
import com.storage.Database;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    //создание курса
    public static Course createCourse(Teacher teacher, String name_course) {
        if (courseExistsForTeacher(name_course, teacher.getName())) {
            System.out.println("Курс с названием '" + name_course + "' уже существует у преподавателя " + teacher.getName());
            return null;
        }

        Course new_course = new Course(name_course, teacher.getName());
        Database.courses.add(new_course);
        System.out.println("Курс " + name_course + " создан преподавателем " + teacher.getName());
        return new_course;
    }

    // Проверка существования курса у учителя
    private static boolean courseExistsForTeacher(String courseName, String teacherName) {
        for (Course course : Database.courses) {
            if (course.getName_course().equals(courseName) &&
                    course.getName_teacher().equals(teacherName)) {
                return true;
            }
        }
        return false;
    }

    // Удаление курса для администратора (по названию и учителю)
    public static void deleteCourseForAdmin(String courseName, Teacher teacher) {
        // Проверяем существование учителя
        if (!TeacherService.teacherExists(teacher)) {
            System.out.println("Преподаватель " + teacher.getName() + " не найден");
            return;
        }
        Course course = findCourseByNameAndTeacher(courseName,  teacher.getName());
        if (course != null ) {
            deleteCourse(course);
            System.out.println("Курс " + courseName + " преподавателя " +  teacher.getName() + " удалён");
        } else {
            System.out.println("Курс не найден");
        }
    }

    // Удаление курса для учителя (только по названию, только свои курсы)
    public static void deleteCourseForTeacher(String courseName, Teacher teacher) {
        Course course = findCourseByNameAndTeacher(courseName, teacher.getName());
        if (course != null && course.getName_teacher().equals(teacher.getName())) {
            deleteCourse(course);
            System.out.println("Курс " + courseName + " удалён");
        } else if (course != null) {
            System.out.println("Вы можете удалять только свои курсы");
        } else {
            System.out.println("Курс не найден");
        }
    }

    // Общая логика удаления курса
    private static void deleteCourse(Course course) {
        Database.courses.remove(course);

        // Удаляем курс из списков регистрации студентов
        for (Student student : Database.students) {
            student.getRegistration_courses().remove(course);
        }

        // Удаляем прогресс по курсу
        Database.progressCourses.removeIf(progress_course ->
                progress_course.getCourse_progress().equals(course));
    }

    // Поиск курса по названию и учителю
    public static Course findCourseByNameAndTeacher(String courseName, String teacherName) {
        for (Course course : Database.courses) {
            if (course.getName_course().equals(courseName) &&
                    course.getName_teacher().equals(teacherName)) {
                return course;
            }
        }
        System.out.println("Курс не найден");
        return null;
    }

    /// ////////////////////////////////////



}
