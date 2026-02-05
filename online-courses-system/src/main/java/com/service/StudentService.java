package com.service;

import com.model.Course;
import com.model.Lesson;
import com.model.Teacher;
import com.storage.Database;

public class StudentService {
    //вывод имён преподавателей с конкретной специальностью
    public static void teacherNameLisBySpecialisation(String specialisation){
        System.out.println("Список преподавателей со специализацией "+specialisation+":");
        for (Teacher teacher: Database.teachers){
            if(teacher.getSpecialisation().contains(specialisation)){
                System.out.println(teacher.getName());
            }
        }
    }

    //вывод имён преподавателей ведущих курс с определённым названием
    public static void teacherNameLisByCourse(String courseName){
        System.out.println("Список преподавателей ведущих курс "+courseName);
        for (Course course: Database.courses){
            if(course.getName_course().equals(courseName)){
                System.out.println(course.getName_teacher());
            }
        }
    }

    //вывод всех уроков указанного курса указанного преподавателя тип которых соответствует указанному
    public static void lessonNameListByType(Course course, String type){
        System.out.println("Список уроков курса "+course.getName_course()+", которые имеют тип "+type+":");
        if(Database.courses.contains(course)){
            for (Lesson lesson: course.getLesson_list()){
                if (lesson.getType().equals(type)){
                    System.out.println(lesson.getName_lession());
                }
            }
        }
    }

    // поиск учителя по имени
    public static Teacher findTeacherByName(String teacherName){
        for (Teacher teacher:Database.teachers){
            if(teacher.getName().equals(teacherName)){
                return  teacher;
            }
        }
        System.out.println("Учитель в системе не найден");
        return null;
    }

}
