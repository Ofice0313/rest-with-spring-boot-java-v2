package devcaleb.rest_with_spring_boot_java_v2.services;

import devcaleb.rest_with_spring_boot_java_v2.controllers.BookController;
import devcaleb.rest_with_spring_boot_java_v2.data.dto.BookDTO;
import devcaleb.rest_with_spring_boot_java_v2.entities.Book;
import devcaleb.rest_with_spring_boot_java_v2.exceptions.RequiredObjectIsNullException;
import devcaleb.rest_with_spring_boot_java_v2.exceptions.ResourceNotFoundException;
import devcaleb.rest_with_spring_boot_java_v2.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static devcaleb.rest_with_spring_boot_java_v2.mapper.ObjectMapper.parseListObjects;
import static devcaleb.rest_with_spring_boot_java_v2.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    private Logger logger = Logger.getLogger(BookService.class.getName());

    @Autowired
    private BookRepository repository;

    public List<BookDTO> findAll() {
        logger.info("Finding all People");
        var books = parseListObjects(repository.findAll(), BookDTO.class);
        books.forEach(this::addHateoasLinks);
        return books;
    }

    public BookDTO findById(Long id) {
        logger.info("Finding one Book");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this " + id));
        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class)
                .findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class)
                .delete(dto.getId())).withRel("delete").withType("DELETE"));
        dto.add(linkTo(methodOn(BookController.class)
                .create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class)
                .update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class)
                .findAll()).withRel("findAll").withType("GET"));
    }

    public BookDTO create(BookDTO book) {

        if(book == null) throw new RequiredObjectIsNullException();
        logger.info("Creating a Book");

        var entity = parseObject(book, Book.class);
        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO update(BookDTO book) {
        logger.info("Updating a Book");
        Book entity = repository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this " + book.getId()));
        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var dto =  parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting a Book");
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this " + id));
        repository.delete(entity);
    }
}
