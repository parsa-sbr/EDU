package Graphic;

import Module.Lesson;

import java.util.Comparator;

public class SortByDate implements Comparator<Lesson> {


    public int compare(Lesson a, Lesson b) {

        return a.getDateOfExam() - b.getDateOfExam();
    }
}
