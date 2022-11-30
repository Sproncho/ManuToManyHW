package telran.java2022.book.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import telran.java2022.book.model.Author;

import java.util.stream.Stream;

public interface AuthorRepository extends CrudRepository<Author, String> {

    @Query(value = "SELECT * FROM  BOOK_AUTHORS b join\n" +
            "AUTHOR a on b.AUTHORS_NAME = a.NAME \n" +
            "where b.BOOK_ISBN  = ?1" , nativeQuery = true)
    Stream<Author>findAuthorsByBook(String isbn);
}
