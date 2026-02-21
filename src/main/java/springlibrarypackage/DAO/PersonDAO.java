package springlibrarypackage.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springlibrarypackage.models.Book;
import springlibrarypackage.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(Person person, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<Person>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(full_name, age_of_birthday) VALUES (?,?)", person.getFullName(), person.getAgeOfBirthday());

    }

    public Person show(int id) {

        return jdbcTemplate.query("SELECT * FROM person WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findFirst().orElse(null);
    }


    public void update(int id, Person updatedperson) {
        jdbcTemplate.update("UPDATE person set full_name = ?, age_of_birthday = ? WHERE id = ?",
                updatedperson.getFullName(), updatedperson.getAgeOfBirthday(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }
}
