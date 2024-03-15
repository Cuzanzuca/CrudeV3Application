package com.github.cuzanzuca.crudeV3Application.repository;

import com.github.cuzanzuca.crudeV3Application.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends  JpaRepository<Book,Long>
{

}
