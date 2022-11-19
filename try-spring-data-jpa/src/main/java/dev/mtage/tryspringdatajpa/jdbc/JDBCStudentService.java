package dev.mtage.tryspringdatajpa.jdbc;

import dev.mtage.tryspringdatajpa.entity.Student;
import org.springframework.stereotype.Service;

/**
 * @author mtage
 * @since 2020/11/29 19:59
 */
@Service
public class JDBCStudentService {
    private final JDBCStudentRepository studentRepository;

    public JDBCStudentService(JDBCStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudentById(long id) {
        return studentRepository.getById(id);
    }

}
