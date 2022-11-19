package dev.mtage.tryspringdatajpa.jpa;

import dev.mtage.tryspringdatajpa.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mtage
 * @since 2020/11/29 21:43
 */
@RestController
@RequestMapping("/jpa")
public class JPAPersonDemoController {
    private final JPAPersonDemoService personDemoService;

    @Autowired
    public JPAPersonDemoController(JPAPersonDemoService personDemoService) {
        this.personDemoService = personDemoService;
    }


    @GetMapping("/person/list")
    public List<Person> getAllPerson(@RequestParam(required = false) String city) {
        if (city == null || city.isEmpty()) {
            return personDemoService.getAllPersonByPage(Pageable.unpaged()).getContent();
        }
        return personDemoService.getAllPersonByAddressCity(city);
    }

    /**
     * Just for demonstration, not recommended for designing APIs like this
     * use http://localhost:8080/jpa/person/list/by-name?name=Bers%25  for test
     * @param name
     * @return
     */
    @GetMapping("/person/list/by-name")
    public List<Person> getAllPersonByName(@RequestParam String name) {
        return personDemoService.getAllPersonByNamePattern(name);
    }

    @GetMapping("/person/list/paged")
    public Page<Person> getAllPersonByPage(@PageableDefault Pageable pageable) {
        return personDemoService.getAllPersonByPage(pageable);
    }

    @GetMapping("/person/transaction")
    public void tryTransaction(@RequestParam(required = false) String name) {
        personDemoService.insertIntoPersonAndTeacher(Person.builder().name(name).build());
    }



}
