package se331.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    Long id;
    String name;
    String location;
    String age;
    String image;
    EventOrganizerDTO organizer;
    List<String> imageUrls;
    List<CommentDTO> commentList;
    List<VaccineDTO> vaccineList;

}

