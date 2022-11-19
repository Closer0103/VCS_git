package dev.mtage.tryspringdatajpa.jpa;

import dev.mtage.tryspringdatajpa.entity.Address;
import dev.mtage.tryspringdatajpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author mtage
 * @since 2020/11/29 21:41
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByAddressAndName(Address address, String name);

    List<Person> findByNameContains(String nameSlice);

    List<Person> findByNameLike(String namePattern);

    List<Person> findByAddressCity(String addressCity);

    List<Person> findByAgeIsGreaterThanAndAgeIsLessThanEqual(int age1, int age2);
}
