package pro.profsoft.meetnetbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.profsoft.meetnetbackend.model.City;
import pro.profsoft.meetnetbackend.model.Country;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findAllByCountry(Country c);
    List<City> findAllByNameContainsIgnoreCase(String searchTxt);
    List<City> findAllByCountryAndNameContainsIgnoreCase(Country c, String searchTxt);
}
