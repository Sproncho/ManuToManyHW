package telran.java2022.book.model;

import lombok.*;
import javax.persistence.Id;

import javax.persistence.Entity;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "publisherName")
@Entity
public class Publisher implements Serializable {
    public static final long serialVersionUID = 342908577645382L;
    @Id
    String publisherName;
}
