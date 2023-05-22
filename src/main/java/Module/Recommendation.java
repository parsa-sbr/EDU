package Module;


public class Recommendation {

    User user;
    Teacher teacher;
    Boolean confirmed;

    public Recommendation(User user, Teacher teacher) {
        this.user = user;
        this.teacher = teacher;
    }

    public User getUser() {
        return user;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
