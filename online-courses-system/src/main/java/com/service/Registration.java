package com.service;

import com.model.Admin;
import com.model.Student;
import com.model.Teacher;
import com.model.User;
import com.storage.Database;

import java.util.Scanner;

public class Registration {
    private Scanner scanner = new Scanner(System.in);
    private final String adminCode = "12admin21"; //код для админов


    //регистрация
    public  void registration() {
        System.out.println("Регистрация");
        //вод роли (выбор из предложенных)
        System.out.println("Выберите роль в системе: 1. Преподаватель, 2. Студент, 3. Администратор");
        String roleNumber = scanner.nextLine();

        String role = "";
        if (roleNumber.equals("1")) role = "teacher";
        else if (roleNumber.equals("2")) role = "student";
        else if (roleNumber.equals("3")) role = "admin";
        else {
            System.out.println("Неверный выбор роли!");
            return;
        }
        //если админ ввести код для регистрации в качестве администратора
        //проверка введённого кода
        if (role.equals("admin")) {
            System.out.print("Введите код регистрации администратора: ");
            String code = scanner.nextLine();
            if (!code.equals(adminCode)) {
                System.out.println("Неверный код! Регистрация отменена.");
                return;
            }
        }

        //ввод имени
        System.out.println("Введите имя: ");
        System.out.println("Минимум 2 символа");
        String name = scanner.nextLine();

        //валидация имени
        if (!nameValidation(name)){return;}

        // проверка существования в Database в списке с такой ролью такого имени (требование системы)
        if (isNameExistsInRole(name, role)) {
            System.out.println("Пользователь с таким именем уже существует в этой роли!");
            return;
        }

        //ввод электронной почты
        System.out.print("Введите email: ");
        String email = scanner.nextLine();

        //валидация электронной почты
        if (!emailValidation(email)){return;}

        // проверка существования в Database в списках пользователей такой почты
        if (isEmailExists(email)!=null) {
            System.out.println("Этот email уже зарегистрирован!");
            return;
        }

        // ввод пароля
        System.out.println("Введите пароль: ");
        System.out.println("Требования: длина 8-32 символа, должен содержать минимум 1 строчный и 1 прописной символ, минимум 1 цифру, минимум 1 спецсимвол (!@#$%^&*),  отсутствие пробелов, буквы только английского алфавита ");
        String password = scanner.nextLine();

        //валидация пароля
        if (!passwordValidation(password)){return;}

        // Подтверждение пароля
        System.out.println("Подтвердите пароль: ");
        String confirmPassword = scanner.nextLine();

        //проверка подтверждения
        if (!password.equals(confirmPassword)) {
            System.out.println("Пароли не совпадают!");
            return;
        }

        // если введённые данные соответствуют всем условиям создание объекта пользователя указанного типа и сохранение в бд в списке с соответствующей ролью
       // User newUser =new  User(name, email, password);
       // Database.addUser(newUser);
        if (role.equals("teacher")){
            Teacher teacher =new  Teacher(name, email, password);
            Database.addUser(teacher);
        }
        if (role.equals("student")){
            Student student =new  Student(name, email, password);
            Database.addUser(student);
        }
        if (role.equals("admin")){
            Admin admin =new  Admin(name, email, password);
            Database.addUser(admin);
        }

        System.out.println("Регистрация произведена успешно! Добро пожаловать, " + name);

    }

   private boolean nameValidation(String name){
        if (name.length() < 2) {
            System.out.println("Имя должно содержать не менее 2 символов!");
            return false;
        }
        String regex = "^[a-zA-Zа-яА-ЯёЁ0-9]+$";
        if(!name.matches(regex)) {
            System.out.println("Имя должно содержать только буквы русского и английского алфавита и цифры от 0 до 9");
            return false;
        }
        return true;
    }

    // Проверяет, есть ли имя в списке пользователей конкретной роли
    private boolean isNameExistsInRole(String name, String role) {
        if (((role.equals("teacher"))&&(StudentService.findTeacherByName(name)==null))||
                ((role.equals("student"))&&(TeacherService.findStudentByName(name)==null))||
                ((role.equals("admin"))&&(AdminService.findAdminByName(name)==null)))
                {
                return false;
        }
        return true;
    }

    public static  boolean emailValidation(String email){
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if(!email.matches(regex)) {
            System.out.println("Некорректный email");
            return false;
        }
        return true;
    }

    // Проверяет email во всех пользователях
    public static User isEmailExists(String email) {
        for (User user:Database.users){
            if (user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    public static boolean passwordValidation(String password){
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,32}$";
        if(!password.matches(regex)) {
            System.out.println("Некорректный пароль!");
            return false;
        }
        return true;
    }

}
