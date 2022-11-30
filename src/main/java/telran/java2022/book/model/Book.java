package telran.java2022.book.model;

import lombok.*;
import telran.java2022.book.dto.AuthorDto;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "isbn")
@Entity
public class Book implements Serializable {
    public static final long serialVersionUID = 34565638366789l;
    @Id
    String isbn;
    String title;
    @ManyToMany
    Set<Author> authors;
    @ManyToOne
    Publisher publisher;
}
