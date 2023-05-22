package Module;

import java.util.ArrayList;

public class Teacher extends User{

    private Rank rank;
    private Position position;
    private String roomNum;
    private boolean tutor;
    private College college;

    public Teacher(String username, String password, String name,
                   String nationalId, String eduId, String phoneNum,
                   String email, College college,
                   Rank rank, Position position, String roomNum, boolean tutor) {

        super(username, password, name, nationalId, eduId, phoneNum, email);
        this.rank = rank;
        this.position = position;
        this.roomNum = roomNum;
        this.tutor = tutor;
        this.college = college;
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String getRoomNum() {
        return roomNum;
    }

    @Override
    public boolean isTutor() {
        return tutor;
    }

    @Override
    public College getCollege() {
        return college;
    }

}

