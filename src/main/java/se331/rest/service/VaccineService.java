package se331.rest.service;
import se331.rest.entity.Comment;
import se331.rest.entity.Event;
import se331.rest.entity.Vaccine;

import java.util.List;


public interface VaccineService {
    Vaccine save(Vaccine vaccine);
    List<Vaccine>getAllVaccine();

}
