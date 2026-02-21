package springlibrarypackage.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class Book {
    private int id;
    private Integer personId;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    @NotNull
    @Size(min = 2, max = 100, message = "Автор должен быть от 2 до 100 символов")
    private String authorName;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull ( message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно содержать от 2 до 100 символов")
    private String name;
    @NotNull
    @Min(value = 0, message = "Год рождения должен быть больше 0")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Book() {}

    public Book(int id, int personId, String name, String authorName, int age) {
        this.id = id;
        this.personId = personId;
        this.authorName = authorName;
        this.name = name;
        this.age = age;
    }
}
