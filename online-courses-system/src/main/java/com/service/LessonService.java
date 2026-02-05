package com.service;

import com.model.Course;
import com.model.Grade;
import com.model.Lesson;
import com.model.ProgressCourse;
import com.model.Teacher;

import com.storage.Database;

public class LessonService {
    //удаление урока для преподавателя (только из своих курсов)
    public static void deleteLessonForTeacher(String courseName, Teacher teacher, String lessonName) {
        Course course =CourseService.findCourseByNameAndTeacher(courseName, teacher.getName());

        if (course == null) {
            System.out.println("Курс " + courseName + " не найден или не принадлежит вам");
            return;
        }

        Lesson lesson = findLessonInCourse(course, lessonName);
        if (lesson == null) {
            System.out.println("Урок " + lessonName + " не найден в курсе " + courseName);
            return;
        }

        deleteLesson(course, lesson);
    }

    // Удаление урока для администратора (из любого курса)
    public static void deleteLessonForAdmin(String courseName, Teacher teacher, String lessonName) {
        // Проверяем существование учителя (как в CourseService)
        if (!TeacherService.teacherExists(teacher)) {
            System.out.println("Преподаватель " + teacher.getName() + " не найден");
            return;
        }

        Course course = CourseService.findCourseByNameAndTeacher(courseName, teacher.getName());
        if (course == null) {
            System.out.println("Курс " + courseName + " преподавателя " + teacher.getName() + " не найден");
            return;
        }

        Lesson lesson = findLessonInCourse(course, lessonName);
        if (lesson == null) {
            System.out.println("Урок " + lessonName + " не найден в курсе " + courseName);
            return;
        }

        deleteLesson(course, lesson);
    }
    // Поиск урока в курсе по названию
    public static Lesson findLessonInCourse(Course course, String lessonName) {
        if (!Database.courses.contains(course)) {
            System.out.println("Курс " + course.getName_course() + " не найден в системе");
            return null ;
        }
        for (Lesson lesson: course.getLesson_list()){
            if (lesson.getName_lession().equals(lessonName)){
                return lesson;
            }
        }
        System.out.println("Урок " + lessonName + " пока не существует в курсе "+course.getName_course());
        return null;
    }


    // Общая логика удаления урока
    private static void deleteLesson(Course course, Lesson lesson) {
        String courseName = course.getName_course();
        String lessonName = lesson.getName_lession();

        boolean removeLessonFromCourse = course.getLesson_list().remove(lesson);

        if (!removeLessonFromCourse) {
            System.out.println("При удалении урока из курса произошла ошибка");
            return;
        }

        removeLessonFromStudentProgress(course, lesson);

        removeGradesForLesson(course, lessonName);

        System.out.println("Урок " + lessonName + " удален из курса " + courseName);

    }

    //удаление урока из прогресса всех студентов
    private static void removeLessonFromStudentProgress(Course course, Lesson lesson) {
        for (ProgressCourse progress : Database.progressCourses) {
            if (progress.getCourse_progress().equals(course)) {
                progress.getLesson_progress().remove(lesson);
                recalculateCompletionCourse(progress);
            }
        }
    }
    //пересчет завершения курса после удаления урока
    private static void recalculateCompletionCourse(ProgressCourse progress) {
        Course course = progress.getCourse_progress();

        boolean nowCompleted = !course.getLesson_list().isEmpty() && progress.getLesson_progress().size() == course.getLesson_list().size();

        progress.setCompleted_course(nowCompleted);

    }

    // Удаление оценок за урок у всех студентов
    private static void removeGradesForLesson(Course course, String lessonName) {
        for (ProgressCourse progress : Database.progressCourses) {
            if (progress.getCourse_progress().equals(course)) {
                        progress.removeGrade(lessonName);
                        progress.calculateGPA();
                }
            }
        }

    /// /////////////////////////////////////////////
    public static void  LessonInf(Lesson lesson, Course course){
        if (Database.courses.contains(course)){
            if(course.getLesson_list().contains(lesson)){
                lesson.informationOutput();
            }
        }
    }
    }

