package telran.java2022.book.model;

import lombok.*;
import javax.persistence.Id;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "name")
@Entity
public class Author implements Serializable {
    public static final long serialVersionUID = 9283394567L;
    @Id
    String name;
    LocalDate birthDate;
}
