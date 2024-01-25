package com.kanttanhed.Publication.service;

import com.kanttanhed.Publication.client.CommentClient;
import com.kanttanhed.Publication.domain.Publication;
import com.kanttanhed.Publication.exception.FallbackException;
import com.kanttanhed.Publication.mapper.PublicationMapper;
import com.kanttanhed.Publication.repository.PublicationRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PublicationService {

    @Autowired
    PublicationRepository publicationRepository;

    @Autowired
    PublicationMapper publicationMapper;

    @Autowired
    CommentClient commentClient;

    public void insert(Publication publication){
        // Convert the publication object to a publication entity using the mapper
        var publicationEntity = publicationMapper.toPublicationEntity(publication);
        publicationRepository.save(publicationEntity);
    }

    public List<Publication> findAll(){
       var publications = publicationRepository.findAll();

       // Convert each publication entity to a publication object and return the list
        return publications.stream()
               .map(publicationMapper::toPublication).toList();
    }

    @CircuitBreaker(name = "comments", fallbackMethod = "findByIdFallback")
    public Publication findById(String id){
        var publication = publicationRepository.findById(id)
                .map(publicationMapper::toPublication)
                .orElseThrow(RuntimeException::new);

        var comments = commentClient.getComments(id);
        publication.setComments(comments);

        return publication;
    }

    public Publication findByIdFallback(String id, Throwable cause) {
       log.warn("[warn] fallback with id {}", id);
       throw new FallbackException(cause);
    }
}
