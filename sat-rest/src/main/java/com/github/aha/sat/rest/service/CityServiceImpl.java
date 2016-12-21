package com.github.aha.sat.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.aha.sat.rest.domain.City;
import com.github.aha.sat.rest.repository.CityRepository;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    /*
     * (non-Javadoc)
     * @see com.github.aha.sat.rest.service.CityService#list(java.lang.String, java.lang.String)
     */
    @Override
    public List<City> list(String country, String sorting) {
        Sort sort = new Sort(Sort.Direction.ASC, sorting == null ? "id" : sorting);
        if (country == null) {
            return cityRepository.findAll(sort);
        } else {
            return cityRepository.findByCountry(country, sort);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.github.aha.sat.rest.service.CityService#item(long)
     */
    @Override
    public City item(long id) {
        return cityRepository.findOne(id);
    }

    /*
     * (non-Javadoc)
     * @see com.github.aha.sat.rest.service.CityService#save(com.github.aha.sat.rest.domain.City)
     */
    @Override
    public long save(City city) {
        City savedEntity = cityRepository.save(city);
        return savedEntity.getId();
    }

    /*
     * (non-Javadoc)
     * @see com.github.aha.sat.rest.service.CityService#delete(long)
     */
    @Override
    public void delete(long id) {
        cityRepository.delete(id);
    }

}
