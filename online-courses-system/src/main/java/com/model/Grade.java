package com.model;

public class Grade {
   // private String lessonName;    // Название урока
    private double score;         // Оценка
    private String comment;       // Комментарий
    private String date;          // Дата выставления

    public Grade(double score,   String comment) {
        this.score = score;
        this.comment = comment;
        this.date = java.time.LocalDate.now().toString();
    }

    // геттеры
    public double getScore() {
        return score;
    }
    public String getComment() {
        return comment;
    }
    public String getDate() {
        return date;
    }

    // сеттеры
    public void setScore(double score) {
        this.score = score;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    //вывод информациии об оценке
}
