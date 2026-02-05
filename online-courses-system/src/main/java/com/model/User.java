package com.model;

public  class User {
    private String  name;
    private String  email;
    private String  password;

    public User (String  name, String  email,String  password){
        this.name=name;
        this.email=email;
        this.password=password;
    }

    //геттеры
    public  String getName(){
        return name;
    }

    public  String getEmail(){
        return email;
    }


    public  String getPassword(){
        return password;
    }

    // сеттеры
    public void setName(String name){
        this.name=name;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public void setPassword(String password){
        this.password=password;
    }


    //другие методы

    //проверка пороля
    public boolean passwordCheck(String entered_password){
        return this.password.equals(entered_password);
    }
    // вывод информации
    public void informationOutput(){
        System.out.println("Имя пользователя: "+name+ "%n Email:"+ email);
    }

}
