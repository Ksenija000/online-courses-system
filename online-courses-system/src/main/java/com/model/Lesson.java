package com.model;

public   class Lesson {
    private String name_lession;// название урока
    private  String content;// содержимое (материал)
    private  String type; //тип урока

    public Lesson ( String  name_lession, String  content,  String type){
        this.name_lession=name_lession;
        this.content=content;
        this.type=type;
    }

    //вывод информации о контенте
    public void displayContent(){
        informationOutput();
    };

    //геттеры
    public  String getName_lession(){
        return name_lession;
    }

    public  String getType(){
        return type;
    }

    public  String getContent(){
        return content;
    }

    //сеттеры

    public void setName_lession(String name_lession){
        this.name_lession=name_lession;
    }

    public void setContent(String content){
        this.content=content;
    }

    public void setType(String type){
        this.type=type;
    }

    // **вывод информации
    public void informationOutput(){
        System.out.println("Название урока: "+getName_lession()+ "%n содержимое (материал):"+ getContent()+"%n тип урока:" +getType());

    }

}
