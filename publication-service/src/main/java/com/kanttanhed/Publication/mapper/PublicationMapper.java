package com.kanttanhed.Publication.mapper;

import com.kanttanhed.Publication.controller.request.PublicationRequest;
import com.kanttanhed.Publication.domain.Publication;
import com.kanttanhed.Publication.repository.entity.PublicationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    // Mapping method that defines how to convert a Publication object
    // into a PublicationEntity object.
    PublicationEntity toPublicationEntity(Publication publication);

    Publication  toPublication(PublicationEntity publicationEntity);

    Publication  toPublication(PublicationRequest publicationRequest);

}
