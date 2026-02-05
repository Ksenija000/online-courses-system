package com.model;

import java.util.ArrayList;
import java.util.List;

public class Course
{
    private  String name_course;// название курса
    private  String name_teacher;// имя преподавателя
    private List<Lesson> lesson_list;//список уроков
    private List<Student> students_list;//список студентов


    public Course(String name_course, String name_teacher){
        this.name_course=name_course;
        this.name_teacher=name_teacher;
        this.lesson_list=new ArrayList<>();
        this.students_list=new ArrayList<>();
    }

    //добавление урока
    public  void  add_lesson_list(Lesson lesson)
    {
        this.lesson_list.add(lesson);
    }
   //добавление студента
    public  void  add_students_list(Student student)
    {
        this.students_list.add(student);
    }


   //геттеры
    public  String getName_course()
    {
        return name_course;
    }

    public  String getName_teacher()
    {
        return name_teacher;
    }

    //список уроков на курсе
    public  List<Lesson> getLesson_list()
    {
        return lesson_list;
    }
    //список студентов на курсе
    public  List<Student> getStudent_list()
    {
        return students_list;
    }

    //**количество уроков на курсе
    public  int getLessonListCount()
    {
        return lesson_list.size();
    }

    //**количество студеннтов на курсе
    public  int getStudent_listCount()
    {
        return students_list.size();
    }


    // сеттеры
    public void setName_course(String name_course){
        this.name_course=name_course;
    }

    public void setName_teacher(String name_teacher){
        this.name_teacher=name_teacher;
    }

    // **вывод информации
    public void informationOutput(){
        System.out.println("Название курса: "+getName_course()+ "%n Преподаватель:"+ getName_teacher());
        System.out.println("Количество уроков курса:");
        System.out.println(getLessonListCount());
        System.out.println("Список уроков курса:");
        for (Lesson lesson : getLesson_list()) {
            System.out.println(lesson.getName_lession());
        }
        System.out.println("Количество студентов курса:");
        System.out.println(getStudent_listCount());
        System.out.println("Список студентов курса:");
        for (Student student : getStudent_list()) {
            System.out.println(student.getName());
        }
    }
}
