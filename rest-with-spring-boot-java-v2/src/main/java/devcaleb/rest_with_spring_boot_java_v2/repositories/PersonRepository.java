package devcaleb.rest_with_spring_boot_java_v2.repositories;

import devcaleb.rest_with_spring_boot_java_v2.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
