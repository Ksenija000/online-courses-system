package com.console;
import com.model.*;
import com.service.*;
import com.storage.Database;

import java.util.Scanner;

public class Main
{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        User user=null;

        while (true) {

            int block = 0;

            while (block == 0) {
                System.out.println("ГЛАВНОЕ МЕНЮ");
                System.out.println("    1. Регистрация");
                System.out.println("    2. Вход в систему");
                System.out.println("    3. Забыли пароль?");
                System.out.println("    4. Выход из программы");
                System.out.print("Выберите действие: ");

                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        try {
                        Registration registration = new Registration();
                        registration.registration();
                        } catch (Exception e) {
                            System.out.println("Ошибка при регистрации: " + e.getMessage());
                        }
                        break;
                    case "2":
                        try {
                        Authorization authorization = new Authorization();
                        user=authorization.authorizationUser();
                        if(user!=null){
                            block=1;
                        }
                        }
                    catch (Exception e) {
                        System.out.println("Ошибка при входе: " + e.getMessage());
                    }
                        break;
                    case "3":
                        try {
                        PasswordRecoveryService passwordRecoveryService = new PasswordRecoveryService();
                        passwordRecoveryService.recoverPassword();
                        } catch (Exception e) {
                            System.out.println("Ошибка восстановления пароля: " + e.getMessage());
                        }
                        break;
                    case "4":
                        System.out.println("До свидания!");
                        System.exit(0);
                    default:
                        System.out.println("Неверный выбор! Введите число от 1 до 4.");
                        continue;
                }
            }
            if(block==0){continue;}

            while (block == 1) {
                if (user instanceof Teacher) {
                    Teacher teacher = (Teacher) user;
                    System.out.println("Преподаватель: " + teacher.getName());
                    System.out.println("Выберите действие: ");
                    System.out.println("    1: Главное меню");
                    System.out.println("    2. Выход из программы");
                    System.out.println("    3. Создать курс");
                    System.out.println("    4. Создание лекции");
                    System.out.println("    5. Добавление материала к лекции");
                    System.out.println("    6. Удаление материала из лекции");
                    System.out.println("    7. Создание задания для студентов");
                    System.out.println("    8. Проверка работы студента");
                    System.out.println("    9. Список ваших кусов");
                    System.out.println("    10. Количество ваших кусов");
                    System.out.println("    11. Список ваших специальностей");
                    System.out.println("    12. Добавить специальность");
                    System.out.println("    13. Удалить специальность");
                    System.out.println("    14. Список ваших студентов");
                    System.out.println("    15. Количество ваших студентов");
                    System.out.println("    16. Количество уроков в указанном курсе (только для ваших курсов)");
                    System.out.println("    17. Список уроков в указанном курсе (только для ваших курсов)");
                    System.out.println("    18. Количество пройденных уроков на определённом курсе определённым студентом (только для ваших курсов и студентов)");
                    System.out.println("    19. Список пройденных уроков на определённом курсе определённым студентом (только для ваших курсов и студентов)");
                    System.out.println("    20. Информация о прохождении уроков на каждом курсе каждом студентом (только для ваших курсов)");
                    System.out.println("    21. Информация о прохождении уроков на определённом курсе каждом студентом (только для ваших курсов)");
                    System.out.println("    22. Информация о прохождении уроков на определённом курсе определённым студентом (только для ваших курсов и студентов)");
                    System.out.println("    23. Выставление оценки студенту (только для ваших студентов)");
                    System.out.println("    24. Удаление оценки студента (только для ваших студентов)");
                    System.out.println("    25. Информация о ваc");
                    System.out.println("    26. Информация об одном из ваших курсов");
                    System.out.println("    27. Информация об одном из ваших уроков");
                    System.out.println("    28. Информация об одном из ваших студентов");
                    System.out.println("    29. Удаление вашего курса");
                    System.out.println("    30. Удаление урока из вашего курса");
                    System.out.println("    31. Вывод студентов по среднему балу (только для ваших студентов)");
                    System.out.println("    32. Вывод студентов проценту завершения указанного курса (только для ваших студентов)");
                    System.out.println("    33. Вывод студентов,  которые завершили/не завершили конкретный курс (только для ваших студентов)");

                    System.out.print("Ваш выбор: ");

                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            System.out.println("Главное меню");
                            block=0;
                            break;
                        case "2":
                            System.out.println("Выход из программы");
                            System.out.println("До свидания!");
                            System.exit(0);
                        case "3":
                            try {
                            System.out.println("Создать курс");
                            System.out.print("Введите название курса: ");
                            String courseName = scanner.nextLine();
                            Course course=teacher.createdCourse(courseName);
                            }
                            catch (Exception e) {
                                System.out.println("Ошибка создания курса: " + e.getMessage());
                            }
                            continue;
                        case "4":
                            try {
                            System.out.println("Создание лекции");
                            System.out.print("Введите название курса: ");
                            String courseName4 = scanner.nextLine();
                           Course course4= teacher.findCourseByNameAndTeacher( courseName4);
                           if(course4==null){ block=1;
                               continue;}
                            System.out.print("Введите название урока: ");
                            String lessonName4 = scanner.nextLine();

                            if( LessonService.findLessonInCourse(course4,lessonName4)!=null){
                                System.out.print("Урок с таким названием уже существует");
                                continue;}

                            System.out.print("Введите описание контента: ");
                            String content = scanner.nextLine();
                            teacher.addLectureLesson( course4, lessonName4, content);
                            }
                            catch (Exception e) {
                                System.out.println("Ошибка создания лекции: " + e.getMessage());
                            }
                            continue;
                        case "5":
                            try {
                            System.out.println("Добавление материала к лекции");
                            System.out.print("Введите название курса: ");
                            String courseName5 = scanner.nextLine();
                            Course course5= teacher.findCourseByNameAndTeacher( courseName5);
                            System.out.print("Введите название урока: ");
                            String lessonName5 = scanner.nextLine();
                            System.out.print("Введите название материала: ");
                            String title = scanner.nextLine();
                            System.out.print("Введите тип материала (pdf файл, видео, презентация, ссылка): ");
                            String type = scanner.nextLine();
                            System.out.print("Введите путь к файлу с материалом: ");
                            String filePath = scanner.nextLine();
                            System.out.print("Введите внешнюю ссылку: ");
                            String url = scanner.nextLine();
                            LearningMaterial material=new LearningMaterial(title, type, filePath, url);
                            teacher.addMaterialToLecture(course5, lessonName5,  material);
                            } catch (Exception e) {
                                System.out.println("Ошибка добавления материала: " + e.getMessage());}
                            continue;
                        case "6":
                            try {
                            System.out.println("Удаление материала из лекции");
                            System.out.print("Введите название курса: ");
                            String courseName6 = scanner.nextLine();
                            Course course6= teacher.findCourseByNameAndTeacher( courseName6);
                            System.out.print("Введите название урока: ");
                            String lessonName6 = scanner.nextLine();
                            System.out.print("Введите название материала: ");
                            String materialTitle = scanner.nextLine();
                            LearningMaterial  material= TeacherService.findMaterial(teacher,course6,lessonName6, materialTitle);
                            teacher.removeMaterialToLecture( course6, lessonName6,  material);
                            } catch (Exception e) {
                                System.out.println("Ошибка удаления материала: " + e.getMessage());
                            }
                            continue;
                        case "7":
                            try {
                            System.out.println("Создание задания для студентов");
                            System.out.print("Введите название курса: ");
                            String courseName7= scanner.nextLine();
                            Course course7= teacher.findCourseByNameAndTeacher( courseName7);
                            System.out.print("Введите название урока: ");
                            String lessonName7 = scanner.nextLine();
                            System.out.print("Введите условие задания: ");
                            String taskDescription = scanner.nextLine();
                            System.out.print("Введите дедлайн для сдачи задания: ");
                            String deadline = scanner.nextLine();
                            System.out.print("Введите максимальный балл за задание: ");
                            int maxScore = scanner.nextInt();
                            teacher.addAssignmentLesson( course7, lessonName7,  taskDescription, deadline, maxScore);
                            } catch (Exception e) {
                                System.out.println("Ошибка создания задания: " + e.getMessage());
                            }
                            continue;
                        case "8":
                            try {
                            System.out.println("Проверка работы студента");
                            System.out.print("Введите название курса: ");
                            String courseName8= scanner.nextLine();
                            Course course8= teacher.findCourseByNameAndTeacher( courseName8);
                            System.out.print("Введите название урока: ");
                            String lessonName8 = scanner.nextLine();
                            System.out.print("Введите имя студента: ");
                            String studentName8 = scanner.nextLine();
                            Student student=TeacherService.findStudentByName(studentName8);
                            teacher. checkAssignment(course8, lessonName8, student);
                            } catch (Exception e) {
                                System.out.println("Ошибка проверки работы: " + e.getMessage());}
                            continue;
                        case "9":
                            try {
                            System.out.println("Список ваших кусов");
                            for (Course course9:teacher.getCreated_course()){
                                System.out.print(course9.getName_course());
                            }
                            } catch (Exception e) {
                                System.out.println("Ошибка получения списка курсов: " + e.getMessage());
                            }
                            continue;
                        case "10":
                            try {
                            System.out.println("Количество ваших кусов");
                            System.out.print(teacher.createdCourseCount());
                            } catch (Exception e) {
                                System.out.println("Ошибка получения количества курсов: " + e.getMessage());
                            }
                            continue;
                        case "11":
                            try {
                            System.out.println("Список ваших специальностей");
                            for (String specialisation:teacher.getSpecialisation()){
                                System.out.print(specialisation);
                            }
                            } catch (Exception e) {
                                System.out.println("Ошибка получения списка специализаций: " + e.getMessage());
                            }
                            continue;
                        case "12":
                            try {
                            System.out.println("Добавить специальность");
                            System.out.print("Введите название специальности: ");
                            String specialisation= scanner.nextLine();
                            teacher.addSpecialisation(specialisation);
                            } catch (Exception e) {
                                System.out.println("Ошибка добавления специализации: " + e.getMessage());
                            }
                            continue;
                        case "13":
                            try {
                            System.out.println("Удалить специальность");
                            System.out.print("Введите название специальности: ");
                            String specialisation13= scanner.nextLine();
                            if (teacher.removeSpecialization( specialisation13)){
                                System.out.print("Специальность успешно удалена ");
                            }
                            else {  System.out.print("Такой специальности у вас нет");};
                            } catch (Exception e) {
                                System.out.println("Ошибка удаления специализации: " + e.getMessage());
                            }
                            continue;
                        case "14":
                            try {
                            System.out.println("Список ваших студентов");
                            for (Student student14:teacher.getStudent()){
                                System.out.print(student14.getName());
                            }
                            } catch (Exception e) {
                                System.out.println("Ошибка получения списка студентов: " + e.getMessage());
                            }
                            continue;
                        case "15":
                            try {
                            System.out.println("Количество ваших студентов");
                            teacher.getStudentCount();
                            } catch (Exception e) {
                                System.out.println("Ошибка получения количества студентов: " + e.getMessage());
                            }
                            continue;
                        case "16":
                            try {
                            System.out.println("Количество уроков в указанном курсе (только для ваших курсов)");
                            System.out.print("Введите название курса: ");
                            String courseName16= scanner.nextLine();
                            Course course16= teacher.findCourseByNameAndTeacher(courseName16);
                            System.out.println("Количество уроков = "+teacher.lessonCount(course16));
                            } catch (Exception e) {
                                System.out.println("Ошибка получения количества уроков: " + e.getMessage());
                            }
                            continue;
                        case "17":
                            try {
                            System.out.println("Список уроков в указанном курсе (только для ваших курсов)");
                            System.out.print("Введите название курса: ");
                            String courseName17= scanner.nextLine();
                            Course course17= teacher.findCourseByNameAndTeacher(courseName17);
                            System.out.println("Список уроков курса: "+course17.getName_course());
                            for (Lesson lesson:teacher. lessonListCourse(course17)){
                                System.out.print("   "+lesson.getName_lession());
                            }
                            } catch (Exception e) {
                                System.out.println("Ошибка получения списка уроков: " + e.getMessage());
                            }
                            continue;
                        case "18":
                            try {
                            System.out.println("Количество пройденных уроков на определённом курсе определённым студентом");
                            System.out.print("Введите название курса: ");
                            String courseName18= scanner.nextLine();
                            Course course18= teacher.findCourseByNameAndTeacher(courseName18);
                            System.out.print("Введите имя студента: ");
                            String studentName18 = scanner.nextLine();
                            Student student18=TeacherService.findStudentByName(studentName18);
                            System.out.println("Количество = "+ teacher.lessonCountCourseStudent(student18, course18));
                            } catch (Exception e) {
                                System.out.println("Ошибка получения количества пройденных уроков: " + e.getMessage());
                            }
                            continue;
                        case "19":
                            try {
                            System.out.println("Список пройденных уроков на определённом курсе определённым студентом");
                            System.out.print("Введите название курса: ");
                            String courseName19= scanner.nextLine();
                            Course course19= teacher.findCourseByNameAndTeacher(courseName19);
                            System.out.print("Введите имя студента: ");
                            String studentName19 = scanner.nextLine();
                            Student student19=TeacherService.findStudentByName(studentName19);
                            System.out.print("Список: ");
                            for (Lesson lesson:teacher.lessonListCourseStudent( student19, course19)){
                               System.out.println(lesson.getName_lession());
                            }
                            } catch (Exception e) {
                                System.out.println("Ошибка получения списка пройденных уроков: " + e.getMessage());
                            }
                            continue;
                        case "20":
                            try {
                            System.out.println(" Информация о прохождении уроков на каждом курсе каждом студентом ");
                            teacher.completeLessonEveryCourseEveryStudent();
                            } catch (Exception e) {
                                System.out.println("Ошибка получения информации о прогрессе: " + e.getMessage());
                            }
                            continue;
                        case "21":
                            try {
                            System.out.println("Информация о прохождении уроков на определённом курсе каждом студентом");
                            System.out.print("Введите название курса: ");
                            String courseName21= scanner.nextLine();
                            Course course21= teacher.findCourseByNameAndTeacher(courseName21);
                            teacher.completeLessonCourseEveryStudent(course21);
                            } catch (Exception e) {
                                System.out.println("Ошибка получения информации о прогрессе: " + e.getMessage());
                            }
                            continue;
                        case "22":
                            try {
                            System.out.println("Информация о прохождении уроков на определённом курсе определённым студентом ");
                            System.out.print("Введите название курса: ");
                            String courseName22= scanner.nextLine();
                            Course course22= teacher.findCourseByNameAndTeacher(courseName22);
                            System.out.print("Введите имя студента: ");
                            String studentName22 = scanner.nextLine();
                            Student student22=TeacherService.findStudentByName(studentName22);
                            teacher.completeLessonCourseStudent(course22,student22);
                            } catch (Exception e) {
                                System.out.println("Ошибка получения информации о прогрессе: " + e.getMessage());
                            }
                            continue;
                        case "23":
                            try {
                            System.out.println("Выставление оценки студенту ");
                            System.out.print("Введите имя студента: ");
                            String studentName23 = scanner.nextLine();
                            Student student23=TeacherService.findStudentByName(studentName23);
                            System.out.print("Введите название курса: ");
                            String courseName23= scanner.nextLine();
                            Course course23= teacher.findCourseByNameAndTeacher(courseName23);
                            System.out.print("Введите название урока: ");
                            String lessonName23 = scanner.nextLine();
                            System.out.print("Введите оценку: ");
                            int score = scanner.nextInt();
                            System.out.print("Введите комментарий к оценке: ");
                            String comment = scanner.nextLine();
                            teacher.gradeStudent(student23,course23, lessonName23, score, comment);
                            } catch (Exception e) {
                                System.out.println("Ошибка выставления оценки: " + e.getMessage());
                            }
                            continue;
                        case "24":
                            try {
                            System.out.println("Удаление оценки студента");
                            System.out.print("Введите имя студента: ");
                            String studentName24 = scanner.nextLine();
                            Student student24=TeacherService.findStudentByName(studentName24);
                            System.out.print("Введите название курса: ");
                            String courseName24= scanner.nextLine();
                            Course course24= teacher.findCourseByNameAndTeacher(courseName24);
                            System.out.print("Введите название урока: ");
                            String lessonName24 = scanner.nextLine();
                            teacher.removeStudentGrade(student24, course24, lessonName24);
                            } catch (Exception e) {
                                System.out.println("Ошибка удаления оценки: " + e.getMessage());
                            }
                            continue;
                        case "25":
                            try {
                            System.out.println("Информация о ваc");
                            teacher.informationOutput();
                            } catch (Exception e) {
                                System.out.println("Ошибка вывода информации: " + e.getMessage());
                            }
                            continue;
                        case "26":
                            try {
                            System.out.println("Информация об одном из ваших курсов");
                            System.out.print("Введите название курса: ");
                            String courseName26= scanner.nextLine();
                            Course course26= teacher.findCourseByNameAndTeacher(courseName26);
                            teacher.CourseInf(course26);
                            } catch (Exception e) {
                                System.out.println("Ошибка вывода информации о курсе: " + e.getMessage());
                            }
                            continue;
                        case "27":
                            try {
                            System.out.println(" Информация об одном из ваших уроков");
                            System.out.print("Введите название курса: ");
                            String courseName27= scanner.nextLine();
                            Course course27= teacher.findCourseByNameAndTeacher(courseName27);
                            System.out.print("Введите название урока: ");
                            String lessonName27 = scanner.nextLine();
                            Lesson lesson27= LessonService.findLessonInCourse(course27,lessonName27);
                            teacher.LessonInf(course27, lesson27);
                            } catch (Exception e) {
                                System.out.println("Ошибка вывода информации об уроке: " + e.getMessage());
                            }
                            continue;
                        case "28":
                            try {
                            System.out.println("Информация об одном из ваших студентов");
                            System.out.print("Введите имя студента: ");
                            String studentName28 = scanner.nextLine();
                            Student student28=TeacherService.findStudentByName(studentName28);
                            teacher.studentInf(student28);
                            } catch (Exception e) {
                                System.out.println("Ошибка вывода информации о студенте: " + e.getMessage());
                            }
                            continue;
                        case "29":
                            try {
                            System.out.println("Удаление вашего курса");
                            System.out.print("Введите название курса: ");
                            String courseName29= scanner.nextLine();
                            teacher.deleteCourse(courseName29);
                            } catch (Exception e) {
                                System.out.println("Ошибка удаления курса: " + e.getMessage());
                            }
                            continue;
                        case "30":
                            try {
                            System.out.println("Удаление урока из вашего курса");
                            System.out.print("Введите название курса: ");
                            String courseName28= scanner.nextLine();
                            System.out.print("Введите название урока: ");
                            String lessonName28 = scanner.nextLine();
                            teacher.deleteLesson(courseName28, lessonName28);
                            } catch (Exception e) {
                                System.out.println("Ошибка удаления урока: " + e.getMessage());
                            }
                            continue;
                        case "31":
                            try {
                            System.out.println("Вывод студентов по среднему балу");
                            System.out.print("Введите название курса: ");
                            String courseName31= scanner.nextLine();
                            Course course31= teacher.findCourseByNameAndTeacher(courseName31);
                            System.out.print("Вывести всех студентов курса "+courseName31+" , у которых средний балл от: ");
                            double from = scanner.nextDouble();
                            System.out.print("до: ");
                            double to = scanner.nextDouble();
                            teacher.studentNameListByGPA(course31, from , to);
                            } catch (Exception e) {
                                System.out.println("Ошибка вывода студентов по баллу: " + e.getMessage());
                            }
                            continue;
                        case "32":
                            try {
                            System.out.println("Вывод студентов проценту завершения указанного курса");
                            System.out.print("Введите название курса: ");
                            String courseName32= scanner.nextLine();
                            Course course32= teacher.findCourseByNameAndTeacher(courseName32);
                            System.out.print("Вывести всех студентов курса "+courseName32+" , у которых процент завершения курса от: ");
                            double from32 = scanner.nextDouble();
                            System.out.print("до: ");
                            double to32 = scanner.nextDouble();
                            teacher.studentNameListByPercent(course32, from32 , to32);
                            } catch (Exception e) {
                                System.out.println("Ошибка вывода студентов по проценту: " + e.getMessage());
                            }
                            continue;
                        case "33":
                            try {
                            System.out.println("Вывод студентов,  которые завершили/не завершили конкретный курс");
                            System.out.print("Введите название курса: ");
                            String courseName33= scanner.nextLine();
                            Course course33= teacher.findCourseByNameAndTeacher(courseName33);
                            System.out.print("Выберите, что выводить:");
                            System.out.print("1. студентов,  которые завершили курс "+courseName33);
                            System.out.print("2. студентов,  которые не завершили курс "+courseName33);
                                int x;
                                try {
                                    x = Integer.parseInt(scanner.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Ошибка! Введите число");
                                    continue;
                                }
                            boolean completed=true;
                            if (x==1){completed=true;}
                            else if(x==2){completed =false;}
                            else {
                                System.out.print("Выбрано не верное значение");
                                continue;
                            }
                            teacher.studentNameListByCompleted_course(course33, completed);
                            } catch (Exception e) {
                                System.out.println("Ошибка вывода студентов по статусу: " + e.getMessage());
                            }
                            continue;
                        default:
                            System.out.println("Неверный выбор!");
                            block=0;
                            continue;
                    }
                }
                else if (user instanceof Student) {
                    Student student = (Student) user;
                    System.out.println("Студент: " + student.getName());
                    System.out.println("Выберите действие: ");
                    System.out.println("    1: Главное меню");
                    System.out.println("    2. Выход из программы");
                    System.out.println("    3. Записаться на курс");
                    System.out.println("    4. Посмотреть список курсов на которе записан");
                    System.out.println("    5. Посмотреть количество курсов на которе записан");
                    System.out.println("    6. Посмотреть список всех существующих курсов");
                    System.out.println("    7. Посмотреть процент завершения конкретного курса");
                    System.out.println("    8. Посмотреть средний балл за конкретный курс");
                    System.out.println("    9. Посмотреть оценку за конкретный урок");
                    System.out.println("    10. Посмотреть все оценки за конкретный курс");
                    System.out.println("    11. Посмотреть завершён ли конкретный курс");
                    System.out.println("    12. Посмотреть количество пройденных уроков в конкретном курсе");
                    System.out.println("    13. Посмотреть список пройденных уроков в конкретном курсе");
                    System.out.println("    14. Посмотреть общую информацию о конкретном курсе");
                    System.out.println("    15. Посмотреть общую информацию о конкретном уроке конкретного курса (на который записан)");
                    System.out.println("    16. Посмотреть прогресс на конкретном курсе ");
                    System.out.println("    17. Посмотреть прогресс на всех курсах ");
                    System.out.println("    18. Проверить записан ли на конкретный курс");
                    System.out.println("    19. Посмотреть информацию о себе");
                    System.out.println("    20. Посмотреть преподавателей с конкретной специальностью");
                    System.out.println("    21. Посмотреть преподавателей ведущих конкретный курс");
                    System.out.println("    22. Посмотреть уроки конкретного курса конкретного преподавателя тип которых соответствует указанному (если записан на курс)");
                    System.out.println("    23. Посмотреть список названий всех курсов, существующих в системе");
                    System.out.println("    24. Посмотреть информацию о конкретном уроке конкретного курса (на который записан)");
                    System.out.println("    25. Посмотреть информацию о преподавателе");
                    System.out.println("    26. Посмотреть список курсов конкретного преподавателя");
                    System.out.println("    27. Покинуть курс");
                    System.out.println("    28. Посмотреть контент урока");
                    System.out.println("    29. Отправить выполненное задание");
                    System.out.println("    30. Удалить конкретное задание сданное по конкретному уроку");

                    System.out.print("Ваш выбор: ");

                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            System.out.println("Главное меню");
                            block=0;
                            break;
                        case "2":
                            System.out.println("Выход из программы");
                            System.out.println("До свидания!");
                            System.exit(0);
                        case "3":
                            System.out.println("Записаться на курс");
                            System.out.print("Введите название курса: ");
                            String courseName= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName= scanner.nextLine();
                            Course course= student.findCourseByNameAndTeacher(courseName, teacherName);
                            student.registrationCourses(course);
                            continue;
                        case "4":
                            System.out.println("Посмотреть список курсов на которе записан");
                            for (Course course4: student.getRegistration_courses()){
                                System.out.println(course4.getName_course());
                            }
                            continue;
                        case "5":
                            System.out.println("Посмотреть количество курсов на которе записан");
                            System.out.println("Количество = "+student.getRegistrationCoursesCount());
                            continue;
                        case "6":
                            System.out.println("Посмотреть список всех существующих курсов");
                            for (Course course6:   student.getAllCourses()){
                                System.out.println(course6.getName_course());
                            }
                            continue;
                        case "7":
                            System.out.println("Посмотреть процент завершения конкретного курса");
                            System.out.print("Введите название курса: ");
                            String courseName7= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName7= scanner.nextLine();
                            Course course7= student.findCourseByNameAndTeacher(courseName7, teacherName7);
                            System.out.println("Процент завершения = "+student.percentCourse(course7));
                            continue;
                        case "8":
                            System.out.println("Посмотреть средний балл за конкретный курс");
                            System.out.print("Введите название курса: ");
                            String courseName8= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName8= scanner.nextLine();
                            Course course8= student.findCourseByNameAndTeacher(courseName8, teacherName8);
                            System.out.println("Средний балл = "+student.GPACourse(course8));
                            continue;
                        case "9":
                            System.out.println("Посмотреть оценку за конкретный урок");
                            System.out.print("Введите название курса: ");
                            String courseName9= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName9= scanner.nextLine();
                            Course course9= student.findCourseByNameAndTeacher(courseName9, teacherName9);
                            System.out.print("Введите название урока: ");
                            String lessonName= scanner.nextLine();
                            System.out.println("Средний балл = "+student.getGradeForLessonForStudent(course9, lessonName));
                            continue;
                        case "10":
                            System.out.println("Посмотреть все оценки за конкретный курс");
                            System.out.print("Введите название курса: ");
                            String courseName10= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName10= scanner.nextLine();
                            Course course10= student.findCourseByNameAndTeacher(courseName10, teacherName10);
                            System.out.print("Оценки за курс "+course10.getName_course()+": ");
                            student.getGradesForCourse(course10);
                            continue;
                        case "11":
                            System.out.println("Посмотреть завершён ли конкретный курс");
                            System.out.print("Введите название курса: ");
                            String courseName11= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName11= scanner.nextLine();
                            Course course11= student.findCourseByNameAndTeacher(courseName11, teacherName11);
                            student.getCompletedCourse(course11);
                            continue;
                        case "12":
                            System.out.println("Посмотреть количество пройденных уроков в конкретном курсе");
                            System.out.print("Введите название курса: ");
                            String courseName12= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName12= scanner.nextLine();
                            Course course12= student.findCourseByNameAndTeacher(courseName12, teacherName12);
                            System.out.println("Количество = "+student.completeLessonCount(course12));
                            continue;
                        case "13":
                            System.out.println("Посмотреть список пройденных уроков в конкретном курсе");
                            String courseName13= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName13= scanner.nextLine();
                            Course course13= student.findCourseByNameAndTeacher(courseName13, teacherName13);
                            for (Lesson lesson:student.completeLessonListCourse(course13)){
                                lesson.getName_lession();
                            }
                            continue;
                        case "14":
                            System.out.println("Посмотреть общую информацию о конкретном курсе");
                            System.out.print("Введите название курса: ");
                            String courseName14= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName14= scanner.nextLine();
                            Course course14= student.findCourseByNameAndTeacher(courseName14, teacherName14);
                            student.courseInf(course14);
                            continue;
                        case "15":
                            System.out.println("Посмотреть общую информацию о конкретном уроке конкретного курса");
                            System.out.print("Введите название курса: ");
                            String courseName15= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName15= scanner.nextLine();
                            Course course15= student.findCourseByNameAndTeacher(courseName15, teacherName15);
                            System.out.print("Введите название урока: ");
                            String lessonName15= scanner.nextLine();
                            Lesson lesson15=student.findLessonByNameForCourse(course15, lessonName15);
                            student.LessonInf( lesson15, course15);
                            continue;
                        case "16":
                            System.out.println("Посмотреть прогресс на конкретном курсе");
                            System.out.print("Введите название курса: ");
                            String courseName16= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName16= scanner.nextLine();
                            Course course16= student.findCourseByNameAndTeacher(courseName16, teacherName16);
                            student.completeLessonListSpecificCourse(course16);
                            continue;
                        case "17":
                            System.out.println("Посмотреть прогресс на всех курсах");
                            student.completeLessonListEveryCourse();
                            continue;
                        case "18":
                            System.out.println("Проверить записан ли на конкретный курс");
                            System.out.print("Введите название курса: ");
                            String courseName18= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName18= scanner.nextLine();
                            Course course18= student.findCourseByNameAndTeacher(courseName18, teacherName18);
                            if(student.registrationCoursesCheck(course18)){
                                System.out.print("Вы записаны на курс "+courseName18);
                            }
                            else {
                                System.out.print("Вы не записаны на курс "+courseName18);
                            }
                            continue;
                        case "19":
                            System.out.println("Посмотреть информацию о себе");
                            student.informationOutput();
                            continue;
                        case "20":
                            System.out.println("Посмотреть преподавателей с конкретной специальностью");
                            System.out.print("Введите название специальности: ");
                            String specialisation= scanner.nextLine();
                            student.teacherNameLisBySpecialisation(specialisation);
                            continue;
                        case "21":
                            System.out.println("Посмотреть преподавателей ведущих конкретный курс");
                            System.out.print("Введите название курса: ");
                            String courseName21= scanner.nextLine();
                           student.teacherNameLisByCourse( courseName21);
                            continue;
                        case "22":
                            System.out.println("Посмотреть уроки конкретного курса конкретного преподавателя тип которых соответствует указанному");
                            System.out.print("Введите название курса: ");
                            String courseName22= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName22= scanner.nextLine();
                            Course course22= student.findCourseByNameAndTeacher(courseName22, teacherName22);
                            System.out.print("Введите тип урока: ");
                            String type= scanner.nextLine();
                            student.lessonNameListByType(course22, type);
                            continue;
                        case "23":
                            System.out.println("Посмотреть список названий всех курсов, существующих в системе");
                            student.namesAllCourses();
                            continue;
                        case "24":
                            System.out.println("Посмотреть информацию о конкретном уроке конкретного курса");
                            System.out.print("Введите название курса: ");
                            String courseName24= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName24= scanner.nextLine();
                            Course course24= student.findCourseByNameAndTeacher(courseName24, teacherName24);
                            System.out.print("Введите название урока: ");
                            String lessonName24= scanner.nextLine();
                            Lesson lesson24=student.findLessonByNameForCourse(course24, lessonName24);
                            student.LessonInf(lesson24, course24);
                            continue;
                        case "25":
                            System.out.println("Посмотреть информацию о преподавателе");
                            System.out.print("Введите имя учителя: ");
                            String teacherName25= scanner.nextLine();
                            Teacher teacher25=student.findTeacherByName(teacherName25);
                            student.teacherInf(teacher25);
                            continue;
                        case "26":
                            System.out.println("Посмотреть список курсов конкретного преподавателя");
                            System.out.print("Введите имя учителя: ");
                            String teacherName26= scanner.nextLine();
                            Teacher teacher26=student.findTeacherByName(teacherName26);
                            System.out.println("Список курсов: ");
                            for (Course course1: student.getCreated_courseForTeacher(teacher26)){
                                System.out.println(course1.getName_course());
                            }
                            continue;
                        case "27":
                            System.out.println("Покинуть курс");
                            System.out.print("Введите название курса: ");
                            String courseName27= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName27= scanner.nextLine();
                            Course course27= student.findCourseByNameAndTeacher(courseName27, teacherName27);
                            student.leaveCourse(course27);
                            continue;
                        case "28":
                            System.out.println("Посмотреть контент урока");
                            System.out.print("Введите название курса: ");
                            String courseName28= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName28= scanner.nextLine();
                            Course course28= student.findCourseByNameAndTeacher(courseName28, teacherName28);
                            System.out.print("Введите название урока: ");
                            String lessonName28= scanner.nextLine();
                            student. viewLessonContent(course28, lessonName28);
                            continue;
                        case "29":
                            System.out.println("Отправить выполненное задание");
                            System.out.print("Введите название курса: ");
                            String courseName29= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName29= scanner.nextLine();
                            Course course29= student.findCourseByNameAndTeacher(courseName29, teacherName29);
                            System.out.print("Введите название урока: ");
                            String lessonName29= scanner.nextLine();
                            System.out.print("Введите текстовый ответ: ");
                            String answerText= scanner.nextLine();
                            System.out.print("Введите путь к прикрепленному файлу: ");
                            String filePath= scanner.nextLine();
                            student.submitAssignment(course29, lessonName29, answerText, filePath);
                            continue;
                        case "30":
                            System.out.println("Удалить задание сданное по конкретному уроку");
                            System.out.print("Введите название курса: ");
                            String courseName30= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName30= scanner.nextLine();
                            Course course30= student.findCourseByNameAndTeacher(courseName30, teacherName30);
                            System.out.print("Введите название урока: ");
                            String lessonName30= scanner.nextLine();
                            student.removeAssignment(course30,lessonName30);
                            continue;
                        default:
                            System.out.println("Неверный выбор!");
                            block=0;
                            continue;
                            }
                }
                else if (user instanceof Admin) {
                    Admin admin = (Admin) user;
                    System.out.println("Администратор: " + admin.getName());
                    System.out.println("Выберите действие: ");
                    System.out.println("    1: Главное меню");
                    System.out.println("    2. Выход из программы");
                    System.out.println("    3. Удалить пользователя");
                    System.out.println("    4. Просмотреть всех пользователей и информации о них");
                    System.out.println("    5. Просмотреть конкретного пользователя и информации о нём");
                    System.out.println("    6. Просмотреть просмотреть статистику системы");
                    System.out.println("    7. Просмотреть количество курсов на которые записан конкретный студент");
                    System.out.println("    8. Просмотреть список курсов на которых записан конкретный студент ");
                    System.out.println("    9. Просмотреть процент завершения курса конкретным студентом");
                    System.out.println("    10. Просмотреть оценку за конкретный урок для конкретного студента");
                    System.out.println("    11. Просмотреть средний балл конкретного студента за конкретный курс");
                    System.out.println("    12. Просмотреть все оценки конкретного студента за конкретный курс");
                    System.out.println("    13. Просмотреть завершён ли конкретный курс конкретным студентом");
                    System.out.println("    14. Просмотреть записан ли на конкретный курс конкретный студент");
                    System.out.println("    15. Просмотреть список кусов конкретного преподавателя");
                    System.out.println("    16. Просмотреть количество кусов конкретного преподавателя");
                    System.out.println("    17. Просмотреть список специальностей конкретного преподавателя");
                    System.out.println("    18. Удалить конкретную специальность конкретного преподавателя");
                    System.out.println("    19. Добавить конкретную специальности конкретному преподавателю");
                    System.out.println("    20. Просмотреть список студентов конкретного преподавателя");
                    System.out.println("    21. Просмотреть количества студентов конкретного преподавателя");
                    System.out.println("    22. Просмотреть количество пройденных уроков на конкретном курсе конкретного преподавателя конкретным студентом");
                    System.out.println("    23. Просмотреть список пройденных уроков на конкретном курсе конкретного преподавателя конкретным студентом");
                    System.out.println("    24. Просмотреть прогресс на каждом курсе конкретного преподавателя конкретным студентом");
                    System.out.println("    25. Просмотреть прогресс на конкретном курсе конкретного преподавателя каждым студентом");
                    System.out.println("    26. Просмотреть прогресс на конкретном курсе конкретного преподавателя конкретным студентом");
                    System.out.println("    27. Удалить оценку студента за конкретный урок конкретного курса конкретного преподавателя");
                    System.out.println("    28. Просмотреть имена преподавателей с конкретной специальностью");
                    System.out.println("    29. Просмотреть имена преподавателей ведущих курс с конкретным названием");
                    System.out.println("    30. Просмотреть всех студентов со средним балом за конкретный курс конкретного преподавателя из диапазона (от до)");
                    System.out.println("    31. Просмотреть студентов у которых процент завершения конкретного курса конкретного преподавателя из диапазона (от до)");
                    System.out.println("    32. Просмотреть всех уроков конкретного типа, конкретного курса,  конкретного преподавателя");
                    System.out.println("    33. Просмотреть всех студентов которые завершили/не завершили конкретный курс конкретного преподавателя");
                    System.out.println("    34. Просмотреть список названий всех существующих курсов в системе");
                    System.out.println("    35. Просмотреть существование учителя");
                    System.out.println("    36. Просмотреть общую информацию о конкретном курсе");
                    System.out.println("    37. Просмотреть общую информацию о конкретном уроке конкретного курса");
                    System.out.println("    38. Удалить курс");
                    System.out.println("    39. Удалить урок");
                    System.out.println("    40. Удалить материал из лекции");
                    System.out.println("    41. Просмотреть работу студента");
                    System.out.println("    42. Просмотреть контент урока");
                    System.out.println("    43. Удалить конкретное задание конкретного студентом");

                    System.out.print("Ваш выбор: ");

                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            System.out.println("Главное меню");
                            block=0;
                            break;
                        case "2":
                            System.out.println("Выход из программы");
                            System.out.println("До свидания!");
                            System.exit(0);
                        case "3":
                            System.out.println("Удалить пользователя");
                            System.out.print("Введите имя пользователя, которого хотите удалить: ");
                            String userName= scanner.nextLine();
                            System.out.print("Выберите роль пользователя, которого хотите удалить (1.Администратор, 2. Преподаватель, 3. Ученик : ");
                            int role= scanner.nextInt();
                            User user3=null;
                            if (role==1){
                                for (Admin admin3:Database.admins){
                                    if(admin3.getName().equals(userName)){
                                        user3=admin3;
                                    }
                                }
                            }
                            else if (role==2){
                                for (Teacher teacher3:Database.teachers){
                                    if(teacher3.getName().equals(userName)){
                                        user3=teacher3;
                                    }
                                }
                            }
                            else if (role==3){
                                for (Student student3:Database.students){
                                    if(student3.getName().equals(userName)){
                                        user3=student3;
                                    }
                                }
                            }
                            else{System.out.println("Такой роли нет");
                                continue;
                            }
                            admin.deleteUser(user3);
                            continue;
                        case "4":
                            System.out.println("Просмотреть всех пользователей и информации о них");
                            admin.viewUsers ();
                            continue;
                        case "5":
                            System.out.println("Просмотреть конкретного пользователя и информации о нём");
                            System.out.println("Удалить пользователя");
                            System.out.print("Введите имя пользователя, которого хотите удалить: ");
                            String userName5= scanner.nextLine();
                            System.out.print("Выберите роль пользователя, которого хотите удалить (1.Администратор, 2. Преподаватель, 3. Ученик : ");
                            int role5= scanner.nextInt();
                            User user5=null;
                            if (role5==1){
                                for (Admin admin5:Database.admins){
                                    if(admin5.getName().equals(userName5)){
                                        user5=admin5;
                                    }
                                }
                            }
                            else if (role5==2){
                                for (Teacher teacher5:Database.teachers){
                                    if(teacher5.getName().equals(userName5)){
                                        user5=teacher5;
                                    }
                                }
                            }
                            else if (role5==3){
                                for (Student student5:Database.students){
                                    if(student5.getName().equals(userName5)){
                                        user5=student5;
                                    }
                                }
                            }
                            else{System.out.println("Такой роли нет");
                                continue;
                            }
                                    admin.viewInfoUser(user5);
                            continue;
                        case "6":
                            System.out.println("Просмотреть просмотреть статистику системы");
                            admin.viewSystemStatistics();
                            continue;
                        case "7":
                            System.out.println("Просмотреть количество курсов на которые записан конкретный студент");
                            System.out.println("Количество = "+ admin.RegistrationCoursesCountForStudent(inputStudent(admin)));
                            continue;
                        case "8":
                            System.out.println("Просмотреть список курсов на которых записан конкретный студент");
                            System.out.print("Введите имя студента: ");
                            String studentName8 = scanner.nextLine();
                            Student student8=admin.findStudentByName(studentName8);
                            for (Course course8: admin.Registration_coursesForStudent(student8)){
                                System.out.println(course8.getName_course());
                            }
                            continue;
                        case "9":
                            System.out.println("Просмотреть процент завершения курса конкретным студентом");
                            System.out.println("Процент завершения = "+admin.percentCourseForStudent(inputStudent(admin), inputCourseByTeacher(admin)));
                            continue;
                        case "10":
                            System.out.println("Просмотреть оценку за конкретный урок для конкретного студента");
                            System.out.print("Введите название урока: ");
                            String lessonName10= scanner.nextLine();
                            System.out.print("Оценка = "+admin.GradeForLessonForStudent(inputStudent(admin), inputCourseByTeacher(admin), lessonName10));
                            continue;
                        case "11":
                            System.out.println("Просмотреть средний балл конкретного студента за конкретный курс");
                            System.out.print("Средний балл = "+ admin.GPACourseForStudent(inputStudent(admin), inputCourseByTeacher(admin)));
                            continue;
                        case "12":
                            System.out.println("Просмотреть все оценки конкретного студента за конкретный курс");
                            System.out.print("Оценки студента "+inputStudent(admin).getName()+" за конкретный курс"+inputCourseByTeacher(admin).getName_course()+": ");
                           admin.GradesForCourseForStudent(inputStudent(admin), inputCourseByTeacher(admin));
                            continue;
                        case "13":
                            System.out.println("Просмотреть завершён ли конкретный курс конкретным студентом");
                            admin.CompletedCourseForStudent(inputStudent(admin), inputCourseByTeacher(admin));
                            continue;
                        case "14":
                            System.out.println("Просмотреть записан ли на конкретный курс конкретный студент");
                            if( admin.registrationCoursesCheckForStudent(inputStudent(admin), inputCourseByTeacher(admin))){
                                System.out.print("Студент "+ inputStudent(admin).getName() +" записан на курс "+inputCourseByTeacher(admin).getName_course());
                            }
                            else {
                                System.out.print("Студент "+ inputStudent(admin).getName() +" не записан на курс "+inputCourseByTeacher(admin).getName_course());
                            }
                            continue;
                        case "15":
                            System.out.println("Просмотреть список кусов конкретного преподавателя");
                            System.out.print("Введите имя учителя: ");
                            String teacherName15 = scanner.nextLine();
                            Teacher teacher15 =admin.findTeacherByName(teacherName15);
                            System.out.println("Список кусов преподавателя "+teacherName15+": ");
                            for (Course course15: admin.getCreated_courseForTeacher(teacher15)){
                                course15.getName_course();
                            }
                            continue;
                        case "16":
                            System.out.println("Просмотреть количество кусов конкретного преподавателя");
                            System.out.print("Введите имя учителя: ");
                            String teacherName16 = scanner.nextLine();
                            Teacher teacher16 =admin.findTeacherByName(teacherName16);
                            System.out.println("Количество = "+admin.createdCourseCountForTeacher(teacher16));
                            continue;
                        case "17":
                            System.out.println("Просмотреть список специальностей конкретного преподавателя");
                            System.out.print("Введите имя учителя: ");
                            String teacherName17 = scanner.nextLine();
                            Teacher teacher17 =admin.findTeacherByName(teacherName17);
                            System.out.println("Список специальностей преподавателя "+teacherName17+": ");
                            for (String specialisation:  admin.SpecialisationForTeacher(teacher17)){
                                System.out.print(specialisation);
                            }
                            continue;
                        case "18":
                            System.out.println("Удалить конкретную специальность конкретного преподавателя");
                            System.out.print("Введите имя учителя: ");
                            String teacherName18= scanner.nextLine();
                            Teacher teacher18 =admin.findTeacherByName(teacherName18);
                            System.out.print("Введите специальность для удаления: ");
                            String specialisation18= scanner.nextLine();
                            admin.removeSpecializationForTeacher(teacher18, specialisation18);
                            continue;
                        case "19":
                            System.out.println("Добавить конкретную специальности конкретному преподавателю");
                            System.out.print("Введите имя учителя: ");
                            String teacherName19= scanner.nextLine();
                            Teacher teacher19 =admin.findTeacherByName(teacherName19);
                            System.out.print("Введите специальность для добавления: ");
                            String specialisation19= scanner.nextLine();
                            admin.addSpecialisationForTeacher(teacher19, specialisation19);
                            continue;
                        case "20":
                            System.out.println("Просмотреть список студентов конкретного преподавателя");
                            System.out.print("Введите имя учителя: ");
                            String teacherName20= scanner.nextLine();
                            Teacher teacher20 =admin.findTeacherByName(teacherName20);
                            System.out.println("Список студентов преподавателя "+teacherName20+": ");
                            for (Student student: admin.StudentListForTeacher(teacher20)){
                                System.out.print(student.getName());
                            }
                            continue;
                        case "21":
                            System.out.println("Просмотреть количества студентов конкретного преподавателя");
                            System.out.print("Введите имя учителя: ");
                            String teacherName21= scanner.nextLine();
                            Teacher teacher21 =admin.findTeacherByName(teacherName21);
                            System.out.println("количество = "+  admin.StudentCountForTeacher(teacher21));
                            continue;
                        case "22":
                            System.out.println("Просмотреть количество пройденных уроков на конкретном курсе конкретного преподавателя конкретным студентом");
                            System.out.print("Введите имя учителя: ");
                            String teacherName22= scanner.nextLine();
                            Teacher teacher22 =admin.findTeacherByName(teacherName22);
                            System.out.print("Введите название курса: ");
                            String courseName22= scanner.nextLine();
                            Course course22= admin.findCourseByNameAndTeacher(courseName22, teacherName22);
                            System.out.println("количество = "+ admin.lessonCountCourseStudentForTeacher(teacher22, inputStudent(admin),course22));
                            continue;
                        case "23":
                            System.out.println("Просмотреть список пройденных уроков на конкретном курсе конкретного преподавателя конкретным студентом");
                            System.out.print("Введите имя учителя: ");
                            String teacherName23= scanner.nextLine();
                            Teacher teacher23 =admin.findTeacherByName(teacherName23);
                            System.out.print("Введите название курса: ");
                            String courseName23= scanner.nextLine();
                            Course course23= admin.findCourseByNameAndTeacher(courseName23, teacherName23);
                            System.out.print("Список пройденных уроков: ");
                            for (Lesson lesson: admin.lessonListCourseStudentForTeacher(teacher23, inputStudent(admin),course23)){
                                System.out.print(lesson.getName_lession());
                            }
                            continue;
                        case "24":
                            System.out.println("Просмотреть прогресс на каждом курсе конкретного преподавателя конкретным студентом");
                            System.out.print("Введите имя учителя: ");
                            String teacherName24= scanner.nextLine();
                            Teacher teacher24 =admin.findTeacherByName(teacherName24);
                            admin.completeLessonEveryCourseEveryStudentForTeacher(teacher24);
                            continue;
                        case "25":
                            System.out.println("Просмотреть прогресс на конкретном курсе конкретного преподавателя каждым студентом");
                            System.out.print("Введите имя учителя: ");
                            String teacherName25= scanner.nextLine();
                            Teacher teacher25 =admin.findTeacherByName(teacherName25);
                            System.out.print("Введите название курса: ");
                            String courseName25= scanner.nextLine();
                            Course course25= admin.findCourseByNameAndTeacher(courseName25, teacherName25);
                            admin.completeLessonCourseEveryStudentForTeacher(teacher25,course25);
                            continue;
                        case "26":
                            System.out.println("Просмотреть прогресс на конкретном курсе конкретного преподавателя конкретным студентом");
                            System.out.print("Введите имя учителя: ");
                            String teacherName26= scanner.nextLine();
                            Teacher teacher26 =admin.findTeacherByName(teacherName26);
                            System.out.print("Введите название курса: ");
                            String courseName26= scanner.nextLine();
                            Course course26= admin.findCourseByNameAndTeacher(courseName26, teacherName26);
                            admin.completeLessonCourseStudentForTeacher(teacher26,course26, inputStudent(admin));
                            continue;
                        case "27":
                            System.out.println("Удалить оценку студента за конкретный урок конкретного курса конкретного преподавателя");
                            System.out.print("Введите имя учителя: ");
                            String teacherName27= scanner.nextLine();
                            Teacher teacher27 =admin.findTeacherByName(teacherName27);
                            System.out.print("Введите название курса: ");
                            String courseName27= scanner.nextLine();
                            Course course27= admin.findCourseByNameAndTeacher(courseName27, teacherName27);
                            System.out.print("Введите название урока: ");
                            String lessonName27= scanner.nextLine();
                            admin.removeStudentGradeForTeacher(teacher27, inputStudent(admin),course27,lessonName27);
                            continue;
                        case "28":
                            System.out.println("Просмотреть имена преподавателей с конкретной специальностью");
                            System.out.println("Введите специальность для поиска");
                            String specialisation28= scanner.nextLine();
                            System.out.println("Результат поиска: ");
                            admin.teacherNameLisBySpecialisation(specialisation28);
                            continue;
                        case "29":
                            System.out.println("Просмотреть имена преподавателей ведущих курс с конкретным названием");
                            System.out.print("Введите название курса: ");
                            String courseName29= scanner.nextLine();
                            System.out.println("Результат поиска: ");
                            admin.teacherNameLisByCourse(courseName29);
                            continue;
                        case "30":
                            System.out.println("Просмотреть всех студентов со средним балом за конкретный курс конкретного преподавателя из диапазона (от до)");
                            System.out.print("Введите значение от: ");
                            double from30= scanner.nextDouble();
                            System.out.print("Введите значение до: ");
                            double to30= scanner.nextDouble();
                            admin.studentNameListByGPA(inputCourseByTeacher(admin), from30 , to30);
                            continue;
                        case "31":
                            System.out.println("Просмотреть студентов у которых процент завершения конкретного курса конкретного преподавателя из диапазона (от до)");
                            System.out.print("Введите значение от: ");
                            double from31= scanner.nextDouble();
                            System.out.print("Введите значение до: ");
                            double to31= scanner.nextDouble();
                            admin.studentNameListByPercent(inputCourseByTeacher(admin),from31, to31);
                            continue;
                        case "32":
                            System.out.println("Просмотреть всех уроков конкретного типа, конкретного курса,  конкретного преподавателя");
                            System.out.print("Введите название курса: ");
                            String courseName32= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName32= scanner.nextLine();
                            Course course32= admin.findCourseByNameAndTeacher(courseName32, teacherName32);
                            System.out.print("Введите тип урока : ");
                            String type32= scanner.nextLine();
                            admin.lessonNameListByType(course32, type32);
                            continue;
                        case "33":
                            System.out.println("Просмотреть всех студентов которые завершили/не завершили конкретный курс конкретного преподавателя");
                            System.out.print("Выберите, что выводить:");
                            System.out.print("1. студентов,  которые завершили курс "+inputCourseByTeacher(admin).getName_course());
                            System.out.print("2. студентов,  которые не завершили курс "+inputCourseByTeacher(admin).getName_course());
                            int x = scanner.nextInt();
                            boolean completed=true;
                            if (x==1){completed=true;}
                            else if(x==2){completed =false;}
                            else {
                                System.out.print("Выбрано не верное значение");
                                continue;
                            }
                            admin.studentNameListByCompleted_course(inputCourseByTeacher(admin), completed);
                            continue;
                        case "34":
                            System.out.println("Просмотреть список названий всех существующих курсов в системе");
                            admin.namesAllCourses();
                            continue;
                        case "35":
                            System.out.println("Просмотреть существование учителя");
                            System.out.print("Введите имя учителя: ");
                            String teacherName35= scanner.nextLine();
                            Teacher teacher35 =admin.findTeacherByName(teacherName35);
                            if(admin.teacherExists(teacher35)){
                                System.out.print("Учитель "+teacherName35+" существует в системе");
                            }
                            else {
                                System.out.print("Учитель "+teacherName35+" не существует в системе");
                            }
                            continue;
                        case "36":
                            System.out.println("Просмотреть общую информацию о конкретном курсе");
                            admin.CourseInf(inputCourseByTeacher(admin));
                            continue;
                        case "37":
                            System.out.println("Просмотреть общую информацию о конкретном уроке конкретного курса");
                            System.out.print("Введите название урока: ");
                            String lessonName37= scanner.nextLine();
                            Lesson lesson37=admin.findLessonByNameForCourse(inputCourseByTeacher(admin), lessonName37);
                            admin. CourseInf(lesson37, inputCourseByTeacher(admin));
                            continue;
                        case "38":
                            System.out.println("Удалить курс");
                            System.out.print("Введите название курса: ");
                            String courseName38= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName38= scanner.nextLine();
                            Teacher teacher38 =admin.findTeacherByName(teacherName38);
                            admin.deleteCourse(courseName38, teacher38);
                            continue;
                        case "39":
                            System.out.println("Удалить урок");
                            System.out.print("Введите название курса: ");
                            String courseName39= scanner.nextLine();
                            System.out.print("Введите имя учителя: ");
                            String teacherName39= scanner.nextLine();
                            Teacher teacher39 =admin.findTeacherByName(teacherName39);
                            System.out.print("Введите название урока: ");
                            String lessonName39= scanner.nextLine();
                            admin.deleteLesson(courseName39, teacher39,lessonName39);
                            continue;
                        case "40":
                            System.out.println("Удалить материал из лекции");
                            System.out.print("Введите имя учителя: ");
                            String teacherName40= scanner.nextLine();
                            Teacher teacher40 =admin.findTeacherByName(teacherName40);
                            System.out.print("Введите название курса: ");
                            String courseName40= scanner.nextLine();
                            Course course40= admin.findCourseByNameAndTeacher(courseName40, teacherName40);
                            System.out.print("Введите название урока: ");
                            String lessonName40= scanner.nextLine();
                            System.out.print("Введите название материала: ");
                            String materialTitle = scanner.nextLine();
                            LearningMaterial material= TeacherService.findMaterial(teacher40,course40,lessonName40, materialTitle);
                            admin.removeMaterialToLecture(teacher40, course40, lessonName40, material);
                            continue;
                        case "41":
                            System.out.println("Просмотреть работу студента");
                            System.out.print("Введите название урока: ");
                            String lessonName41= scanner.nextLine();
                            admin.checkAssignment(inputCourseByTeacher(admin),lessonName41, inputStudent(admin));
                            continue;
                        case "42":
                            System.out.println("Просмотреть контент урока");
                            System.out.print("Введите название урока: ");
                            String lessonName42= scanner.nextLine();
                            admin. viewLessonContent(inputCourseByTeacher(admin), lessonName42);
                            continue;
                        case "43":
                            System.out.println("Удалить конкретное задание конкретного студентом");
                            System.out.print("Введите название урока: ");
                            String lessonName43= scanner.nextLine();
                            admin.removeAssignment(inputStudent(admin), inputCourseByTeacher(admin), lessonName43);
                            continue;
                        default:
                            System.out.println("Неверный выбор!");
                            block=0;
                            continue;
                    }
                }

            }
            if(block==0){continue;}


        }

    }

    public static Student inputStudent(Admin admin)
    { Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя студента: ");
        String studentName = scanner.nextLine();
        Student student=admin.findStudentByName(studentName);
        return student;
    }
    public static Course inputCourseByTeacher(Admin admin)
    { Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название курса: ");
        String courseName= scanner.nextLine();
        System.out.print("Введите имя учителя: ");
        String teacherName= scanner.nextLine();
        Course course= admin.findCourseByNameAndTeacher(courseName, teacherName);
        return course;
    }
}
