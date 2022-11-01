package se331.rest.entity;


import lombok.*;
import se331.rest.security.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    String name;
    String location;
    String age;
    String image;

    @OneToOne
    User user;

    @OneToMany

    @Builder.Default
    List<Comment> commentList= new ArrayList<>();
    @OneToMany
    @Builder.Default
    List<Vaccine> vaccineList = new ArrayList<>();


    @ManyToOne
    Organizer organizer;

    @ManyToMany(mappedBy = "eventHistory")
    List<Participant> participants;
    @ElementCollection
    List<String> imageUrls;


}


