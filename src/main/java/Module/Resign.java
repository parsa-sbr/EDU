package Module;

public class Resign {

    User user;
    Teacher teacher;
    Boolean confirmed;

    public Resign(User user, Teacher teacher) {
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

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }
}
