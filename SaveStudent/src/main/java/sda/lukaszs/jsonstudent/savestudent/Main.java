package sda.lukaszs.jsonstudent.savestudent;

import sda.lukaszs.jsonstudent.studentmodel.Student;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Łukasz","Świetlik",10001));
        students.add(new Student("Adam","Tomaszewski",10002));
        students.add(new Student("Damian","Popławski",10003));
        students.add(new Student("Aneta","Zakrzewska",10004));
        students.add(new Student("Barbara","Wysocka",10005));

        SaveStudent.saveJSON("json/students.json",students);
    }
}
