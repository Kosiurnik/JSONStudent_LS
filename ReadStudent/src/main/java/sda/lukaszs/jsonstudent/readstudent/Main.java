package sda.lukaszs.jsonstudent.readstudent;

import sda.lukaszs.jsonstudent.studentmodel.Student;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //listuję wszystkich
        List<Student> students = ReadStudent.readListFromJSON(new File("json/students.json"));
        for(Student s : students){
            System.out.println(s);
        }
        System.out.println();

        //listuję studentów zaczynających się imieniem na A
        List<Student> filtered = students
                .stream()
                .filter(s -> s.getName().startsWith("A"))
                .collect(Collectors.toList());
        for(Student s : filtered){
            System.out.println(s);
        }
    }
}
