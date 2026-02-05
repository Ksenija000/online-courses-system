package com.model;

import com.service.TeacherService;
import com.storage.Database;

import com.service.CourseService;
import com.service.LessonService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Teacher extends User
{
    private  List<String> specialisation;//специализации преподаватнеля

    public Teacher( String name, String email, String password){
        super( name, email, password);
        this.specialisation=new LinkedList<>();
    }

    //создание курса
    public Course createdCourse( String name_course) {
        return CourseService.createCourse(this, name_course);
    }

    //добавление урока
    public void addLesson (Course course, String  name_lession, String  content,  String type){
        if (!Database.courses.contains(course)) {
            System.out.println("Такого курса в системе нет");
            return;
        }
            if (course.getName_teacher().equals(getName())) {
                Lesson new_lesson = new Lesson( name_lession, content, type);

               for (Lesson lesson: course.getLesson_list()){
                   if(lesson.getName_lession().equals(new_lesson.getName_lession()));
                   System.out.println("Урок с таким названием уже существует ");
                   return;
               }
                System.out.println("Урок добавлен");
                course.add_lesson_list(new_lesson);

                for (ProgressCourse progress: Database.progressCourses){
                    recalculateCompletionCourse(progress);
                }

            }
    }

    //пересчет завершения курса после добавления урока
    private void recalculateCompletionCourse(ProgressCourse progress) {
        Course course = progress.getCourse_progress();
        boolean wasCompleted = progress.getCompleted_course();

        boolean nowCompleted = !course.getLesson_list().isEmpty() && progress.getLesson_progress().size() == course.getLesson_list().size();

        progress.setCompleted_course(nowCompleted);

        if (wasCompleted && !nowCompleted) {
            System.out.println("Курс " + course.getName_course() + " больше не завершен для студента " + progress.getName_student_progress());
        }
    }


    //список кусов преподавателя
    public List<Course> getCreated_course(){
        List<Course> createdCourse = new ArrayList<>();
        for (Course course : Database.courses) {
            if (course.getName_teacher().equals(getName())) {
                createdCourse.add(course);
            }
        }
        return createdCourse;
    }
    //количество кусов преподавателя
    public int createdCourseCount(){
        return getCreated_course().size();
    }

    //список специальностей преподавателя
    public List<String> getSpecialisation(){
      return specialisation;
    }

    // добавление специальности
    public  void  addSpecialisation(String specialisation){
        if (!this.specialisation.contains(specialisation)){
            System.out.println("Специальность успешно добавлена");
            this.specialisation.add(specialisation);
        }
        else {   System.out.println("Такая специальность уже есть у вас");}
    }

   //удаление специальности
   public boolean removeSpecialization(String specialisation) {
        if (this.specialisation.contains(specialisation)){
           return this.specialisation.remove(specialisation);
        }
       return false;
   }

   //получение списка студентов данного преподавателя
    public List<Student> getStudent(){
        List<Student> students=new ArrayList<>();
        for (Course course : getCreated_course()){
            students.addAll(course.getStudent_list());
        }
        return students;
    }
    //получение количества студентов данного преподавателя
    public int getStudentCount(){
      int count=0;
        for (Course course : getCreated_course()){
            count += course.getStudent_list().size();
        }
        return count;
    }

    //количество уроков в указанном курсе преподавателя
    public  int   lessonCount(Course course){
        if(Database.courses.contains(course)) {
            if (course.getName_teacher().equals(getName())) {
                return course.getLessonListCount();
            }
        }
        return 0;
    }
    // список уроков на указанном курсе преподавателя
    public  List<Lesson>   lessonListCourse(Course course){
        if(Database.courses.contains(course)) {
            if (course.getName_teacher().equals(getName())) {
                return course.getLesson_list();
            }
        }
        return null;
    }
    //***количество пройденных уроков на определённом курсе определённым студентом
    public int lessonCountCourseStudent(Student student, Course course){
       return TeacherService.lessonCountCourseStudent(this, student,  course);
    }

    //***список пройденных уроков на определённом курсе определённым студентом
    public List<Lesson>  lessonListCourseStudent(Student student, Course course){
       return TeacherService.lessonListCourseStudentForTeacher(this,student,course);
    }

    //количество и список пройденных уроков на каждом курсе каждом студентом + прогресс
    public void completeLessonEveryCourseEveryStudent(){
        System.out.println("***Информация о прохождении курсов каждомы студентом:");
        for (Course course : getCreated_course()){
            for (Student student : course.getStudent_list()){
                System.out.println("///Студент "+student.getName()+":");
                student.completeLessonListSpecificCourse(course);
            }
        }
    }

    //количество и список пройденных уроков на определённом курсе каждом студентом + прогресс
    public void completeLessonCourseEveryStudent(Course course){
       TeacherService.completeLessonCourseEveryStudentForTeacher(this,course);
    }

    //количество и список пройденных уроков на определённом курсе определённым студентом + прогресс
    public void completeLessonCourseStudent(Course course, Student student){
       TeacherService.completeLessonCourseStudentForTeacher(this,course,student);
    }

//Начало блока оценки//////////////////////////////////////////////////////
// Выставление оценки студенту
public void gradeStudent(Student student, Course course, String lessonName, int score, String comment) {
    ProgressCourse progress = validateGradeOperation(student, course, lessonName,"выставлять оценки");
    if (progress != null) {
        progress.addGrade(lessonName, score, comment);
        progress.calculateGPA();
        System.out.println("Оценка " + score + " выставлена студенту " + student.getName() +
                " за урок '" + lessonName + "'");
    }
}

//удаление оценки студента
    public void removeStudentGrade(Student student, Course course, String lessonName) {
       TeacherService.removeStudentGradeForTeacher(this,student,course,lessonName);
    }

    // Общий метод проверок удаления выставления оценки
    public ProgressCourse validateGradeOperation(Student student, Course course, String lessonName, String operation) {
        if (!course.getName_teacher().equals(getName())) {
            System.out.println("Вы можете " + operation + " только по своим курсам");
            return null;
        }
        if (!course.getLesson_list().contains(LessonService.findLessonInCourse(course,lessonName))) {
            System.out.println("Урок "+lessonName+" в курсе "+course.getName_course()+" не найден");
            return null;
        }
        if (!student.getRegistration_courses().contains(course)) {
            System.out.println("Студент не записан на этот курс");
            return null;
        }

        for (ProgressCourse progress : Database.progressCourses) {
            if (progress.getCourse_progress().equals(course) &&
                    progress.getName_student_progress().equals(student.getName())) {
                return progress;
            }
        }

        System.out.println("Прогресс студента по курсу не найден");
        return null;
    }
    //Конец блока оценки///////////////////////////////////////

    //вывод информации о преподавателе
    public void informationOutput(){
  super.informationOutput();
        System.out.println("Список курсов преподавателя: ");
        for (Course course: getCreated_course()){
            System.out.println(course.getName_course());
        }
        System.out.println("Специализация: "+getSpecialisation());
        for (String specialisation: getSpecialisation()){
            System.out.println(specialisation);
        }
    }

    //вывод информации об одном из своих курсов
    public  void  CourseInf(Course course){
        if(getCreated_course().contains(course)) {
            course.informationOutput();
        }
    }

    //вывод информации об одном из своих уроков
    public  void  LessonInf(Course course, Lesson lesson){
        if(getCreated_course().contains(course)) {
            if(course.getLesson_list().contains(lesson)){
            course.informationOutput();
            }
        }
    }

    //информация о студенте своём
 public void studentInf(Student student) {
     if (!getStudent().contains(student)) {
         System.out.println("Преподаватель может смотреть информацию только о своих студентах");
         return;
     }
    student.informationOutput();
 }

    //удаление своего курса по названию
    public void deleteCourse(String courseName) {
        CourseService.deleteCourseForTeacher(courseName, this);
    }

    //удаление урока из своего курса по названию урока и курса
    public void deleteLesson(String courseName, String lessonName) {
        LessonService.deleteLessonForTeacher(courseName, this, lessonName);
    }

    /// ///////////////////////////////////////////////////////////
    //вывод всех студентов со средним балом за указанный курс указанного преподавателя из диапазона (от до)
    public void studentNameListByGPA(Course course, double from , double to){
        if (!course.getName_teacher().equals(getName())){
            System.out.println("Преподаватель может смотреть информацию только о своих курсах");
        return;}
        TeacherService.studentNameListByGPA(course, from,to);
    }

    //вывод студентов у которых процент завершения указанного курса указанного преподавателя из диапазона (от до)
    public void studentNameListByPercent(Course course, double from , double to){
        if (!course.getName_teacher().equals(getName())){
            System.out.println("Преподаватель может смотреть информацию только о своих курсах");
            return;}
        TeacherService.studentNameListByPercent(course,from,to);
    }

    //вывод всех студентов которые завершили/не завершили (передача значения как параметра) конкретный курс конкретного преподавателя
    public void studentNameListByCompleted_course(Course course, boolean completed){
        if (!course.getName_teacher().equals(getName())){
            System.out.println("Преподаватель может смотреть информацию только о своих курсах");
            return;}
        TeacherService.studentNameListByCompleted_course(course, completed);
    }

    // Поиск урока в курсе по названию урока и курса для своих урсов
    public  Lesson  findLessonByNameForCourse(Course course, String lessonName){
        if (!course.getName_teacher().equals(getName())){
            System.out.println("Преподаватель может смотреть информацию только о своих курсах");
            return null;}
        return LessonService.findLessonInCourse(course,lessonName);
    }

    // поиск студента по имени для своих
    public  Student findStudentByName(String studentName){
        if (!getStudent().contains(TeacherService.findStudentByName(studentName))) {
            System.out.println("Преподаватель может смотреть информацию только о своих студентах");
            return null;
        }
        return TeacherService.findStudentByName(studentName);
    }

    // Поиск курса по названию и учителю
    public Course findCourseByNameAndTeacher(String courseName) {
     return CourseService. findCourseByNameAndTeacher(courseName,getName());
    }

    /// //////////////////////////////////////////

    /// ////////////////////////////////////////////////задания
    //создание лекции
    public void addLectureLesson(Course course, String name_lession, String content) {
        if (!getCreated_course().contains(course)) {
            return;
        }
            LectureLesson newLesson = new LectureLesson(name_lession, content);
            course.add_lesson_list(newLesson);
    }

    //добавление материала к лекции
    public void addMaterialToLecture(Course course, String lessonName, LearningMaterial material) {
        if (!getCreated_course().contains(course)) {
            System.out.println("Курс не принадлежит преподавателю");
            return;
        }
            Lesson lesson = findLessonByNameForCourse(course, lessonName);
        if (lesson==null){return;}

            if (lesson instanceof LectureLesson) {
                ((LectureLesson) lesson).addMaterial(material);
                System.out.println("Материал добавлен к лекции");
            } else {
                System.out.println("Это не лекция!");
            }
    }

    //удаление материала из лекции
    public void removeMaterialToLecture( Course course, String lessonName, LearningMaterial material) {
        TeacherService.removeMaterialToLecture(this,course,lessonName,material);
    }

    //создание задания для студентов
    public void addAssignmentLesson(Course course, String name_lession, String taskDescription, String deadline, int maxScore) {
        if (!getCreated_course().contains(course)) {
            return;
        }
            AssignmentLesson newLesson = new AssignmentLesson(name_lession, taskDescription, deadline, maxScore);
            course.add_lesson_list(newLesson);
    }

    //проверка работы студента
    public void checkAssignment(Course course, String lessonName, Student student) {
         if (!getCreated_course().contains(course)) {
             System.out.println("Курс не принадлежит преподавателю");
            return;
        }
            Lesson lesson = findLessonByNameForCourse(course, lessonName);
        if (lesson==null){return;}

        if (!course.getStudent_list().contains(student)) {
            System.out.println("Студент"+student.getName()+" не записан как курс"+course.getName_course());
            return;
        }

            if (lesson instanceof AssignmentLesson) {
                AssignmentLesson assignment = (AssignmentLesson) lesson;

                for (AssignmentStudent assignmentStudent : assignment.getAssignmentStudent()) {
                    if (assignmentStudent.getStudent().equals(student)) {
                        assignmentStudent.informationOutput();
                        assignmentStudent.setStatus("проверено");
                        return;
                    }
                }
                System.out.println("Студент не отправлял это задание");
            }
    }

    /// /////////////////////////////////////////////////конец заданий

}
