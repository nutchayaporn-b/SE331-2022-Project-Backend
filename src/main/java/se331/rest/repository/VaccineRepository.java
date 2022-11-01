package se331.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import se331.rest.entity.Vaccine;

import java.util.List;

public interface VaccineRepository extends JpaRepository<Vaccine,Long> {

}
