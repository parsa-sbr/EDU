package Module;

import java.util.ArrayList;

public class User {

    protected String username;
    protected String password;
    protected String name;
    protected String nationalId;
    protected String eduId;
    protected String phoneNum;
    protected String email;
    protected String lastIn;

    public User(String username, String password, String name,
                String nationalId, String eduId, String phoneNum,
                String email) {

        this.username = username;
        this.password = password;
        this.name = name;
        this.nationalId = nationalId;
        this.eduId = eduId;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getEduId() {
        return eduId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoomNum() {
        return "";
    }

    public Rank getRank() {
        return null;
    }

    public Position getPosition() {
        return null;
    }

    public College getCollege() {
        return null;
    }

    public String getField() {
        return "field";
    }

    public void setField(String field) {

    }

    public String getDateOfEntry() {
        return "dateOfEntry";
    }

    public Double getGrade() {
        return null;
    }

    public void setGrade(double grade) {

    }

    public Teacher getTutor() {
        return null;
    }

    public void setTutor(Teacher tutor) {

    }

    public Type getType() {
        return null;
    }

    public void setType(Type type) {

    }

    public boolean isAllowedToEnroll() {
        return true;
    }

    public void setAllowedToEnroll(boolean allowedToEnroll) {

    }

    public String getTimeToEnroll() {
        return "timeToEnroll";
    }

    public void setTimeToEnroll(String timeToEnroll) {

    }

    public ArrayList<Grade> getGrades() {
        return null;
    }

    public void addGrades(Grade grade) {

    }

    public ArrayList<College> getColleges() {
        return null;
    }

    public void addColleges(College college) {

    }

    public String getLastIn() {
        return lastIn;
    }

    public void setLastIn(String lastIn) {
        this.lastIn = lastIn;
    }

    public Situation getSituation() {
        return null;
    }

    public void setSituation(Situation situation) {

    }

    public void setPosition(Position position) {

    }

    public boolean isTutor() {
        return true;
    }

    public Boolean getDorm() {
        return null;
    }

    public void setDorm(Boolean dorm) {

    }

    public Integer getDefend() {
        return null;
    }

    public void setDefend(Integer defend) {

    }
}
