package se331.rest.entity;
import antlr.collections.List;
import  lombok.*;
import se331.rest.security.entity.User;

import javax.persistence.*;
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String comment;
    String docter;
}
