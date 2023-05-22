package Module;

import java.util.ArrayList;

public class Student extends User{

    private String field;
    private String dateOfEntry;
    private Double grade;
    private Teacher tutor;
    private Type type;
    private Situation situation;
    private boolean allowedToEnroll = true;
    private String timeToEnroll;
    private ArrayList<Grade> grades;
    private ArrayList<College> colleges;
    private Boolean dorm;
    private Integer defend;


    public Student(String username, String password, String name,
                   String nationalId, String eduId, String phoneNum,
                   String email,
                   String field, String dateOfEntry, Teacher tutor,
                   Type type, Situation situation, String timeToEnroll,
                   ArrayList<Grade> grades, ArrayList<College> colleges) {

        super(username, password, name, nationalId, eduId, phoneNum, email);
        this.field = field;
        this.dateOfEntry = dateOfEntry;
        this.tutor = tutor;
        this.type = type;
        this.situation = situation;
        this.timeToEnroll = timeToEnroll;
        this.grades = grades;
        this.colleges = colleges;

    }

    @Override
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String getDateOfEntry() {
        return dateOfEntry;
    }

    @Override
    public Double getGrade() {
        Double grade = 0.00;
        for (Grade g : grades) {
            if (!g.isTemporary()) {
                grade += g.getAmount();
            }
        }
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public Teacher getTutor() {
        return tutor;
    }

    public void setTutor(Teacher tutor) {
        this.tutor = tutor;
    }

    @Override
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean isAllowedToEnroll() {
        return allowedToEnroll;
    }

    public void setAllowedToEnroll(boolean allowedToEnroll) {
        this.allowedToEnroll = allowedToEnroll;
    }

    @Override
    public String getTimeToEnroll() {
        return timeToEnroll;
    }

    public void setTimeToEnroll(String timeToEnroll) {
        this.timeToEnroll = timeToEnroll;
    }

    @Override
    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void addGrades(Grade grade) {
        this.grades.add(grade);
    }

    @Override
    public ArrayList<College> getColleges() {
        return colleges;
    }

    public void addColleges(College college) {
        this.colleges.add(college);
    }

    public Situation getSituation() {
        return situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    public Boolean getDorm() {
        return dorm;
    }

    public void setDorm(Boolean dorm) {
        this.dorm = dorm;
    }

    public Integer getDefend() {
        return defend;
    }

    public void setDefend(Integer defend) {
        this.defend = defend;
    }
}

