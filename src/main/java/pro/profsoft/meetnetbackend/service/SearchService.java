package pro.profsoft.meetnetbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pro.profsoft.meetnetbackend.exception.EntityNotFoundException;
import pro.profsoft.meetnetbackend.model.City;
import pro.profsoft.meetnetbackend.model.Country;
import pro.profsoft.meetnetbackend.repository.CityRepository;
import pro.profsoft.meetnetbackend.repository.CountryRepository;

import java.util.List;

@Service
public class SearchService {
    private CountryRepository countryRepository;
    private CityRepository cityRepository;

    public SearchService(CountryRepository countryRepository, CityRepository cityRepository) {
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Country> getCountryByName(String searchStr) {
        return countryRepository.findAllByNameContainsIgnoreCase(searchStr);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<City> getCityByName(String searchStr) {
        return cityRepository.findAllByNameContainsIgnoreCase(searchStr);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<City> getCityByNameAndCountry(String searchStr, Long countryId) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new EntityNotFoundException("Country not found"));
        if (searchStr == null) {
            return cityRepository.findAllByCountry(country);
        } else {
            return cityRepository.findAllByCountryAndNameContainsIgnoreCase(country, searchStr);
        }
    }
}
