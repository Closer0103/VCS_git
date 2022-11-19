package dev.mtage.tryspringdatajpa.jpa;

import dev.mtage.tryspringdatajpa.entity.Address;
import dev.mtage.tryspringdatajpa.entity.Person;
import dev.mtage.tryspringdatajpa.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * @author mtage
 * @since 2020/11/29 22:07
 */
@Service
public class JPAPersonDemoService {
    private final PersonRepository personRepository;
    private final TeacherRepository teacherRepository;

    @PostConstruct
    public void initDB() {
        personRepository.saveAll(List.of(
                Person.builder().name("Rider").address(Address.builder().city("Beijing").location("Tian'an").build()).build(),
                Person.builder().name("Assasin").address(Address.builder().city("Beijing").location("Tian'an").build()).build(),
                Person.builder().name("Caster").address(Address.builder().city("Beijing").location("Tian'an").build()).build(),
                Person.builder().name("Berserker").address(Address.builder().city("Beijing").location("Tian'an").build()).build(),
                Person.builder().name("Archer").address(Address.builder().city("Beijing").location("Tian'an").build()).build(),
                Person.builder().name("Ruler").address(Address.builder().city("Beijing").location("Tian'an").build()).build(),
                Person.builder().name("Saber").address(Address.builder().city("Shanghai").location("Siping").build()).build(),
                Person.builder().name("Lancer").address(Address.builder().city("Beijing").location("Tian'an").build()).build())
        );
    }

    @Autowired
    public JPAPersonDemoService(PersonRepository personRepository, TeacherRepository teacherRepository) {
        this.personRepository = personRepository;
        this.teacherRepository = teacherRepository;
    }

    public Page<Person> getAllPersonByPage(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public List<Person> getAllPersonByAddressCity(String city) {
        return personRepository.findByAddressCity(city);
    }

    public List<Person> getAllPersonByNamePattern(String namePattern) {
        return personRepository.findByNameLike(namePattern);
    }

    /**
     * Alibaba p3c: [For Reference] Do not overuse @Transactional.
     * Because transaction affects QPS of DB,
     * and relevant rollbacks may need be considered,
     * including cache rollback, search engine rollback, message making up, statistics adjustment, etc.
     * @param person
     */
    @Transactional(rollbackFor = InvalidParameterException.class, timeout = 10)
    public void insertIntoPersonAndTeacher(Person person) {
        personRepository.save(person);
        if ("exception".equals(person.getName())) {
            throw new InvalidParameterException();
        }
        teacherRepository.save(Teacher.builder().name("new teacher").build());
    }


}
