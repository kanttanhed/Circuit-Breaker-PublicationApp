package com.kanttanhed.Publication.controller;

import com.kanttanhed.Publication.controller.request.PublicationRequest;
import com.kanttanhed.Publication.domain.Publication;
import com.kanttanhed.Publication.mapper.PublicationMapper;
import com.kanttanhed.Publication.service.PublicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publications")
public class PublicationController {

    @Autowired
    PublicationService publicationService;

    @Autowired
    PublicationMapper publicationMapper;


    // PostMapping annotation denotes that this method handles POST requests
    // ResponseStatus sets the HTTP status to CREATED (201) for successful execution of this method
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@Valid @RequestBody PublicationRequest publicationRequest) {
        var publications = publicationMapper.toPublication(publicationRequest);
        publicationService.insert(publications);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Publication> listAll() {
        return publicationService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publication findById(@PathVariable("id") String id) {
        return publicationService.findById(id);
    }
}
