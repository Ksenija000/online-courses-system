package com.service;

import java.util.Scanner;
import java.util.Random;

import com.model.User;

public class PasswordRecoveryService {
    private Scanner scanner = new Scanner(System.in);

    //метод восстановления пароля (пока с проверкой существования почты)
    public void recoverPassword() {
        System.out.println("восстановление пароля");

        //ввод электронной почты
        System.out.print("Введите ваш email: ");
        String email = scanner.nextLine();

        //валидация электронной почты
        if (!Registration.emailValidation(email)){return;}

        // проверка существования в бд в списках пользователей такой почты
        User user = Registration.isEmailExists(email);
        if (user==null){
            System.out.println("Пользователя с таким email ("+email+") не существует в системе");
            return;}

        // генерация временного пароля
        String tempPassword = generateTempPassword();

        //установка временного пароля
        user.setPassword(tempPassword);

        //вывод временного пароля
        System.out.println("Ваш временный пароль: " + tempPassword);
        System.out.println("Смените пароль после входа!");

    }

    // Генерирует простой временный пароль
    private String generateTempPassword() {
        Random random = new Random();
        String  specialChars = "!@#$%^&*";
        int randomNumber = random.nextInt((999 - 100) + 1) + 100;
        int randomIndex = random.nextInt(specialChars.length());
        return specialChars.charAt(randomIndex)+"User" + randomNumber;
    }

}
