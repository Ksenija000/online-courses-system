package com.service;

import com.model.Admin;
import com.model.Student;
import com.model.Teacher;
import com.model.User;
import com.storage.Database;

import java.util.Scanner;

public class Authorization {
    private Scanner scanner = new Scanner(System.in);
    //авторизация
    public User authorizationUser () {
        System.out.println("Авторизация");
        //ввод электронной почты
        System.out.print("Введите email: ");
        String email = scanner.nextLine();

        //валидация электронной почты
        if (!Registration.emailValidation(email)){return null;}

        // ввод пароля
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        //валидация пароля
        if (!Registration.passwordValidation(password)){return null;}

        //проверка существования в бд в списке с такой ролью пользователя с такой почтой и паролем
        User user = findUserByEmail(email);


        if (user == null) {
            System.out.println("Пользователя с таким email в системе не существует");
            return null;
        }
         if(!user.getPassword().equals(password)){
             System.out.println("Неверный пароль");
             return null;
         }
        System.out.println(user.getName()+" вошёл в систему");
       return user;
    }

    // поиск пользователя по email
    private User findUserByEmail(String email) {
        for (User user : Database.users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

}
