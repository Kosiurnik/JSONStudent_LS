package sda.lukaszs.jsonstudent.readstudent;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.AfterAll;
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
            Files.write(Paths.get("json/readTest.json"),jsonString.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(Files.exists(Paths.get("json/readTest.json"))).isEqualTo(true);
        Student student1 = ReadStudent.readFromJSON(new File("json/readTest.json"));
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
            Files.write(Paths.get("json/readListTest.json"),jsonString.getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }

        assertThat(Files.exists(Paths.get("json/readListTest.json"))).isEqualTo(true);
        List<Student> students = ReadStudent.readListFromJSON(new File("json/readListTest.json"));
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
            if(Files.exists(Paths.get("json/readTest.json")))
                Files.delete(Paths.get("json/readTest.json"));
            if(Files.exists(Paths.get("json/readListTest.json")))
                Files.delete(Paths.get("json/readListTest.json"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}