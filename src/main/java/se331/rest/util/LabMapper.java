package se331.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.rest.entity.*;
import se331.rest.security.entity.User;
import se331.rest.security.entity.UserDTO;
import se331.rest.entity.CommentDTO;
import se331.rest.entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);

    EventDTO getEventDto(Event event);

    UserDTO getUserDTO(User user);
    VaccineDTO getVaccineDto(Vaccine vaccine);
    CommentDTO getCommentDto(Comment comment);
    List<CommentDTO> getCommentDto(List<Comment> comments);
    List<EventDTO> getEventDto(List<Event> events);
    List<VaccineDTO> getVaccineDto(List<Vaccine> vaccine);

    OrganizerDTO getOrganizerDTO(Organizer organizer);

    List<OrganizerDTO> getOrganizerDTO(List<Organizer> organizers);

    @Mapping(target = "authorities", expression = "java(organizer.getUser().getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    OrganizerAuthDTO getOrganizerAuthDTO(Organizer organizer);

}