package dev.mtage.tryspringdatajpa.jdbc;

import dev.mtage.tryspringdatajpa.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * @author mtage
 * @since 2020/11/29 20:17
 */
@Repository
public class JDBCStudentRepository {
    public static final String DDL_SQL = "CREATE TABLE IF NOT EXISTS student(id bigint auto_increment primary key, name varchar(100), age integer)";
    public static final String[] INSERT_SQLS = new String[]{
            "INSERT INTO student(name, age) values ( 'Tomoyo', 22 )",
            "INSERT INTO student(name, age) values ( 'Amiya', 16 )",
            "INSERT INTO student(name, age) values ( 'Eyjafjalla', 18 )",
    };

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCStudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @PostConstruct
    public void initDB() {
        this.jdbcTemplate.execute(DDL_SQL);
        this.jdbcTemplate.batchUpdate(INSERT_SQLS);
    }

    public Student getById(long id) {
        return jdbcTemplate.queryForObject("SELECT * from student where id = ?",
                (resultSet, rowNum) -> Student.builder()
                        .id(id)
                        .name(resultSet.getString("name"))
                        .age(resultSet.getInt("age"))
                        .build(), id);
    }

}
