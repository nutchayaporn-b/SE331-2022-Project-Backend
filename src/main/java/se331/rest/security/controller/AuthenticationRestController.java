package se331.rest.security.controller;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import se331.rest.entity.Event;
import se331.rest.entity.Organizer;
import se331.rest.repository.EventRepository;
import se331.rest.repository.OrganizerRepository;
import se331.rest.security.entity.Authority;
import se331.rest.security.entity.AuthorityName;
import se331.rest.security.entity.JwtUser;
import se331.rest.security.entity.User;
import se331.rest.security.repository.AuthorityRepository;
import se331.rest.security.repository.UserRepository;
import se331.rest.security.service.UserService;
import se331.rest.security.util.JwtTokenUtil;
import se331.rest.util.LabMapper;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@RestController
@CrossOrigin
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    EventRepository eventRepository;
    @Autowired
    OrganizerRepository organizerRepository;

    @PostMapping("${jwt.route.authentication.path}")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        Map result = new HashMap();
        result.put("token", token);
        User user = userRepository.findById(((JwtUser) userDetails).getId()).orElse(null);
        if (user.getOrganizer() != null) {
            result.put("user", LabMapper.INSTANCE.getOrganizerAuthDTO( user.getOrganizer()));
        }

        return ResponseEntity.ok(result);
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws  AuthenticationException{
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser = Authority.builder().name(AuthorityName.USER).build();
        authorityRepository.save(authUser);
        User user2 = User.builder()
                .enabled(true)
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .location(user.getLocation())
                .password(encoder.encode(user.getPassword()))
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user2.getAuthorities().add(authUser);
        userRepository.save(user2);

        Event event = Event.builder().name(user.getFirstname() + " " + user.getLastname()).location(user.getLocation()).user(user2).build();
        eventRepository.save(event);

        userService.save(user2);
        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(user2));
    }


}
