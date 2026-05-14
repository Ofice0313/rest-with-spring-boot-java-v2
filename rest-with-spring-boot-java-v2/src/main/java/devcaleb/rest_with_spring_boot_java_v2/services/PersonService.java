package devcaleb.rest_with_spring_boot_java_v2.services;

import devcaleb.rest_with_spring_boot_java_v2.entities.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAll() {
        logger.info("Finding all People");
        List<Person> persons = new ArrayList<Person>();
        for(int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person findById(Long id) {
        logger.info("Finding one Person");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Marcelo");
        person.setLastName("Ofice");
        person.setAddress("Luis Cabral - Cidade de Maputo");
        person.setGender("Male");
        return person;
    }

    public Person create(Person person) {
        logger.info("Creating a Person");

        return person;
    }

    public Person update(Person person) {
        logger.info("Updating a Person");
        return person;
    }

    public void delete(Long id) {
        logger.info("Deleting a Person");
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("FirstName " + i);
        person.setLastName("LastName " + i);
        person.setAddress("Some Address in Maputo City");
        person.setGender("Male");
        return person;
    }
}
