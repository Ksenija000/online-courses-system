package com.storage;

import  com.model.*;
import java.util.ArrayList;
import  java.util.List;

public class Database {

    //нечто вроде бд, где будут храниться данные в списках

    public static List<User> users = new ArrayList<>();

    public static List<Student> students = new ArrayList<>();
    public static List<Teacher> teachers = new ArrayList<>();
    public static List<Admin> admins = new ArrayList<>();

    public static List<Course> courses = new ArrayList<>();
    //public static List<Lesson> lessons = new ArrayList<>(); // не имеет смысла так как список уроков хранится в каждом классе из списака курсов courses
    public static List<ProgressCourse> progressCourses = new ArrayList<>();
    //добавление пользователя в список
    public static  void  addUser(User user){
        users.add(user);

        if(user instanceof Student){
            students.add((Student) user);
        }
        else if (user instanceof Teacher){
            teachers.add((Teacher) user);
        }
        else if (user instanceof Admin){
            admins.add((Admin) user);
        }
    }

}
