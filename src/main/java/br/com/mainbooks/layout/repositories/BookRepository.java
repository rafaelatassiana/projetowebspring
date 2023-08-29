package br.com.mainbooks.layout.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mainbooks.layout.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findAll();

    Book findById(Long id);
}
