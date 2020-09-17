package pro.profsoft.meetnetbackend.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.profsoft.meetnetbackend.model.City;
import pro.profsoft.meetnetbackend.model.Country;
import pro.profsoft.meetnetbackend.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @ApiOperation(value = "Список стран для поиска",
            notes = "Без параметра searchTxt возвращает список всех стран")
    @Cacheable("country")
    @GetMapping(value = "country")
    public List<Country> getCountriesByPartOfName(String searchTxt) {
        if (searchTxt == null) {
            return searchService.getAllCountries();
        } else {
            return searchService.getCountryByName(searchTxt);
        }
    }

    @ApiOperation(value = "Список городов для поиска",
            notes = "searchTxt - часть названия города. Без параметра ищет по всем городам." +
                    " countryId - только города данной страны. Без параметра ищет по всем странам. ")
    @Cacheable("city")
    @GetMapping(value = "city")
    public List<City> getCitiesByPartOfNameAndCountry(String searchTxt, Long countryId) {
        if (countryId == null) {
            if (searchTxt == null) {
                return searchService.getAllCities();
            } else {
                return searchService.getCityByName(searchTxt);
            }
        } else {
            return searchService.getCityByNameAndCountry(searchTxt, countryId);
        }
    }
}
