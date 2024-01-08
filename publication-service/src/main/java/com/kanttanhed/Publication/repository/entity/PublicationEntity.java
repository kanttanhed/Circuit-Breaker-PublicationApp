package com.kanttanhed.Publication.repository.entity;

import com.kanttanhed.Publication.domain.Comment;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@Document(collection = "publications")
public class PublicationEntity {

    @MongoId
    private String id;
    private String title;
    private String imageUrl;
    private String text;
}
