package com.github.aha.sat.rest.city;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.aha.sat.rest.city.resource.CityBaseResource;

@Service
@Transactional
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	public List<City> search(String country, String sorting) {
		Sort sort = Sort.by(sorting == null ? "id" : sorting);
		sort.descending();
		if (country == null) {
			return cityRepository.findAll(sort);
		} else {
			return cityRepository.findByCountry(country, sort);
		}
	}

	public City getOne(Long id) {
		return cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(String.format("City [id=%d] was not found!", id)));
	}

	public City save(Long cityId, CityBaseResource cityResource) {
		var city = getOrCreateCity(cityId);
		updateCity(city, cityResource);
		return cityRepository.save(city);
	}

	private City getOrCreateCity(Long cityId) {
		if (isNull(cityId)) {
			return new City();
		}
		return cityRepository.getOne(cityId);
	}

	private void updateCity(City city, CityBaseResource cityResource) {
		if (nonNull(cityResource.getName())) {
			city.setName(cityResource.getName());
		}
		if (nonNull(cityResource.getState())) {
			city.setState(cityResource.getState());
		}
		if (nonNull(cityResource.getCountry())) {
			city.setCountry(cityResource.getCountry());
		}
	}

	public void delete(Long id) {
		cityRepository.deleteById(id);
	}

}
