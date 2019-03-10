package sda.lukaszs.jsonstudent.studentmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    String name;
    String lastName;
    int index;

    @Override
    public String toString() {
        return String.format("%s %s (%d)", name, lastName, index);
    }
}
