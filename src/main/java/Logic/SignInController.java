package Logic;

//import Files.*;
import Module.Student;
import Module.Teacher;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SignInController {

    Logger log = LogManager.getLogger(SignInController.class);

    public static SignInController signInController;

    private SignInController() {

    }

    public static SignInController getInstance(){
        if (signInController==null){
            signInController = new SignInController();
        }
        return signInController;
    }

    public Student isStudentValid(String username, String password) {

        try {
            File file = new File("src/main/java/Files/Students.txt");
            Student student;
            log.info("file named " + file.getName() + " opened");

            Scanner scanner= new Scanner(file);
            String userjson="";
            GsonBuilder gsonBuilder= new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson= gsonBuilder.create();
            while (scanner.hasNextLine()){
                userjson =scanner.nextLine();
                student = gson.fromJson(userjson,Student.class);
                if (student.getPassword().equals(password) && student.getUsername().equals(username)) {
                    log.info("file named " + file.getName() + " closed");
                    return student;
                }
            }
        }
        catch (FileNotFoundException e) {
            log.error("could not find the file in path " + "src/main/java/Files/Students.txt");
            e.printStackTrace();
        }

        log.info("file named " + "Students.txt" + " closed");

        return null;
    }

    public Teacher isTeacherValid(String username, String password) {

        try {
            File file = new File("src/main/java/Files/Teachers.txt");
            Teacher teacher;
            log.info("file named " + file.getName() + " opened");

            Scanner scanner= new Scanner(file);
            String userjson="";
            GsonBuilder gsonBuilder= new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson= gsonBuilder.create();
            while (scanner.hasNextLine()){
                userjson =scanner.nextLine();
                teacher = gson.fromJson(userjson,Teacher.class);
                if (teacher.getPassword().equals(password) && teacher.getUsername().equals(username)) {
                    log.info("file named " + file.getName() + " closed");
                    return teacher;
                }
            }
        }
        catch (FileNotFoundException e) {
            log.error("could not find the file in path " + "src/main/java/Files/Teachers.txt");
            e.printStackTrace();
        }

        log.info("file named " + "Teachers.txt" + " closed");

        return null;
    }

}
