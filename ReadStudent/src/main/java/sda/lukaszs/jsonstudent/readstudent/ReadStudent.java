package sda.lukaszs.jsonstudent.readstudent;

import com.fasterxml.jackson.databind.ObjectMapper;
import sda.lukaszs.jsonstudent.studentmodel.Student;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadStudent {
    //tworzy pojedynczego studenta z JSON'a
    public static Student readFromJSON(File file) {
        ObjectMapper mapper = new ObjectMapper();
        Student output = null;
        try {
            output = mapper.readValue(file, Student.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    //tworzy listę studentów z dowolnego json'a
    public static List<Student> readListFromJSON(File file){
        ObjectMapper mapper = new ObjectMapper();
        List<Student> output = new ArrayList<>();
        Student[] outArray;
        try {
            outArray = mapper.readValue(file,Student[].class);
            output = Arrays.asList(outArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
