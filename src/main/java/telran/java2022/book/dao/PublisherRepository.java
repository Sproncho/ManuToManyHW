package telran.java2022.book.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import telran.java2022.book.model.Publisher;

import java.util.stream.Stream;

public interface PublisherRepository extends CrudRepository<Publisher,String> {

    @Query(value = "SELECT p.* FROM  BOOK_AUTHORS b\n" +
            "join AUTHOR a on b.AUTHORS_NAME = a.NAME \n" +
            "join BOOK  bk on bk.ISBN =b.BOOK_ISBN \n" +
            "join PUBLISHER p on bk.PUBLISHER_PUBLISHER_NAME = p.PUBLISHER_NAME \n" +
            "where a.name = ?1 \n", nativeQuery = true)
    public Stream<Publisher> findPublishersByAuthor(String author);
}
