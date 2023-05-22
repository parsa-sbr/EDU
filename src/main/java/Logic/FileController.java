package Logic;

import Module.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FileController {

    Logger log = LogManager.getLogger(FileController.class);

    public static FileController fileController;

    private FileController() {

    }

    public static FileController getInstance() {
        if (fileController == null){
            fileController = new FileController();
        }
        return fileController;
    }

    public void deleteLesson(String id, User user) {
        File file = new File("src/main/java/Files/Lessons.txt");
        Lesson lesson;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            lesson = gson.fromJson(userjson,Lesson.class);
            if (!lesson.getLessonNum().equals(id) || !lesson.getCollege().equals(user.getCollege())) {
                result += gson.toJson(lesson);
                result += "\n";
            }
            else {
                deleteLessonOfGrade(lesson);
            }
        }

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Lessons.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("file named " + file.getName() + " closed");

    }

    public void deleteLessonOfGrade(Lesson lesson) {
        File file = new File("src/main/java/Files/Students.txt");
        Student student;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()) {
            userjson = scanner.nextLine();
            student = gson.fromJson(userjson,Student.class);
            for (Grade g : student.getGrades()) {
                if (g.getLesson().getLessonNum().equals(lesson.getLessonNum())){
                    student.getGrades().remove(g);
                    break;
                }
            }
            result += gson.toJson(student);
            result += "\n";
        }

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Students.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("file named " + file.getName() + " closed");
    }

    public void addLesson(String name, String teacherName, String collegeName, String classTime, String unit,
                          String examDate, String id, String type) {

        Lesson lesson = new Lesson(name, teacherByName(teacherName), collegeByName(collegeName), classTime,
                Integer.parseInt(unit), Integer.parseInt(examDate), id, typeByName(type));

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String userjson=gson.toJson(lesson);
        try {
            FileWriter writer= new FileWriter("src/main/java/Files/Lessons.txt", true);
            log.info("file named " + "Lessons.txt" + " opened");
            writer.write(userjson + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("file named " + "Lessons.txt" + " closed");
    }

    public void deleteTeacher(String id, User user) {
        File file = new File("src/main/java/Files/Teachers.txt");
        Teacher teacher;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            teacher = gson.fromJson(userjson,Teacher.class);
            if (!teacher.getEduId().equals(id) || !teacher.getCollege().equals(user.getCollege())) {
                result += gson.toJson(teacher);
                result += "\n";
            }
            else {
                log.info("file named " + file.getName() + " closed");

                deleteTutor(teacher);
                deleteTeacherOfLesson(teacher);
                deleteTeacherOfLessonsOfGrades(teacher);
            }
        }

        log.info("file named " + file.getName() + " opened");

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Teachers.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("file named " + file.getName() + " closed");
    }

    public void deleteTutor(Teacher teacher) {
        File file = new File("src/main/java/Files/Students.txt");
        Student student;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()) {
            userjson = scanner.nextLine();
            student = gson.fromJson(userjson,Student.class);
            if (student.getTutor() != null && student.getTutor().getEduId().equals(teacher.getEduId())){
                student.setTutor(null);
            }
            result += gson.toJson(student);
            result += "\n";
        }

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Students.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("file named " + file.getName() + " closed");
    }

    private void deleteTeacherOfLesson(Teacher teacher) {
        File file = new File("src/main/java/Files/Lessons.txt");
        Lesson lesson;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()) {
            userjson = scanner.nextLine();
            lesson = gson.fromJson(userjson,Lesson.class);
            if (lesson.getTeacher().getEduId().equals(teacher.getEduId())){
                lesson.setTeacher(null);
            }
            result += gson.toJson(lesson);
            result += "\n";
        }

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Lessons.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("file named " + file.getName() + " closed");
    }

    private void deleteTeacherOfLessonsOfGrades(Teacher teacher) {
        File file = new File("src/main/java/Files/Students.txt");
        Student student;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()) {
            userjson = scanner.nextLine();
            student = gson.fromJson(userjson,Student.class);
            for (Grade g : student.getGrades()) {
                if (g.getLesson().getTeacher().getEduId().equals(teacher.getEduId())){
                    g.getLesson().setTeacher(null);
                }
            }
            result += gson.toJson(student);
            result += "\n";
        }

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Students.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("file named " + file.getName() + " closed");
    }

    public void addTeacher(String username, String password, String name, String nationalId, String eduId,
                           String phone, String email, String college, String rank, String pos, String room, boolean tutor) {

        Teacher teacher = new Teacher(username, password, name, nationalId, eduId, phone, email, collegeByName(college),
                rankByName(rank), posByName(pos), room, tutor);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String userjson=gson.toJson(teacher);
        try {
            FileWriter writer= new FileWriter("src/main/java/Files/Teachers.txt", true);
            log.info("file named " + "Teachers.txt" + " opened");
            writer.write(userjson + "\n");
            writer.close();
            log.info("file named " + "Teachers.txt" + " closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(User user) {
        File file = new File("src/main/java/Files/Students.txt");
        Student student;
        Student newStudent = (Student) user;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            student = gson.fromJson(userjson,Student.class);
            if (student.getEduId().equals(newStudent.getEduId())) {
                result += gson.toJson(newStudent);
                result += "\n";
            }
            else {
                result += gson.toJson(student);
                result += "\n";
            }
        }

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Students.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("file named " + file.getName() + " closed");
    }

    public void updateLesson(String name, String teacherName, String collegeName, String classTime, String unit,
                          String examDate, String id, String type) {

        Lesson newLesson = new Lesson(name, teacherByName(teacherName), collegeByName(collegeName), classTime,
                Integer.parseInt(unit), Integer.parseInt(examDate), id, typeByName(type));

        File file = new File("src/main/java/Files/Lessons.txt");
        Lesson lesson;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            lesson = gson.fromJson(userjson,Lesson.class);
            if (lesson.getLessonNum().equals(newLesson.getLessonNum())) {
                result += gson.toJson(newLesson);
                result += "\n";
            }
            else {
                result += gson.toJson(lesson);
                result += "\n";
            }
        }

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Lessons.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("file named " + file.getName() + " closed");

        updateLessonOfGrades(newLesson);


    }

    public void editTeacher(User user) {
        File file = new File("src/main/java/Files/Teachers.txt");
        Teacher teacher;
        Teacher newTeacher = (Teacher) user;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            teacher = gson.fromJson(userjson,Teacher.class);
            if (teacher.getEduId().equals(newTeacher.getEduId())) {
                result += gson.toJson(newTeacher);
                result += "\n";
            }
            else {
                result += gson.toJson(teacher);
                result += "\n";
            }
        }

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Teachers.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("file named " + file.getName() + " closed");
    }

    public void updateTeacher(String username, String password, String name, String nationalId, String eduId,
                              String phone, String email, String college, String rank, String pos, String room, boolean tutor) {

        Teacher newTeacher = new Teacher(username, password, name, nationalId, eduId, phone, email, collegeByName(college),
                rankByName(rank), posByName(pos), room, tutor);
        Teacher teacher;

        File file = new File("src/main/java/Files/Teachers.txt");

        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            teacher = gson.fromJson(userjson,Teacher.class);
            if (teacher.getEduId().equals(newTeacher.getEduId())) {
                result += gson.toJson(newTeacher);
                result += "\n";
            }
            else {
                result += gson.toJson(teacher);
                result += "\n";
            }
        }

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Teachers.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("file named " + file.getName() + " closed");

        updateTutor(newTeacher);
        updateTeacherOfLesson(newTeacher);
        updateTeacherOfLessonOfGrades(newTeacher);

    }

    public void addStudent(String username, String password, String name, String nationalId, String eduId,
                           String phone, String email, String college, String field, String entry, String tutor,
                           String type, String situation) {

        Student student = new Student(username, password, name, nationalId, eduId ,phone, email, field, entry,
                teacherByName(tutor), typeByName(type), sitByName(situation), null, new ArrayList<Grade>(),
                new ArrayList<College>());
        student.addColleges(collegeByName(college));

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String userjson=gson.toJson(student);
        try {
            FileWriter writer= new FileWriter("src/main/java/Files/Students.txt", true);
            log.info("file named " + "Students" + " opened");
            writer.write(userjson + "\n");
            writer.close();
            log.info("file named " + "Students" + " closed");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void updateTutor(Teacher teacher) {
        File file = new File("src/main/java/Files/Students.txt");
        Student student;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()) {
            userjson = scanner.nextLine();
            student = gson.fromJson(userjson,Student.class);
            if (student.getTutor() != null && student.getTutor().getEduId().equals(teacher.getEduId())){
                student.setTutor(teacher);
            }
            result += gson.toJson(student);
            result += "\n";
        }

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Students.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("file named " + file.getName() + " closed");
    }

    private void updateTeacherOfLesson(Teacher teacher) {
        File file = new File("src/main/java/Files/Lessons.txt");
        Lesson lesson;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()) {
            userjson = scanner.nextLine();
            lesson = gson.fromJson(userjson,Lesson.class);
            if (lesson.getTeacher().getEduId().equals(teacher.getEduId())){
                lesson.setTeacher(teacher);
            }
            result += gson.toJson(lesson);
            result += "\n";
        }

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Lessons.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("file named " + file.getName() + " closed");
    }

    private void updateTeacherOfLessonOfGrades(Teacher teacher) {

        File file = new File("src/main/java/Files/Students.txt");
        Student student;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()) {
            userjson = scanner.nextLine();
            student = gson.fromJson(userjson,Student.class);
            for (Grade g : student.getGrades()) {
                if (g.getLesson().getTeacher().getEduId().equals(teacher.getEduId())){
                    g.getLesson().setTeacher(teacher);
                }
            }
            result += gson.toJson(student);
            result += "\n";
        }

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Students.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("file named " + file.getName() + " closed");
    }

    private void updateLessonOfGrades(Lesson lesson) {
        File file = new File("src/main/java/Files/Students.txt");
        Student student;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()) {
            userjson = scanner.nextLine();
            student = gson.fromJson(userjson,Student.class);
            for (Grade g : student.getGrades()) {
                if (g.getLesson().getLessonNum().equals(lesson.getLessonNum())){
                    g.setLesson(lesson);
                }
            }
            result += gson.toJson(student);
            result += "\n";
        }

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Students.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("file named " + file.getName() + " closed");
    }

    public Student studentById(String id) {
        for (Student s : getStudents()) {
            if (s.getEduId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public ArrayList<Student> getStudents() {
        File file = new File("src/main/java/Files/Students.txt");
        Student student;
        log.info("file named " + file.getName() + " opened");
        ArrayList<Student> students = new ArrayList<>();

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            student = gson.fromJson(userjson,Student.class);
            students.add(student);
        }

        log.info("file named " + file.getName() + " closed");

        return students;
    }

    public ArrayList<Lesson> getLessons() {
        File file = new File("src/main/java/Files/Lessons.txt");
        Lesson lesson;
        log.info("file named " + file.getName() + " opened");
        ArrayList<Lesson> lessons = new ArrayList<>();

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            lesson = gson.fromJson(userjson,Lesson.class);
            lessons.add(lesson);
        }

        log.info("file named " + file.getName() + " closed");

        return lessons;
    }

    public ArrayList<Teacher> getTeachers() {
        File file = new File("src/main/java/Files/Teachers.txt");
        Teacher teacher;
        ArrayList<Teacher> teachers = new ArrayList<>();
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            teacher = gson.fromJson(userjson,Teacher.class);
            teachers.add(teacher);
        }

        log.info("file named " + file.getName() + " closed");

        return teachers;
    }

    public void addRecom(Recommendation recom) {


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String userjson=gson.toJson(recom);
        try {
            FileWriter writer= new FileWriter("src/main/java/Files/Recoms.txt", true);
            log.info("file named " + "Recoms.txt" + " opened");
            writer.write(userjson + "\n");
            writer.close();
            log.info("file named " + "Recoms" + " closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateRecom(Recommendation recom) {

        File file = new File("src/main/java/Files/Recoms.txt");
        Recommendation recommendation;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            recommendation = gson.fromJson(userjson,Recommendation.class);
            if (recommendation.getUser().getEduId().equals(recom.getUser().getEduId()) &&
            recommendation.getTeacher().getEduId().equals(recom.getTeacher().getEduId())) {
                result += gson.toJson(recom);
                result += "\n";
            }
            else {
                result += gson.toJson(recommendation);
                result += "\n";
            }
        }

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Recoms.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("file named " + file.getName() + " closed");
    }

    public ArrayList<Recommendation> getRecoms() {
        File file = new File("src/main/java/Files/Recoms.txt");
        Recommendation recommendation;
        ArrayList<Recommendation> recommendations = new ArrayList<>();
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            recommendation = gson.fromJson(userjson,Recommendation.class);
            recommendations.add(recommendation);
        }

        log.info("file named " + file.getName() + " closed");

        return recommendations;
    }

    public void addMinor(Minor minor) {


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String userjson=gson.toJson(minor);
        try {
            FileWriter writer= new FileWriter("src/main/java/Files/Minors.txt", true);
            log.info("file named " + "Minors.txt" + " opened");
            writer.write(userjson + "\n");
            writer.close();
            log.info("file named " + "Minors.txt" + " closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateMinor(Minor newMinor) {

        File file = new File("src/main/java/Files/Minors.txt");
        Minor minor;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            minor = gson.fromJson(userjson,Minor.class);
            if (minor.getUser().getEduId().equals(newMinor.getUser().getEduId()) &&
                    minor.getFrom().equals(newMinor.getFrom()) && minor.getTo().equals(newMinor.getTo())) {
                result += gson.toJson(newMinor);
                result += "\n";

                if (newMinor.isTeacherFrom() != null && newMinor.isTeacherTo() != null &&
                        newMinor.isTeacherFrom() && newMinor.isTeacherTo()) {
                    for (Student s : getStudents()) {
                        if (s.getEduId().equals(newMinor.getUser().getEduId())) {
                            s.addColleges(newMinor.getTo());
                            updateStudent(s);
                        }
                    }
                }
            }
            else {
                result += gson.toJson(minor);
                result += "\n";
            }
        }

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Minors.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("file named " + file.getName() + " closed");
    }

    public ArrayList<Minor> getMinors() {
        File file = new File("src/main/java/Files/Minors.txt");
        Minor minor;
        ArrayList<Minor> minors = new ArrayList<>();
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            minor = gson.fromJson(userjson,Minor.class);
            minors.add(minor);
        }
        log.info("file named " + file.getName() + " closed");

        return minors;
    }

    public void addResign(Resign resign) {


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String userjson=gson.toJson(resign);
        try {
            FileWriter writer= new FileWriter("src/main/java/Files/Resigns.txt", true);
            log.info("file named " + "Resigns.txt" + " opened");
            writer.write(userjson + "\n");
            writer.close();
            log.info("file named " + "Resigns.txt" + " closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateResign(Resign newResign, User user) {

        File file = new File("src/main/java/Files/Resigns.txt");
        Resign resign;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        String result = "";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            resign = gson.fromJson(userjson,Resign.class);
            if (resign.getUser().getEduId().equals(newResign.getUser().getEduId()) &&
                    resign.getTeacher().getEduId().equals(newResign.getTeacher().getEduId())) {
                result += gson.toJson(newResign);
                result += "\n";
                if (newResign.isConfirmed()) {
                    for (Student s : getStudents()) {
                        if (s.getEduId().equals(user.getEduId())) {
                            s.setSituation(Situation.RESIGNED);
                            updateStudent(s);
                        }
                    }
                }
            }
            else {
                result += gson.toJson(resign);
                result += "\n";
            }
        }

        try {
            FileWriter writer = new FileWriter("src/main/java/Files/Resigns.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("file named " + file.getName() + " closed");
    }

    public ArrayList<Resign> getResigns() {
        File file = new File("src/main/java/Files/Resigns.txt");
        log.info("file named " + file.getName() + " opened");
        Resign resign;
        ArrayList<Resign> resigns = new ArrayList<>();

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            resign = gson.fromJson(userjson,Resign.class);
            resigns.add(resign);
        }
        log.info("file named " + file.getName() + " closed");

        return resigns;
    }

    public College collegeByName(String name) {
        name = name.toUpperCase();
        College college = College.valueOf(name);
        return college;
    }

    private Type typeByName(String name) {
        name = name.toUpperCase();
        return Type.valueOf(name);
    }

    public Teacher teacherByName(String name) {

        File file = new File("src/main/java/Files/Teachers.txt");
        Teacher teacher;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            teacher = gson.fromJson(userjson,Teacher.class);
            if (teacher.getName().equals(name)) {
                log.info("file named " + file.getName() + " closed");
                return teacher;
            }
        }

        log.info("file named " + file.getName() + " closed");

        return null;
    }

    public Teacher teacherById(String id) {
        File file = new File("src/main/java/Files/Teachers.txt");
        Teacher teacher;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            teacher = gson.fromJson(userjson,Teacher.class);
            if (teacher.getEduId().equals(id)) {
                log.info("file named " + file.getName() + " closed");
                return teacher;
            }
        }

        log.info("file named " + file.getName() + " closed");
        return null;
    }

    public Lesson lessonByName(String name) {
        File file = new File("src/main/java/Files/Lessons.txt");
        Lesson lesson;
        log.info("file named " + file.getName() + " opened");

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            lesson = gson.fromJson(userjson,Lesson.class);
            if (lesson.getName().equals(name)) {
                log.info("file named " + file.getName() + " closed");
                return lesson;
            }
        }

        log.info("file named " + file.getName() + " closed");

        return null;
    }

    public Lesson lessonById(String id) {
        File file = new File("src/main/java/Files/Lessons.txt");
        log.info("file named " + file.getName() + " opened");
        Lesson lesson;

        Scanner scanner= null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();
        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            lesson = gson.fromJson(userjson,Lesson.class);
            if (lesson.getLessonNum().equals(id)) {
                log.info("file named " + file.getName() + " closed");
                return lesson;
            }
        }
        log.info("file named " + file.getName() + " closed");

        return null;
    }

    private Situation sitByName(String name) {
        name = name.toUpperCase();
        return Situation.valueOf(name);
    }

    private Rank rankByName(String name) {
        name = name.toUpperCase();
        return Rank.valueOf(name);
    }

    private Position posByName(String name) {
        name = name.toUpperCase();
        return Position.valueOf(name);
    }

}
