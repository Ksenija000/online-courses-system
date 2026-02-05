package com.model;

import com.service.CourseService;
import com.service.LessonService;
import com.service.StudentService;
import com.service.TeacherService;
import com.storage.Database;

import java.util.List;
import java.util.ArrayList;

public class Student extends User
{
    private List<Course> registration_courses; //Список курсов на которые записан

             public Student(String name, String email, String password){
        super(name, email, password);
        this.registration_courses=new ArrayList<>();
             }

//запись на курс
             public  void  registrationCourses(Course course)
             {
                 if(!registration_courses.contains(course)){
                 this.registration_courses.add(course);
                 //добавление в бд(пока список)
                 Database.progressCourses.add(new ProgressCourse(course, getName()));
                 System.out.println("Вы записаны на курс "+course.getName_course());
                 }
             }
    //отметка пройденного урока
    public void completeLesson (Course course, Lesson lesson){
        for (ProgressCourse progress_course : Database.progressCourses){
            if(progress_course.getCourse_progress().equals(course) &&
                    progress_course.getName_student_progress().equals(getName())){
                progress_course.completeLesson(lesson);
                break;
            }
        }
    }

    //список курсов на которых записан
    public List<Course> getRegistration_courses(){
        return registration_courses;
    }

    //количество курсов на которых записан
    public int getRegistrationCoursesCount(){
     return getRegistration_courses().size();
    }

    //список всех существующих курсов
    public List<Course> getAllCourses(){
        return Database.courses;
    }

    //процент завершения курса
    public  double percentCourse(Course course){
        if (course==null){
            return 0.0;
        }
        for (ProgressCourse progress_course : Database.progressCourses){
            if(progress_course.getCourse_progress().equals(course) &&
                    progress_course.getName_student_progress().equals(getName())){
                return progress_course.percentCourse();
            }
        }
        return 0.0;
    }

    // Получение среднего балла за курс
    public double GPACourse(Course course) {
        if (course==null){
            return 0.0;
        }
        for (ProgressCourse progress_course : Database.progressCourses) {
            if (progress_course.getCourse_progress().equals(course) &&
                    progress_course.getName_student_progress().equals(getName())) {
                return progress_course.getGPA();
            }
        }
        return 0.0;
    }
    // Получение оценки за конкретный урок
    public Double getGradeForLessonForStudent(Course course,String lessonName){
        if (course==null){
            return 0.0;
        }
        for (ProgressCourse progress_course: Database.progressCourses){
            if (progress_course.getCourse_progress().equals(course) &&
                    progress_course.getName_student_progress().equals(getName())) {
              return   progress_course. getGradeForLesson(lessonName);
            }
        }
        return null;
    }

    // Получение всех оценок за курс
  public void getGradesForCourse(Course course) {
        if (course==null){
            return;
        }
        for (ProgressCourse progress_course : Database.progressCourses) {
            if (progress_course.getCourse_progress().equals(course) &&
                    progress_course.getName_student_progress().equals(getName())) {
                 progress_course.getGrades();
            }
        }
    }

    //завершён ли указанный курс
    public  void getCompletedCourse(Course course){
        if (course==null){
            return;
        }
        for (ProgressCourse progress_course : Database.progressCourses){
            if(progress_course.getCourse_progress().equals(course) &&
                    progress_course.getName_student_progress().equals(getName())){
                if(progress_course.getCompleted_course()){
                    System.out.println("Завершён");
                }
                else { System.out.println("Не завершён");}
            }
        }
    }

    //количество пройденных уроков в указанном курсе
    public  int   completeLessonCount(Course course){
        if (course==null){
            return 0;
        }
        for (ProgressCourse progress_course : Database.progressCourses){
            if(progress_course.getCourse_progress().equals(course) &&
                    progress_course.getName_student_progress().equals(getName())){
                return progress_course.completeLessonCount();
            }
        }
        return 0;
    }
    //список пройденных уроков в указанном курсе
    public   List<Lesson>    completeLessonListCourse(Course course){
        if (course==null){
            return null;
        }
        for (ProgressCourse progress_course : Database.progressCourses){
            if(progress_course.getCourse_progress().equals(course) &&
                    progress_course.getName_student_progress().equals(getName())){
                return progress_course.getLesson_progress();
            }
        }
        return new ArrayList<>();
    }

    //общая информация о конкретном курсе
    public  void  courseInf(Course course){
        if(Database.courses.contains(course)) {
        course.informationOutput();
        }
    }

    //общая информация о конкретном уроке курса на который записан студент
    public  void  lessonInf(Course course, Lesson lesson){
        if(registration_courses.contains(course)) {
            if (course.getLesson_list().contains(lesson)){
            informationOutput();
            }
        }
    }

  //список пройденных уроков на конкретном курсе (их количество) +% +баллы (для конкретного курса)
  public   void  completeLessonListSpecificCourse(Course course ){
      if (course==null){
          return;
      }
   if (registration_courses.contains(course)){
      System.out.println("///Курс: "+course.getName_course());
      System.out.print("---Завершён ли курс: ");
      getCompletedCourse(course);
      System.out.println("---Количество уроков на курсе "+course.getName_course()+": "+ course.getLessonListCount());

      System.out.println("---Список уроков курса "+course.getName_course()+": ");

      for (Lesson lesson : course.getLesson_list()) {
          System.out.print(lesson.getName_lession());
          if (completeLessonListCourse(course).contains(lesson)){
              System.out.println(": пройден");
          }
          else {  System.out.println(": не пройден");}

          if (getGradeForLessonForStudent(course,lesson.getName_lession())==null){
              System.out.println("За данный урок нет оценки");
          }
          else {System.out.println("Оценка за данный урок: "+getGradeForLessonForStudent(course,lesson.getName_lession()));
          }

      }
      System.out.println("---Процент завершения курса "+ percentCourse(course));
      System.out.println("---Средний балл за курс "+  GPACourse( course));
   }
  }
    //список пройденных уроков на всех курсах (их количество) +% +баллы (использует функцию выше (completeLessonListSpecificCourse))
    public   void  completeLessonListEveryCourse(){
        System.out.println("Записаны на: "+getRegistrationCoursesCount()+" курсов");
                 for (Course course :registration_courses ) {
                     completeLessonListSpecificCourse(course);
                 }

    }

    //**проверка того записан ли на указанный курс
    public  boolean   registrationCoursesCheck(Course course){
        if (course==null){
            return false;
        }
        return registration_courses.contains(course);
    }

    //вывод информации
    public void informationOutput(){
        super.informationOutput();
        System.out.println("Курсы на которые записан: ");
        for (Course course : registration_courses) {
            System.out.print( course.getName_course()+ " Процент завершения: "+ percentCourse(course)+ " | ");
            getCompletedCourse(course);
        }
    }

    /////////////////////////////////////////////////

    //вывод имён преподавателей с конкретной специальностью
    public void teacherNameLisBySpecialisation(String specialisation){
        StudentService.teacherNameLisBySpecialisation(specialisation);
    }

    //вывод имён преподавателей ведущих курс с определённым названием
    public void teacherNameLisByCourse(String courseName){
        StudentService.teacherNameLisByCourse(courseName);
    }

    //вывод всех уроков указанного курса указанного преподавателя тип которых соответствует указанному (если он записан на курс)
    public void lessonNameListByType(Course course, String type){
                 if (!getRegistration_courses().contains(course)){
                     System.out.println("Запишитесь на курс чтобы посмотреть детальную информацию");
                     return;
                 }
                 if (course==null){
                     return ;
                 }
        StudentService.lessonNameListByType(course,type);
    }

    // Поиск урока в курсе по названию урока и курса для своих урсов
    public  Lesson  findLessonByNameForCourse(Course course, String lessonName){
        if (!getRegistration_courses().contains(course)){
            System.out.println("Запишитесь на курс чтобы посмотреть детальную информацию");
            return null;
        }
        return LessonService.findLessonInCourse(course,lessonName);
    }

    // поиск учителя по имени
    public  Teacher findTeacherByName(String teacherName){
        return StudentService.findTeacherByName(teacherName);
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

    //информация о конкретном уроке конкретного курса на который записан
    public  void  LessonInf(Lesson lesson, Course course){
        if (!getRegistration_courses().contains(course)){
            System.out.println("Запишитесь на курс чтобы посмотреть детальную информацию");
            return;
        }
        if ((course==null)||(lesson==null)){
            return ;
        }
        LessonService.LessonInf(lesson,course);
    }

    //информация о преподавателе
    public void teacherInf(Teacher teacher) {
        if (!Database.teachers.contains(teacher)) {
            System.out.println("Преподаватель в системе не найден");
            return;
        }
        if (teacher==null){
            return ;
        }
        teacher.informationOutput();
    }

    //вывод списка курсов определённого преподавателя
    public List<Course> getCreated_courseForTeacher(Teacher teacher){
        if (teacher==null){
            return null;
        }
        return TeacherService.getCreated_courseForTeacher(teacher);
    }

    //покинуть курс
    public void leaveCourse(Course course){
        if (course==null){
            return ;
        }
        if (!getRegistration_courses().contains(course)){
            System.out.println("Вы не записаны на курс "+course.getName_course());
            return;
        }
        course.getStudent_list().remove(this);
        getRegistration_courses().remove(course);
        ProgressCourse progressCourse1=null;
        for (ProgressCourse progressCourse: Database.progressCourses){
            if((progressCourse.getName_student_progress().equals(getName()))&&
                    (progressCourse.getCourse_progress().equals(course))){
                progressCourse1=progressCourse;
            }
        }
        Database.progressCourses.remove(progressCourse1);
        for (Lesson lesson: course.getLesson_list()){
            if(lesson instanceof AssignmentLesson){
                AssignmentLesson assignmentLesson=(AssignmentLesson)lesson;
                for (AssignmentStudent assignmentStudent:assignmentLesson.getAssignmentStudent()){
                    if(assignmentStudent.getStudent().equals(this)){
                        assignmentLesson.getAssignmentStudent().remove(assignmentStudent);
                    }
                }
            }
        }
        System.out.println("Вы покинули курс");
    }

    /// ///////////////////////////////////////////////////////////////////////////////////задания
    //просмотр контента урока
    public void viewLessonContent(Course course, String lessonName) {
        if (course==null){
            return ;
        }
        if (!getRegistration_courses().contains(course)){
            System.out.println("Запишитесь на курс чтобы посмотреть детальную информацию");
            return;
        }
        Lesson lesson = findLessonByNameForCourse(course, lessonName);
        if (lesson != null) {
            lesson.displayContent();

            if (lesson instanceof LectureLesson) {
                completeLesson(course, lesson);
            }
        }
    }

    //отправка выполненного задания
    public void submitAssignment(Course course, String lessonName, String answerText, String filePath) {
        if (course==null){
            return ;
        }
        if (!getRegistration_courses().contains(course)){
            System.out.println("Запишитесь на курс чтобы посмотреть детальную информацию");
            return;
        }

            Lesson lesson = findLessonByNameForCourse(course, lessonName);
            if (lesson instanceof AssignmentLesson) {
                AssignmentLesson assignment = (AssignmentLesson) lesson;

                for (AssignmentStudent assignmentStudent:assignment.getAssignmentStudent()){
                    if(assignmentStudent.getStudent().equals(this)){
                        System.out.println(" Задание уже было отправлено на проверку");
                        return;
                    }
                }

                AssignmentStudent studentAssignment = new AssignmentStudent(this, assignment, answerText, filePath);

                assignment.addAssignmentStudent(studentAssignment);
                completeLesson(course, lesson);
                System.out.println(" Задание отправлено на проверку");
            } else {
                System.out.println(" Это не задание!");
            }
    }

    //удаление отправленного задания данным студентом по данному уроку
    public void  removeAssignment(Course course, String lessonName) {
        if (course==null){
            return ;
        }
        if (!getRegistration_courses().contains(course)){
            System.out.println("Запишитесь на курс чтобы посмотреть детальную информацию");
            return;
        }
        Lesson lesson = findLessonByNameForCourse(course, lessonName);
        if (lesson instanceof AssignmentLesson) {
            AssignmentLesson assignment = (AssignmentLesson) lesson;

            for (AssignmentStudent assignmentStudent:assignment.getAssignmentStudent()){
                if(assignmentStudent.getStudent().equals(this)){
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

    /// ///////////////////////////////////////////////////////////////////////////////конец заданий
}
