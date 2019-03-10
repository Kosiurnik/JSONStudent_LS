package sda.lukaszs.jsonstudent.savestudent;

import com.fasterxml.jackson.databind.ObjectMapper;
import sda.lukaszs.jsonstudent.studentmodel.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SaveStudent {
    //tworzy plik json z obiektu określonej klasy
    public static void saveJSON(String filename, Student student){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String p1JsonString = mapper.writeValueAsString(student);
            Files.write(Paths.get(filename),p1JsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveJSON(String filename, List<Student> students){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String p1JsonString = mapper.writeValueAsString(students);
            Files.write(Paths.get(filename),p1JsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
