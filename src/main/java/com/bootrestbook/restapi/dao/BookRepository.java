package com.bootrestbook.restapi.dao;

import com.bootrestbook.restapi.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Integer> {
    public Book findById(int id);
}