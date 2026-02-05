package com.model;

import java.time.LocalDateTime;

public class LearningMaterial {
    private String title;       // название материала
    private String type;        // тип: pdf файл, видео, презентация, ссылка
    private String filePath;    // путь к файлу
    private String url;         // внешняя ссылка
    private LocalDateTime creationDate; //когда создан

    public LearningMaterial(String title, String type, String filePath, String url) {
        this.title = title;
        this.type = type;
        this.filePath = filePath;
        this.url = url;
        this.creationDate = LocalDateTime.now();
    }

    //геттеры
    public String getTitle() {
        return title;
    }
    public String getType() {
        return type;
    }
    public String getFilePath() {
        return filePath;
    }
    public String getUrl() {
        return url;
    }
    public LocalDateTime getCreatedAt() {
        return creationDate;
    }


    //сеттеры
    public void setTitle(String title) {
        this.title=title;
    }
    public void setType(String type) {
        this.type=type;
    }
    public void setFilePath(String filePath) {
        this.filePath=filePath;
    }
    public void setUrl(String url) {
        this.url=url;
    }

    public void informationOutput(){
        System.out.println("Название материала: "+getTitle());
        System.out.println("Тип материала: "+getType());
        System.out.println("Путь к файлу : "+getFilePath());//так как приложение пока консольное будет просто выведен путь
        System.out.println("Ссылка на материал: "+getUrl());
        System.out.println("Дата создания: "+getCreatedAt());
    }

}
