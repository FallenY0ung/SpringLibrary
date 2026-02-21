package springlibrarypackage.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springlibrarypackage.models.Book;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(Book book, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<Book>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author_name, age) VALUES (?,?,?)",  book.getName(), book.getAuthorName(), book.getAge());

    }

    public List<Book> findByPersonId(int personId) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?", new Object[]{personId}, new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findFirst().orElse(null);
    }

    public void update(int id, Book updatedbook) {
        jdbcTemplate.update("UPDATE book set person_id = ?, name = ?, author_name = ?, age = ? WHERE id = ?",
                updatedbook.getPersonId(), updatedbook.getName(), updatedbook.getAuthorName(), updatedbook.getAge(), id);
    }

    public void updatePersonId(int id, Book updatedbook) {
        jdbcTemplate.update("UPDATE book set person_id = ?  WHERE id = ?",
                updatedbook.getPersonId(), id);
    }
    public void release(int id) {
        jdbcTemplate.update("UPDATE book set person_id = NULL  WHERE id = ?", id);

    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }
}
