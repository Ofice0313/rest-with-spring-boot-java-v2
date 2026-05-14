package devcaleb.rest_with_spring_boot_java_v2.services;

import devcaleb.rest_with_spring_boot_java_v2.data.dto.PersonDTO;
import devcaleb.rest_with_spring_boot_java_v2.entities.Person;
import devcaleb.rest_with_spring_boot_java_v2.exceptions.ResourceNotFoundException;
import devcaleb.rest_with_spring_boot_java_v2.mapper.ObjectMapper;
import devcaleb.rest_with_spring_boot_java_v2.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import static devcaleb.rest_with_spring_boot_java_v2.mapper.ObjectMapper.parseListObjects;
import static devcaleb.rest_with_spring_boot_java_v2.mapper.ObjectMapper.parseObject;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository repository;

    public List<PersonDTO> findAll() {
        logger.info("Finding all People");
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one Person");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this " + id));
        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Creating a Person");

        var entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating a Person");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this " + person.getId()));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting a Person");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this " + id));
        repository.delete(entity);
    }
}
