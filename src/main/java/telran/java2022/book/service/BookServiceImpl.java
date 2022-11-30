package telran.java2022.book.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import telran.java2022.book.dao.AuthorRepository;
import telran.java2022.book.dao.BookRepository;
import telran.java2022.book.dao.PublisherRepository;
import telran.java2022.book.dto.AuthorDto;
import telran.java2022.book.dto.BookDto;
import telran.java2022.book.model.Author;
import telran.java2022.book.model.Book;
import telran.java2022.book.model.Publisher;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    final BookRepository bookRepository;
    final AuthorRepository authorRepository;
    final PublisherRepository publisherRepository;
    final ModelMapper modelMapper;
    @Override
    @Transactional
    public boolean addBook(BookDto bookDto) {
        if(bookRepository.existsById(bookDto.getIsbn()))
            return false;
        Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
                .orElse(publisherRepository.save(new Publisher(bookDto.getPublisher())));
        Set<Author> authors = bookDto.getAuthors().stream()
                .map(a -> authorRepository.findById(a.getName()).
                        orElse(authorRepository.save(new Author(a.getName(),a.getBirthDate()))))
                .collect(Collectors.toSet());
        Book book = new Book(bookDto.getIsbn(),bookDto.getTitle(),authors,publisher);
        bookRepository.save(book);
        return true;
    }

    @Override
    public BookDto findBookByIsbn(String isbn) {
        return null;
    }

    @Override
    public BookDto removeBook(String isbn) {
        return null;
    }

    @Override
    public BookDto updateBook(String isbn, String title) {
        return null;
    }

    @Override
    public Iterable<BookDto> findBooksByAuthor(String author) {
        return null;
    }

    @Override
    public Iterable<BookDto> findBooksByPublisher(String publisher) {
        return null;
    }

    @Override
    public Iterable<AuthorDto> findBookAuthors(String isbn) {
        return null;
    }

    @Override
    public Iterable<String> findPublishersByAuthor(String author) {
        return null;
    }

    @Override
    public AuthorDto removeAuthor(String author) {
        return null;
    }
}
