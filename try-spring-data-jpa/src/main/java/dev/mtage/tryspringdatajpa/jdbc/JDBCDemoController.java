package dev.mtage.tryspringdatajpa.jdbc;

import dev.mtage.tryspringdatajpa.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Demo1: JdbcTemplate
 * @author mtage
 * @since 2020/11/29 19:59
 */
@RestController
@RequestMapping("/jdbc")
public class JDBCDemoController {
    private final JDBCStudentService studentService;

    @Autowired
    public JDBCDemoController(JDBCStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable long id) {
        // omit codes like validation, logging...
        return studentService.getStudentById(id);
    }
}
