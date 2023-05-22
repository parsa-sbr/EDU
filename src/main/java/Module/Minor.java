package Module;

public class Minor {

    User user;
    College from;
    College to;
    Boolean teacherFrom;
    Boolean teacherTo;

    public Minor(User user, College from, College to) {
        this.user = user;
        this.from = from;
        this.to = to;
    }

    public User getUser() {
        return user;
    }

    public Boolean isTeacherFrom() {
        return teacherFrom;
    }

    public void setTeacherFrom(Boolean teacherFrom) {
        this.teacherFrom = teacherFrom;
    }

    public Boolean isTeacherTo() {
        return teacherTo;
    }

    public void setTeacherTo(Boolean teacherTo) {
        this.teacherTo = teacherTo;
    }

    public College getFrom() {
        return from;
    }

    public College getTo() {
        return to;
    }
}
