package com.model;

import java.util.ArrayList;
import java.util.List;

public class AssignmentLesson extends Lesson {
    private String taskDescription;  //условие задания
    private String deadline;         //срок сдачи
    private int maxScore;           //максимальный балл
    private List<AssignmentStudent> assignmentStudent = new ArrayList<>(); //работы студентов

    public AssignmentLesson(String name_lession, String taskDescription, String deadline, int maxScore) {
        super(name_lession, "", "Задание");
        this.taskDescription = taskDescription;
        this.deadline = deadline;
        this.maxScore = maxScore;
    }

    //контент задания
    public void displayContent() {
        System.out.println("Задание: " + taskDescription);
        System.out.println("Дедлайн: " + deadline);
        System.out.println("Макс. балл: " + maxScore);
    }

    //добавление работы студента
    public void addAssignmentStudent(AssignmentStudent assignment) {
        assignmentStudent.add(assignment);
    }

    //удаление работы студента
    public void removeSAssignmentStudent(AssignmentStudent assignment) {
        assignmentStudent.remove(assignment);
    }

    //геттеры
    public String getTaskDescription() {
        return taskDescription;
    }
    public String getDeadline() {
        return deadline;
    }
    public int getMaxScore() {
        return maxScore;
    }
    public List<AssignmentStudent> getAssignmentStudent() {
        return assignmentStudent;
    }

    //сеттеры
    public void setTaskDescription(String taskDescription) {
      this.taskDescription=taskDescription;
    }
    public void setDeadline(String deadline) {
        this.deadline=deadline;
    }
    public void setMaxScore(int maxScore) {
        this.maxScore=maxScore;
    }

}
