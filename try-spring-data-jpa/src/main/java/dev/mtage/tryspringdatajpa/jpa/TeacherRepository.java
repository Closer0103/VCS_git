package dev.mtage.tryspringdatajpa.jpa;

import dev.mtage.tryspringdatajpa.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mtage
 * @since 2020/11/29 20:51
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
