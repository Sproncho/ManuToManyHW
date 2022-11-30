package telran.java2022.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDto {
    String isbn;
    String title;
    Set<AuthorDto> authors;
    String publisher;
}
