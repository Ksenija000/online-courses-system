package com.model;
import com.service.*;
import com.storage.Database;

import java.util.List;

public class Admin extends User
{
    public Admin( String name, String email, String password){
        super(name, email, password);
    }

    //удаление пользователя
    public  void deleteUser(User user){
        if(Database.users.contains(user)) {
            Database.users.remove(user);

            if (user instanceof Student) {
                Database.students.remove((Student) user);
            } else if (user instanceof Teacher) {
                Database.teachers.remove((Teacher) user);
            } else if (user instanceof Admin) {
                Database.admins.remove((Admin) user);
            }

            System.out.println("Пользователь " + user.getName() + " удалён");
        }
        else {
            System.out.println("Такого пользователя не существует");
        }
    }


    //просмотр всех пользователей и их информации
    public void viewUsers (){
        System.out.println("Все пользователи: ");
        System.out.println("Преподаватели: ");
        for (Teacher teacher :  Database.teachers){
            teacher.getName();
        }
        System.out.println("Студенты: ");
        for (Student student:  Database.students){
            student.getName();
        }
        System.out.println("Администраторы: ");
        for (Admin admin:  Database.admins){
            admin.getName();
        }
    }

    //просмотр конкретного пользователя и информации о нём
    public void viewInfoUser (User user){
      user.informationOutput();
    }

    //просмотр статистики системы
    public void  viewSystemStatistics(){
        System.out.println("Статистика системы: ");
        System.out.println("    Всего пользователей: "+ Database.users.size());
        System.out.println("        В том числе студентов: "+Database.students.size());
        System.out.println("        В том числе преподавателей: "+Database.teachers.size());
        System.out.println("        В том числе администраторов: "+Database.admins.size());
        System.out.println(" ");

        //преподаватели
        System.out.println("    Всего курсов в системе: "+ Database.courses.size());

        if (!Database.teachers.isEmpty()) {
            int averageCoursesPerTeacher = Database.courses.size() / Database.teachers.size();
            int averageStudentsPerTeacher = Database.students.size() / Database.teachers.size();
            System.out.println("        В среднем у преподавателя: " + averageCoursesPerTeacher + " курсов и " + averageStudentsPerTeacher + " студентов");

            Teacher mostActive = Database.teachers.getFirst();
            for (Teacher teacher : Database.teachers) {
                if (teacher.createdCourseCount() > mostActive.createdCourseCount()) {
                    mostActive = teacher;
                }
            }
            System.out.println("            Самый активный преподаватель: " + mostActive.getName() + ": " + mostActive.createdCourseCount() + " курсов");
        }
        System.out.println(" ");

        //студенты
        if (!Database.students.isEmpty()) {
            int totalStudentCourses = 0;
            Student mostActiveStudent = Database.students.getFirst();

            for (Student student : Database.students) {
                int courseCount = student.getRegistration_courses().size();
                totalStudentCourses += courseCount;
                if (courseCount > mostActiveStudent.getRegistration_courses().size()) {
                    mostActiveStudent = student;
                }
            }

            System.out.println("    Всего записей на курсы: " + totalStudentCourses);

            System.out.println("        В среднем на студента: " + totalStudentCourses / Database.students.size() + " курсов");

            System.out.println("            Самый активный студент: " + mostActiveStudent.getName() + " записан на: " + mostActiveStudent.getRegistrationCoursesCount() + " курсов");
        }
    }

    //функции из других классов

    ///////////////////////////////////методы студента
    //количество курсов на которые записан конкретный студент
    public int RegistrationCoursesCountForStudent(Student student){
        if(Database.students.contains(student)) {
        return student.getRegistrationCoursesCount();
        }
        else {System.out.println("Такой студент в системе не зарегистрирован");
            return 0;}
    }
    //список курсов на которых записан конкретный студент
    public List<Course> Registration_coursesForStudent(Student student){
        if(Database.students.contains(student)) {
        return student.getRegistration_courses();
        }
        else {System.out.println("Такой студент в системе не зарегистрирован");
            return null;}
    }

    //процент завершения курса конкретным студентом
    public  double percentCourseForStudent(Student student, Course course){
        if(Database.students.contains(student)) {
        return student.percentCourse(course);
        }
        else {System.out.println("Такой студент в системе не зарегистрирован");
            return 0.0;}
    }

    // Получение оценки за конкретный урок для конкретного студента
    public Double GradeForLessonForStudent(Student student, Course course, String lessonName){
        if(Database.students.contains(student)) {
        return student.getGradeForLessonForStudent(course,lessonName);
        }
        else {System.out.println("Такой студент в системе не зарегистрирован");
            return 0.0;}
    }

    // Получение среднего балла конкретного студента за курс
    public double GPACourseForStudent(Student student, Course course) {
        if(Database.students.contains(student)) {
        return student.GPACourse(course);
        }
        else {System.out.println("Такой студент в системе не зарегистрирован");
            return 0.0;}
    }

    // Получение всех оценок за конкретного студента курс
    public void GradesForCourseForStudent(Student student, Course course) {
        if(Database.students.contains(student)) {
           student.getGradesForCourse(course);
        }
        else {System.out.println("Такой студент в системе не зарегистрирован");
            return;}
    }

    //завершён ли указанный курс конкретным студентом
    public  void CompletedCourseForStudent(Student student, Course course){
        if(Database.students.contains(student)) {
       student.getCompletedCourse(course);
        }
        else {System.out.println("Такой студент в системе не зарегистрирован");
        }
    }

    //проверка того записан ли на указанный курс конкретный студент
    public  boolean   registrationCoursesCheckForStudent(Student student, Course course){
        if(Database.students.contains(student)) {
        return student.registrationCoursesCheck(course);
        }
        else {System.out.println("Такой студент в системе не зарегистрирован");
                return false; }
    }
 /////////////////////////////////////////конец методов студента

////////////////////////////////////////////методы учителя
    //список кусов конкретного преподавателя
    public List<Course> getCreated_courseForTeacher(Teacher teacher){
     return TeacherService.getCreated_courseForTeacher(teacher);
    }

    //количество кусов конкретного преподавателя
    public int createdCourseCountForTeacher(Teacher teacher){
        if(Database.teachers.contains(teacher)) {
        return teacher.createdCourseCount();}
        else {System.out.println("Такой учитель в системе не зарегистрирован");
            return 0; }
    }

    //список специальностей конкретного преподавателя
    public List<String> SpecialisationForTeacher(Teacher teacher){
        if(Database.teachers.contains(teacher)) {
        return teacher.getSpecialisation();
        }
        else {System.out.println("Такой учитель в системе не зарегистрирован");
            return null; }
    }

    //удаление конкретной специальности конкретного преподавателя
    public boolean removeSpecializationForTeacher(Teacher teacher, String specialisation) {
        if(Database.teachers.contains(teacher)) {
        return teacher.removeSpecialization(specialisation);}
        else {System.out.println("Такой учитель в системе не зарегистрирован");
            return false; }
    }

    // добавление конкретной специальности конкретного преподавателя
    public  void  addSpecialisationForTeacher(Teacher teacher, String specialisation){
        if(Database.teachers.contains(teacher)) {
        teacher.addSpecialisation(specialisation);
        }
        else {System.out.println("Такой учитель в системе не зарегистрирован");
        }
    }

    //получение списка студентов данного преподавателя
    public List<Student> StudentListForTeacher(Teacher teacher){
        if(Database.teachers.contains(teacher)) {
        return teacher.getStudent();
    }
        else {System.out.println("Такой учитель в системе не зарегистрирован");
    return null;}
    }

    //получение количества студентов данного преподавателя
    public int StudentCountForTeacher(Teacher teacher){
        if(Database.teachers.contains(teacher)) {
       return teacher.getStudentCount();
        }
        else {System.out.println("Такой учитель в системе не зарегистрирован");
            return 0;}
    }

    //количество пройденных уроков на определённом курсе данного преподавателя определённым студентом
    public int lessonCountCourseStudentForTeacher(Teacher teacher, Student student, Course course){
        return TeacherService.lessonCountCourseStudent(teacher, student,  course);
    }


    //список пройденных уроков на определённом курсе данного преподавателя определённым студентом
    public List<Lesson>  lessonListCourseStudentForTeacher(Teacher teacher, Student student, Course course){
     return TeacherService.lessonListCourseStudentForTeacher(teacher, student,course);
    }

    //количество и список пройденных уроков на каждом курсе данного преподавателя каждом студентом + прогресс
    public void completeLessonEveryCourseEveryStudentForTeacher(Teacher teacher){
        if(Database.teachers.contains(teacher)) {
       teacher.completeLessonEveryCourseEveryStudent();
        }
        else {System.out.println("Такой учитель в системе не зарегистрирован");}
    }

    //количество и список пройденных уроков на определённом курсе данного преподавателя каждом студентом+ прогресс
    public void completeLessonCourseEveryStudentForTeacher(Teacher teacher, Course course){
       TeacherService.completeLessonCourseEveryStudentForTeacher(teacher,course);
    }

    //количество и список пройденных уроков на определённом курсе данного преподавателя определённым студентом + прогресс
    public void completeLessonCourseStudentForTeacher(Teacher teacher,Course course, Student student){
      TeacherService.completeLessonCourseStudentForTeacher(teacher, course,student);
    }

    //удаление оценки студента за конкретный урок конкретного курса конкретного преподавателя
    public void removeStudentGradeForTeacher(Teacher teacher, Student student, Course course, String lessonName) {
        TeacherService.removeStudentGradeForTeacher(teacher,student,course,lessonName);
    }
    ////////////////////////////////////////////конец методов учителя

    /// ///////////////////////////////////прочие методы
    //вывод имён преподавателей с конкретной специальностью
    public void teacherNameLisBySpecialisation(String specialisation){
        StudentService.teacherNameLisBySpecialisation(specialisation);
    }

    //вывод имён преподавателей ведущих курс с определённым названием
    public void teacherNameLisByCourse(String courseName){
        StudentService.teacherNameLisByCourse(courseName);
    }

    //вывод всех студентов со средним балом за указанный курс указанного преподавателя из диапазона (от до)
    public void studentNameListByGPA(Course course, double from , double to){
        TeacherService.studentNameListByGPA(course, from,to);
    }

    //вывод студентов у которых процент завершения указанного курса указанного преподавателя из диапазона (от до)
    public void studentNameListByPercent(Course course, double from , double to){
        TeacherService.studentNameListByPercent(course,from,to);
    }

    //вывод всех уроков указанного курса указанного преподавателя тип которых соответствует указанному
    public void lessonNameListByType(Course course, String type){
      StudentService.lessonNameListByType(course,type);
    }

    //вывод всех студентов которые завершили/не завершили (передача значения как параметра) конкретный курс конкретного преподавателя
   public void studentNameListByCompleted_course(Course course, boolean completed){
      TeacherService.studentNameListByCompleted_course(course, completed);
    }

    // Поиск урока в курсе по названию урока и курса
    public  Lesson  findLessonByNameForCourse(Course course, String lessonName){
        return LessonService.findLessonInCourse(course,lessonName);
    }

    // поиск учителя по имени
    public  Teacher findTeacherByName(String teacherName){
       return StudentService.findTeacherByName(teacherName);
    }

    // поиск студента по имени
    public  Student findStudentByName(String studentName){
        return TeacherService.findStudentByName(studentName);
    }

    //поиск админа по имени
    public  Admin findAdminByName(String adminName ){
       return AdminService.findAdminByName(adminName);
    }

    //список названий всех существующих курсов в системе
    public void namesAllCourses(){
        for (Course course: Database.courses){
            System.out.println(course.getName_course());
        }
    }

    // Поиск курса по названию и учителю
    public  Course findCourseByNameAndTeacher(String courseName, String teacherName) {
        return CourseService.findCourseByNameAndTeacher(courseName,teacherName);
    }

    // Проверка существования учителя
    public  boolean teacherExists(Teacher teacher) {
        return TeacherService.teacherExists(teacher);
    }
    /// ///////////////////////////////////прочие методы конец


    //общая информация о конкретном курсе
    public  void  CourseInf(Course course){
        if(Database.courses.contains(course)) {
            course.informationOutput();
        }
    }

    //общая информация о конкретном уроке конкретного курса
    public  void  CourseInf(Lesson lesson, Course course){
       LessonService.LessonInf(lesson,course);
    }

    //список всех курсов системы
    public List<Course> getAllCourses(){
        return Database.courses;
    }

    //удаление курса по названию и учителю
        public void deleteCourse(String courseName, Teacher teacher) {
            CourseService.deleteCourseForAdmin(courseName, teacher);
        }

    //удаление урока по названию урока, курса и учителю
    public void deleteLesson(String courseName, Teacher teacher, String lessonName) {
        LessonService.deleteLessonForAdmin(courseName, teacher, lessonName);
    }

    /// /////////////////////////////////задания
    //удаление материала из лекции
    public void removeMaterialToLecture(Teacher teacher, Course course, String lessonName, LearningMaterial material) {
      TeacherService.removeMaterialToLecture(teacher,course,lessonName,material);
    }

    //проверка работы студента
    public void checkAssignment(Course course, String lessonName, Student student) {
        if (!Database.courses.contains(course)) {
            System.out.println("Курс " + course.getName_course() + " не найден в системе");
            return ;
        }
        if (!course.getStudent_list().contains(student)) {
            System.out.println("Студент"+student.getName()+" не записан как курс"+course.getName_course());
            return;
        }
        Lesson lesson =LessonService.findLessonInCourse(course, lessonName);
        if (lesson instanceof AssignmentLesson) {
            AssignmentLesson assignment = (AssignmentLesson) lesson;

            for (AssignmentStudent assignmentStudent : assignment.getAssignmentStudent()) {
                if (assignmentStudent.getStudent().equals(student)) {
                    assignmentStudent.informationOutput();
                    return;
                }
            }
            System.out.println("Студент не отправлял это задание");
        }
    }

    //просмотр контента урока
    public void viewLessonContent(Course course, String lessonName) {
        if(!Database.courses.contains(course)) {
       System.out.println("Такой курс в системе не зарегистрирован");
         return;
         }

        Lesson lesson = findLessonByNameForCourse(course, lessonName);
        if (lesson != null) {
            lesson.displayContent();
        }
    }

    //удаление отправленного задания данным студентом по данному уроку
    public void  removeAssignment(Student student, Course course, String lessonName) {
        if(!Database.students.contains(student)) {
            System.out.println("Такой студент в системе не зарегистрирован");
            return;
        }
        Lesson lesson = findLessonByNameForCourse(course, lessonName);
        if (lesson instanceof AssignmentLesson) {
            AssignmentLesson assignment = (AssignmentLesson) lesson;

            for (AssignmentStudent assignmentStudent:assignment.getAssignmentStudent()){
                if(assignmentStudent.getStudent().equals(student)){
                    assignment. removeSAssignmentStudent(assignmentStudent);
                    System.out.println("Работа удалена");
                    return;
                }
            }
            System.out.println("Данное задание не сдано");

        } else {
            System.out.println(" Это не задание!");
        }
    }

    /// ////////////////////////////////конец задания
}
