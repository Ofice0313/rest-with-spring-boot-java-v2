package devcaleb.rest_with_spring_boot_java_v2.controllers;

import devcaleb.rest_with_spring_boot_java_v2.entities.Person;
import devcaleb.rest_with_spring_boot_java_v2.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/people")
public class PersonController {

    @Autowired
    private PersonService service;

    @RequestMapping(value = "/{id}",
        method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Person> findAll() {
        return service.findAll();
    }
}
