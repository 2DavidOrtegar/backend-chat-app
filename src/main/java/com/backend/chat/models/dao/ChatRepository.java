package com.backend.chat.models.dao;

import com.backend.chat.models.documents.Mensaje;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<Mensaje, String> {

    List<Mensaje> findAll();

}
