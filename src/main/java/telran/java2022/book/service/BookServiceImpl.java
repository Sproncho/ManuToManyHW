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
import telran.java2022.book.dto.exceptions.EntityNotFoundException;
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
    @Transactional(readOnly = true)
    public BookDto findBookByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(book,BookDto.class);
    }

    @Override
    @Transactional
    public BookDto removeBook(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        bookRepository.deleteById(isbn);
        return modelMapper.map(book,BookDto.class);
    }

    @Override
    @Transactional
    public BookDto updateBook(String isbn, String title) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        book.setTitle(title);
        bookRepository.save(book);
        return modelMapper.map(book,BookDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<BookDto> findBooksByAuthor(String author) {
        return bookRepository.findBooksByAuthors(author)
                .map(b -> modelMapper.map(b,BookDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<BookDto> findBooksByPublisher(String publisher) {

        return bookRepository.findBooksByPublisher(publisher)
                .map(b -> modelMapper.map(b,BookDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<AuthorDto> findBookAuthors(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        return book.getAuthors().stream().map(a -> modelMapper.map(a,AuthorDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<String> findPublishersByAuthor(String author) {
        return publisherRepository.findPublishersByAuthor(author).map(p -> p.getPublisherName()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AuthorDto removeAuthor(String author) {
        Author authorImpl = authorRepository.findById(author).orElseThrow(EntityNotFoundException::new);
        authorRepository.deleteById(author);
        return modelMapper.map(authorImpl, AuthorDto.class);
    }
}
