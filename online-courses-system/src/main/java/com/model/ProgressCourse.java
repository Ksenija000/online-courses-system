package com.model;


import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ProgressCourse {
    private Course course_progress; //курсс, по которому определянется прогресс
    private String name_student_progress; //имя студента, для которого определянется прогресс
    private  List<Lesson>  lesson_progress; //список пройденных уроков
   // private List<Grade> grades; //список оценок
   private Map<String, Grade> grades; // словарь оценок: ключ - название урока, значение - оценка
    private double gpa; // средний балл
    private boolean completed_course; // завершён ли курсс

    public ProgressCourse (Course course_progress,String name_student_progress ){
        this.course_progress=course_progress;
        this.name_student_progress=name_student_progress;
        this.lesson_progress=new ArrayList<>();
        this.grades = new HashMap<>();
        this.gpa=0.0;
        this.completed_course=false;
    }

    //метод для ометки прохождения урока
    public void completeLesson (Lesson lesson){
        if(course_progress.getLesson_list().contains(lesson) && !lesson_progress.contains(lesson)) {
            lesson_progress.add(lesson);
        }

        if (lesson_progress.size()==course_progress.getLesson_list().size()){
            this.completed_course=true;
            System.out.println("Курс "+course_progress.getName_course()+" завершён!");

        }
    }

    //добавление оценки
    //если оценка за этот урок уже есть то замена (удаление и выставление новой)
    public void addGrade(String lessonName, double score, String comment) {
            grades.put(lessonName, new Grade(score, comment));
    }

    // Удаление оценки по названию урока
    public boolean removeGrade(String lessonName) {
        return grades.remove(lessonName) != null;
    }

    // расчет среднего балла
    public double calculateGPA() {
        if (grades.isEmpty()) {
            gpa=0.0;
            return gpa;
        }

        double sum = 0.0;
        for (Grade grade : grades.values()) {
            sum += grade.getScore();
        }
        gpa=sum / grades.size();
        return gpa;
    }

    // Получение всех оценок
    public void getGrades() {
        for (Grade grade : grades.values()) {
            System.out.print(grade.getScore());
        }
    }

    // Получение оценки за конкретный урок
    public Double getGradeForLesson(String lessonName) {
        Grade grade = grades.get(lessonName);
        if (grade != null) {
                return grade.getScore();
            }
        System.out.println("За данный урок отметки нет");
        return null;
    }

    //процент прохождения курса
    public double percentCourse(){
        if (course_progress.getLesson_list().isEmpty())
        {
            return 0;
        }
        return (double) lesson_progress.size()/course_progress.getLesson_list().size()*100;
    }

    //количество пройденных уроков
    public  int completeLessonCount(){
        return lesson_progress.size();
    }

    //количество уроков в курсе
    public  int LessonCount(){
        return course_progress.getLesson_list().size();
    }

    //геттеры
    public  Course getCourse_progress(){
        return course_progress;
    }
    public  String getName_student_progress(){
        return name_student_progress;
    }

    public  List<Lesson>  getLesson_progress(){
        return lesson_progress;
    }

    public  double getGPA(){
        return gpa;
    }

    public  boolean getCompleted_course(){
        return completed_course;
    }
    //Сеттеры
    public void setCompleted_course(boolean completed) {
        this.completed_course = completed;
    }

}
