package se331.rest.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import  se331.rest.entity.Vaccine;
import se331.rest.repository.VaccineRepository;

@Repository
public class VaccineDaoDbImpl implements VaccineDao{
    @Autowired
    VaccineRepository vaccineRepository;
    @Override
    public Vaccine save(Vaccine vaccine){
        return vaccineRepository.save(vaccine);

    }
    @Override
    public Page<Vaccine> getVaccine(Pageable pageRequest) {
        return vaccineRepository.findAll(pageRequest);
    }
}
