package telran.java2022.book.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import telran.java2022.book.model.Author;

import java.util.stream.Stream;

public interface AuthorRepository extends CrudRepository<Author, String> {

}
