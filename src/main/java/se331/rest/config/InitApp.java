package se331.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.rest.entity.Comment;
import se331.rest.entity.Event;
import se331.rest.entity.Organizer;
import se331.rest.entity.Vaccine;
import se331.rest.repository.CommentRepository;
import se331.rest.repository.EventRepository;
import se331.rest.repository.OrganizerRepository;
import se331.rest.repository.VaccineRepository;
import se331.rest.security.entity.Authority;
import se331.rest.security.entity.AuthorityName;
import se331.rest.security.entity.User;
import se331.rest.security.repository.AuthorityRepository;
import se331.rest.security.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    OrganizerRepository organizerRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    VaccineRepository vaccineRepository;
    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        User user1,user2,user3;

            PasswordEncoder encoder = new BCryptPasswordEncoder();
            Authority authUser = Authority.builder().name(AuthorityName.USER).build();
            Authority authAdmin = Authority.builder().name(AuthorityName.ADMIN).build();
            Authority authDoctor = Authority.builder().name(AuthorityName.DOCTER).build();
            user1 = User.builder()
                    .username("admin")
                    .password(encoder.encode("admin"))
                    .firstname("admin")
                    .lastname("admin")
                    .email("admin@admin.com")
                    .image("https://media.discordapp.net/attachments/859092780805914644/1036578899624198174/unknown.png")
                    .enabled(true)
                    .lastPasswordResetDate(Date.from(LocalDate.of(2022,12,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .build();

            user2 = User.builder()
                    .username("user")
                    .password(encoder.encode("user"))
                    .firstname("user")
                    .lastname("user")
                    .email("user@user.com")
                    .enabled(true)
                    .lastPasswordResetDate(Date.from(LocalDate.of(2022,12,01)
                            .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .build();
            user3 = User.builder()
                    .username("doctor")
                    .password(encoder.encode("doctor"))
                    .firstname("doctor")
                    .lastname("doctor")
                    .email("doctor@docter.com")
                    .enabled(true)
                    .lastPasswordResetDate(Date.from(LocalDate.of(2022,12,01)
                            .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .build();
            authorityRepository.save(authUser);
            authorityRepository.save(authAdmin);
            authorityRepository.save(authDoctor);
            user1.getAuthorities().add(authUser);
            user1.getAuthorities().add(authAdmin);
            user2.getAuthorities().add(authUser);
            user3.getAuthorities().add(authDoctor);
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

        Event tempEvent = null;
        Comment  comment = null;
        Vaccine vaccine1,vaccine2,vaccine3 = null;

        Organizer org1, org2, org3;
        org1 = organizerRepository.save(Organizer.builder()
                .name("Chiang Mai Ram").build());
        org2 = organizerRepository.save(Organizer.builder()
                .name("Suandok").build());
        org3 = organizerRepository.save(Organizer.builder()
                .name("Suanproong").build());

        tempEvent = eventRepository.save(Event.builder()
                .name("John Smith")
                .location("Chiang Mai")
                .age("20")
                .organizer(org1)
                .build());
        comment = commentRepository.save(Comment.builder()
                .comment("Healthy")
                .docter("Dr. Horace Lehnhoff").build());
        vaccine1 = vaccineRepository.save(Vaccine.builder()
                .type("Sinovac").timestamp(Date.from(LocalDate.of(2022,12,31).atStartOfDay(ZoneId.systemDefault()).toInstant()).toString())
                .build());
        vaccine2 = vaccineRepository.save(Vaccine.builder()
                .type("Moderna").timestamp(Date.from(LocalDate.of(2022,11,12).atStartOfDay(ZoneId.systemDefault()).toInstant()).toString())
                .build());
        tempEvent.getCommentList().add(comment);
        tempEvent.getVaccineList().add(vaccine1);
        tempEvent.getVaccineList().add(vaccine2);
        org1.getOwnEvents().add(tempEvent);
        tempEvent = eventRepository.save(Event.builder()
                .name("Berry Barton")
                .age("21")
                .location("Lampang")
                .organizer(org1)
                .build());
        comment = commentRepository.save(Comment.builder()
                .comment("Smart")
                .docter("Dr. Elijah Francies").build());
        vaccine2 = vaccineRepository.save(Vaccine.builder()
                .type("Moderna").timestamp(Date.from(LocalDate.of(2022,3,12).atStartOfDay(ZoneId.systemDefault()).toInstant()).toString())
                .build());
        tempEvent.getCommentList().add(comment);
        tempEvent.getVaccineList().add(vaccine2);
        org1.getOwnEvents().add(tempEvent);
        tempEvent = eventRepository.save(Event.builder()
                .name("William Washington")
                .age("21")
                .location("Bankkok")
                .organizer(org2)
                .build());
        comment = commentRepository.save(Comment.builder()
                .comment("strong")
                .docter("Dr. Frances Mikami").build());
        vaccine3 = vaccineRepository.save(Vaccine.builder()
                .type("Pfizer").timestamp(Date.from(LocalDate.of(2022,12,01).atStartOfDay(ZoneId.systemDefault()).toInstant()).toString())
                .build());
        tempEvent.getCommentList().add(comment);
        tempEvent.getVaccineList().add(vaccine3);
        org2.getOwnEvents().add(tempEvent);
        tempEvent = eventRepository.save(Event.builder()
                .name("Josh Washington")
                .location("Chiang Mai")
                .age("32")
                .organizer(org3)
                .build());
        comment = commentRepository.save(Comment.builder()
                .comment("Cute")
                .docter("Dr. Maxwell Renfroe").build());
        vaccine1 = vaccineRepository.save(Vaccine.builder()
                .type("Sinovac").timestamp(Date.from(LocalDate.of(2022,2,03).atStartOfDay(ZoneId.systemDefault()).toInstant()).toString())
                .build());
        vaccine3 = vaccineRepository.save(Vaccine.builder()
                .type("Pfizer").timestamp(Date.from(LocalDate.of(2022,1,17).atStartOfDay(ZoneId.systemDefault()).toInstant()).toString())
                .build());
        tempEvent.getCommentList().add(comment);
        tempEvent.getVaccineList().add(vaccine1);
        tempEvent.getVaccineList().add(vaccine3);
        org3.getOwnEvents().add(tempEvent);
        org1.setUser(user1);
        user1.setOrganizer(org1);
        org2.setUser(user2);
        user2.setOrganizer(org2);
        org3.setUser(user3);
        user3.setOrganizer(org3);
    }
}