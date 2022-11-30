package telran.java2022.book.dao;

import org.springframework.data.repository.CrudRepository;
import telran.java2022.book.model.Book;

import java.util.stream.Stream;

public interface BookRepository extends CrudRepository<Book, String> {
    Stream<Book> findBooksByAuthors(String author);
    Stream<Book> findBooksByPublisher(String publisher);
}
