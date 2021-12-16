package rw.ac.rca.termOneExam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import rw.ac.rca.termOneExam.Exceptions.CustomException;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;

@Service
public class CityService {

	@Autowired
	private ICityRepository cityRepository;
	
	public Optional<City> getById(long id) {
		
		return cityRepository.findById(id);
	}

	public List<City> getAll() {
		
		return cityRepository.findAll();
	}

	public boolean existsByName(String name) {
		
		return cityRepository.existsByName(name);
	}

	public City save(CreateCityDTO dto) {
		City city =  new City(dto.getName(), dto.getWeather());
		return cityRepository.save(city);
	}

	//Method to get all weather degree for every city
	public String getCityWeather(String city_name) {
		Long id= cityRepository.findByName(city_name).getId();
		City record= cityRepository.findById(id).orElseThrow(() -> new CustomException("No city Found",HttpStatus.NOT_FOUND));
		return "City ::>"+record.getName()+"\nDegrees ::>"+record.getFahrenheit();
	}

}
