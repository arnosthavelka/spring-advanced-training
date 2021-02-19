package com.github.aha.sat.rest.city;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> list(String country, String sorting) {
		Sort sort = Sort.by(sorting == null ? "id" : sorting);
		sort.descending();
        if (country == null) {
            return cityRepository.findAll(sort);
        } else {
            return cityRepository.findByCountry(country, sort);
        }
    }

    @Override
	public City item(Long id) {
		return cityRepository.getOne(id);
    }

    @Override
    public long save(City city) {
        City savedEntity = cityRepository.save(city);
        return savedEntity.getId();
    }

    @Override
	public void delete(Long id) {
		cityRepository.deleteById(id);
    }

}
