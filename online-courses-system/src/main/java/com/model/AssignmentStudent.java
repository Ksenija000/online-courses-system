package com.model;

import java.time.LocalDateTime;

public class AssignmentStudent {
    private Student student;        //студент, который сдаёт задание
    private AssignmentLesson lesson; //к какому заданию
    private String answerText;      //текстовый ответ
    private String attachedFilePath; //путь к прикрепленному файлу
    private LocalDateTime submittedAt; //когда отправлено
    private String status;          //статус: отправлено, проверено

    public AssignmentStudent(Student student, AssignmentLesson lesson, String answerText, String attachedFilePath) {
        this.student = student;
        this.lesson = lesson;
        this.answerText = answerText;
        this.attachedFilePath = attachedFilePath;
        this.submittedAt = LocalDateTime.now();
        this.status = "отправлено";
    }

    // геттеры
    public Student getStudent() {
        return student;
    }
    public AssignmentLesson getLesson() {
        return lesson;
    }
    public String getAnswerText() {
        return answerText;
    }
    public String getAttachedFilePath() {
        return attachedFilePath;
    }
    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
    public String getStatus() {
        return status;
    }

    // сеттеры
    public void setStatus(String status) {
        this.status = status;
    }

    public void informationOutput(){
        System.out.println("Студент который сдаёт задание: "+student.getName());
        System.out.println("Урок по которому сдаёт задание: "+lesson.getName_lession());
        System.out.println("Текстовый ответ: "+answerText);
        System.out.println("Прикреплённый файл: "+attachedFilePath);//так как приложение пока консольное будет просто выведен путь
        System.out.println("Отправлено: "+submittedAt);
    }
}
