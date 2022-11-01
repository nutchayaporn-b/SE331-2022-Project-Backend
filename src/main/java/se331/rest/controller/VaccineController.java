package se331.rest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import se331.rest.entity.Event;
import se331.rest.entity.Vaccine;
import se331.rest.service.CommentService;
import se331.rest.service.EventService;
import se331.rest.service.VaccineService;
import se331.rest.util.LabMapper;
@Controller
public class VaccineController {
    @Autowired
    VaccineService vaccineService;

    @Autowired
    EventService eventService;

    @GetMapping("/vaccine")
    ResponseEntity<?> getVaccines() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getVaccineDto(vaccineService.getAllVaccine()));
    }

    @PostMapping("/vaccine/event/{id}")
    ResponseEntity<?> addVaccine(@PathVariable("id") Long id,@RequestBody Vaccine vaccine){
        Event output = eventService.getEvent(id);
        output.getVaccineList().add(vaccine);
        Vaccine output2 = vaccineService.save(vaccine);
        return ResponseEntity.ok(LabMapper.INSTANCE.getVaccineDto(output2));
    }
}
