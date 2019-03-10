package sda.lukaszs.jsonstudent.savestudent;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sda.lukaszs.jsonstudent.studentmodel.Student;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
class SaveStudentTest {

    @BeforeAll
    static void createTestDirectory(){
        if(!Files.exists(Paths.get("jsonTest"))){
            try {
                Files.createDirectory(Paths.get("jsonTest"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void saveJSON() {
        JsonFactory jFactory = new JsonFactory();
        StringWriter writer = new StringWriter();
        String jsonString = "";
        try {
            JsonGenerator jsonGenerator = jFactory.createGenerator(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name","testName");
            jsonGenerator.writeStringField("lastName","testLastName");
            jsonGenerator.writeNumberField("index",111);
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
            jsonString = writer.toString();
            System.out.println(jsonString);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Student student = new Student("testName","testLastName",111);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString2 = "null";
        try {
            jsonString2 = mapper.writeValueAsString(student);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertThat(jsonString.equals(jsonString2)).isEqualTo(true);
        SaveStudent.saveJSON("jsonTest/writeTest.json",student);
        assertThat(Files.exists(Paths.get("jsonTest/writeTest.json"))).isEqualTo(true);
    }

    @Test
    void saveJSONList() {
        JsonFactory jFactory = new JsonFactory();
        StringWriter writer = new StringWriter();
        String jsonString = "";
        try{
            JsonGenerator jsonGenerator = jFactory.createGenerator(writer);
            jsonGenerator.writeStartArray();

            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name","testName1");
            jsonGenerator.writeStringField("lastName","testLastName1");
            jsonGenerator.writeNumberField("index",111);
            jsonGenerator.writeEndObject();

            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name","testName2");
            jsonGenerator.writeStringField("lastName","testLastName2");
            jsonGenerator.writeNumberField("index",222);
            jsonGenerator.writeEndObject();

            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name","testName3");
            jsonGenerator.writeStringField("lastName","testLastName3");
            jsonGenerator.writeNumberField("index",333);
            jsonGenerator.writeEndObject();
            jsonGenerator.writeEndArray();
            jsonGenerator.close();

            jsonString = writer.toString();
            System.out.println(jsonString);
        }catch(IOException e){
            e.printStackTrace();
        }
        List<Student> students = new ArrayList<>();
        students.add(new Student("testName1","testLastName1",111));
        students.add(new Student("testName2","testLastName2",222));
        students.add(new Student("testName3","testLastName3",333));
        ObjectMapper mapper = new ObjectMapper();
        String jsonString2 = "null";
        try {
            jsonString2 = mapper.writeValueAsString(students);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertThat(jsonString.equals(jsonString2)).isEqualTo(true);
        SaveStudent.saveJSON("jsonTest/writeListTest.json",students);
        assertThat(Files.exists(Paths.get("jsonTest/writeListTest.json"))).isEqualTo(true);
    }

    @AfterAll
    static void removeFiles(){
        try{
            if(Files.exists(Paths.get("jsonTest/writeTest.json")))
                Files.delete(Paths.get("jsonTest/writeTest.json"));
            if(Files.exists(Paths.get("jsonTest/writeListTest.json")))
                Files.delete(Paths.get("jsonTest/writeListTest.json"));

            if(Files.exists(Paths.get("jsonTest")))
                Files.delete(Paths.get("jsonTest"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}