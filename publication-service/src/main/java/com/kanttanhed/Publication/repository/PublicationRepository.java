package com.kanttanhed.Publication.repository;

import com.kanttanhed.Publication.repository.entity.PublicationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PublicationRepository extends MongoRepository<PublicationEntity, String> {
}
