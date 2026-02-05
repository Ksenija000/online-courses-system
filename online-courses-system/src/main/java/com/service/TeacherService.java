package com.service;

import com.model.*;
import com.storage.Database;

import java.util.List;

public class TeacherService {

    // поиск учителя по имени

    // общий метод для проверки
    private static boolean validateTeacherCourseStudent(Teacher teacher, Course course, Student student) {
        //проверка существования учителя в системе
        if (!Database.teachers.contains(teacher)) {
            System.out.println("Преподаватель " + teacher.getName() + " не найден в системе");
            return false;
        }
        //проверка принадлежности курса учителю
        if (!teacher.getCreated_course().contains(course)) {
            System.out.println("Курс " + course.getName_course() + " не принадлежит преподавателю " + teacher.getName());
            return false;
        }
        //проверка записи студента на курс студента (не для всех методов нужна)
        if (student != null && !course.getStudent_list().contains(student)) {
            System.out.println("Студент " + student.getName() + " не записан на курс " + course.getName_course());
            return false;
        }
        return true;
    }
    //без проверки студента
    private static boolean validateTeacherCourse(Teacher teacher, Course course) {
        return validateTeacherCourseStudent(teacher, course, null);
    }
    // Проверка существования учителя
    public static boolean teacherExists(Teacher teacher) {
        return validateTeacherCourseStudent(teacher, null, null);
    }

    //***количество пройденных уроков на определённом курсе определённым студентом
    public static int lessonCountCourseStudent(Teacher teacher,Student student, Course course){
        if (!validateTeacherCourseStudent(teacher, course, student)) return 0;

        return student.completeLessonCount(course);
    }

    //список пройденных уроков на определённом курсе данного преподавателя определённым студентом
    public static List<Lesson> lessonListCourseStudentForTeacher(Teacher teacher, Student student, Course course) {
        if (!validateTeacherCourseStudent(teacher, course, student)) return null;

        return student.completeLessonListCourse(course);
    }

    //количество и список пройденных уроков на определённом курсе данного преподавателя каждом студентом+ прогресс
    public static void completeLessonCourseEveryStudentForTeacher(Teacher teacher, Course course) {
        if (!validateTeacherCourse(teacher, course)) return;

        System.out.println("***Информация о прохождении курса " + course.getName_course() + " каждым студентом:");
        for (Student student : course.getStudent_list()) {
            System.out.println("///Студент " + student.getName() + ":");
            student.completeLessonListSpecificCourse(course);
        }
    }

    //количество и список пройденных уроков на определённом курсе данного преподавателя определённым студентом + прогресс
    public static void completeLessonCourseStudentForTeacher(Teacher teacher,Course course, Student student) {
        if (!validateTeacherCourseStudent(teacher, course, student)) return;

        System.out.println("***Информация о прохождении курса " + course.getName_course() + " студентом " + student.getName() + ": ");
        student.completeLessonListSpecificCourse(course);
    }


    //удаление оценки студента за конкретный урок конкретного курса конкретного преподавателя
    public static void removeStudentGradeForTeacher(Teacher teacher, Student student, Course course, String lessonName) {
        if (!Database.teachers.contains(teacher)) {
            System.out.println("Преподаватель " + teacher.getName() + " не найден в системе");
            return;
        }
        ProgressCourse progress = teacher.validateGradeOperation(student, course,lessonName, "удалять оценки");
        if (progress == null) {
            return;
        }

        boolean removed = progress.removeGrade(lessonName);
        progress.calculateGPA();
        if (removed) {
            System.out.println("Оценка за урок " + lessonName + " удалена у студента " + student.getName());
        } else {
            System.out.println("Оценка за урок " + lessonName + " не найдена у студента " + student.getName());
        }
    }

    /// ///////////////////////////////////////

    //вывод всех студентов со средним балом за указанный курс указанного преподавателя из диапазона (от до)
    public static void studentNameListByGPA(Course course, double from , double to){
        System.out.println("Список студентов со средни баллом за курс "+course.getName_course() +" от "+from+ " до "+ to+":");
        for (ProgressCourse progressCourse: Database.progressCourses){
            if(progressCourse.getCourse_progress().equals(course)){
                if((progressCourse.getGPA()>=from)&&(progressCourse.getGPA()<=to)){
                    System.out.println(progressCourse.getName_student_progress());
                }
            }
        }
    }

    //вывод студентов у которых процент завершения указанного курса указанного преподавателя из диапазона (от до)
    public static void studentNameListByPercent(Course course, double from , double to){
        System.out.println("Список студентов процент завершения курса "+course.getName_course() +" от "+from+ " до "+ to+":");
        for (ProgressCourse progressCourse: Database.progressCourses){
            if(progressCourse.getCourse_progress().equals(course)){
                if((progressCourse.percentCourse()>=from)&&(progressCourse.percentCourse()<=to)){
                    System.out.println(progressCourse.getName_student_progress());
                }
            }
        }

    }

    //вывод всех студентов которые завершили/не завершили (передача значения как параметра) конкретный курс конкретного преподавателя
    public static void studentNameListByCompleted_course(Course course, boolean completed){
        if (!Database.courses.contains(course)){
            System.out.println("Курс "+course.getName_course() +" в системе не найден");
            return;
        }

        if(completed){ System.out.println("Список студентов курса " + course.getName_course() + ", которые завершили его:");}
        else { System.out.println("Список студентов курса " + course.getName_course() + ", которые не завершили его:");}

        for (ProgressCourse progressCourse: Database.progressCourses){
            if(progressCourse.getCourse_progress().equals(course)){
                if (completed) {
                    if(progressCourse.getCompleted_course()) {
                        System.out.println(progressCourse.getName_student_progress());
                    }
                }
            }
            else {
                if (!progressCourse.getCompleted_course()){
                    System.out.println(progressCourse.getName_student_progress());
                }
            }
        }
    }

    // поиск студента по имени
    public static Student findStudentByName(String studentName){
        for (Student student:Database.students){
            if(student.getName().equals(studentName)){
                return  student;
            }
        }
       // System.out.println("Такой студент в системе не зарегистрирован");
        return null;
    }

    //список кусов конкретного преподавателя
    public static List<Course> getCreated_courseForTeacher(Teacher teacher){
        if(Database.teachers.contains(teacher)) {
            return teacher.getCreated_course();
        }
        else {System.out.println("Такой учитель в системе не зарегистрирован");
            return null; }
    }

    /// //////////////////////////////задания

    //удаление материала из лекции
    public static void removeMaterialToLecture(Teacher teacher, Course course, String lessonName, LearningMaterial material) {
        if(!teacherExists(teacher)){
            return;
        }

        if (!teacher.getCreated_course().contains(course)) {
            System.out.println("Курс не принадлежит преподавателю");
            return;
        }
        Lesson lesson =teacher.findLessonByNameForCourse(course, lessonName);
        if (lesson==null){return;}

        if (lesson instanceof LectureLesson) {
            LectureLesson lectureLesson=(LectureLesson) lesson;
            if (!lectureLesson.getMaterials().contains(material)){
                System.out.println("Такого материала в лекции нет");
                return;
            }
            lectureLesson.removeMaterial(material);
            System.out.println("Материал удалён из лекции");
        } else {
            System.out.println("Это не лекция!");
        }
    }

    //поиска материала в лекции по названию материала
    public static LearningMaterial findMaterial(Teacher teacher,Course course, String lessonName,String materialTitle) {
        if (!teacherExists(teacher)) {
            return null;
        }
        if (!teacher.getCreated_course().contains(course)) {
            System.out.println("Курс не принадлежит преподавателю");
            return null;
        }
        Lesson lesson = teacher.findLessonByNameForCourse(course, lessonName);
        if (lesson == null) {
            return null;
        }

        if (lesson instanceof LectureLesson) {
            LectureLesson lectureLesson = (LectureLesson) lesson;
            for (LearningMaterial learningMaterial:lectureLesson.getMaterials()){
                if(learningMaterial.getTitle().equals(materialTitle)){
                    return learningMaterial;
                }
            }
        }
        System.out.println("Такого материала в лекции нет");
        return null;
    }
    /// //////////////////////////////задания конец

}
