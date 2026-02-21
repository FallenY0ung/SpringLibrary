package springlibrarypackage.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Person {
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull ( message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно содержать от 2 до 100 символов")
    private String fullName;
    @NotNull
    @Min(value = 1900, message = "Год рождения должен быть больше 1899")
    private int ageOfBirthday;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAgeOfBirthday() {
        return ageOfBirthday;
    }

    public void setAgeOfBirthday(int ageOfBirthday) {
        this.ageOfBirthday = ageOfBirthday;
    }

    public Person() {}

    public Person(int id, String fullName, int ageOfBirthday) {
        this.id = id;
        this.fullName = fullName;
        this.ageOfBirthday = ageOfBirthday;

    }
}
