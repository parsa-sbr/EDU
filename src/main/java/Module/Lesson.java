package Module;

public class Lesson {

    private String name;
    private Teacher teacher;
    private College college;
    private String timeOfClass;
    private int numberOfUnites;
    private int dateOfExam;
    private String lessonNum;
    private Type lessonSection;

    public Lesson(String name, Teacher teacher, College college,
                  String timeOfClass, int numberOfUnites, int dateOfExam, String lessonNum, Type lessonSection) {

        this.name = name;
        this.teacher = teacher;
        this.college = college;
        this.timeOfClass = timeOfClass;
        this.numberOfUnites = numberOfUnites;
        this.dateOfExam = dateOfExam;
        this.lessonNum = lessonNum;
        this.lessonSection = lessonSection;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public String getTimeOfClass() {
        return timeOfClass;
    }


    public int getNumberOfUnites() {
        return numberOfUnites;
    }


    public int getDateOfExam() {
        return dateOfExam;
    }

    public String getName() {
        return name;
    }

    public String getLessonNum() {
        return lessonNum;
    }

    public Type getLessonSection() {
        return lessonSection;
    }
}
