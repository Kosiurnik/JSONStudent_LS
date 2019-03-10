package sda.lukaszs.jsonstudent.readstudent;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sda.lukaszs.jsonstudent.studentmodel.Student;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReadStudentTest {

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
    void readFromJSON() {
        JsonFactory jFactory = new JsonFactory();
        StringWriter writer = new StringWriter();
        try {
            JsonGenerator jsonGenerator = jFactory.createGenerator(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name","testName");
            jsonGenerator.writeStringField("lastName","testLastName");
            jsonGenerator.writeNumberField("index",111);
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
            String jsonString = writer.toString();
            System.out.println(jsonString);
            Files.write(Paths.get("jsonTest/readTest.json"),jsonString.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(Files.exists(Paths.get("jsonTest/readTest.json"))).isEqualTo(true);
        Student student1 = ReadStudent.readFromJSON(new File("jsonTest/readTest.json"));
        Student student2 = new Student("testName","testLastName",111);
        assertThat(student1.equals(student2)).isEqualTo(true);
    }

    @Test
    void readListFromJSON() {
        JsonFactory jFactory = new JsonFactory();
        StringWriter writer = new StringWriter();
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

            String jsonString = writer.toString();
            System.out.println(jsonString);
            Files.write(Paths.get("jsonTest/readListTest.json"),jsonString.getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }

        assertThat(Files.exists(Paths.get("jsonTest/readListTest.json"))).isEqualTo(true);
        List<Student> students = ReadStudent.readListFromJSON(new File("jsonTest/readListTest.json"));
        assertThat(students.size()).isEqualTo(3);
        List<Student> students2 = new ArrayList<>();
        students2.add(new Student("testName1","testLastName1",111));
        students2.add(new Student("testName2","testLastName2",222));
        students2.add(new Student("testName3","testLastName3",333));
        assertThat(students.equals(students2)).isEqualTo(true);

    }

    @AfterAll
    static void removeFiles(){
        try{
            if(Files.exists(Paths.get("jsonTest/readTest.json")))
                Files.delete(Paths.get("jsonTest/readTest.json"));
            if(Files.exists(Paths.get("jsonTest/readListTest.json")))
                Files.delete(Paths.get("jsonTest/readListTest.json"));

            if(Files.exists(Paths.get("jsonTest")))
                Files.delete(Paths.get("jsonTest"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}