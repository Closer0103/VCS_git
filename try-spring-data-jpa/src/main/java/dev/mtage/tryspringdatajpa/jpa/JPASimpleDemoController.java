package dev.mtage.tryspringdatajpa.jpa;

import dev.mtage.tryspringdatajpa.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Demo2: Basic usage of Spring Data JPA
 * @author mtage
 * @since 2020/11/29 20:51
 */
@RestController
@RequestMapping("/jpa")
public class JPASimpleDemoController {
    // depends on repository directly just for demonstration
    private final TeacherRepository teacherRepository;

    @Autowired
    public JPASimpleDemoController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @PostConstruct
    public void initDB() {
        teacherRepository.saveAll(List.of(Teacher.builder().name("Aragaki").age(22).build(),
                Teacher.builder().name("Yui").age(16).build()));
    }

    @GetMapping("/teacher/{id}")
    public Teacher getTeacherById(@PathVariable long id) {
        // omit codes like validation, logging...
        return teacherRepository.findById(id).orElse(null);
    }

    @GetMapping("/teacher/list")
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    @PostMapping("/teacher")
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @DeleteMapping("/teacher/{id}")
    public void deleteTeacher(@PathVariable long id) {
        teacherRepository.deleteById(id);
    }
}
