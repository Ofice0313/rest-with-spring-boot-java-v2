package devcaleb.rest_with_spring_boot_java_v2.services;

import devcaleb.rest_with_spring_boot_java_v2.entities.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(Long id) {
        logger.info("Finding one Person");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Marcelo");
        person.setLastName("LastName");
        person.setAddress("Luis Cabral - Cidade de Maputo");
        person.setGender("Male");

        return person;
    }
}
