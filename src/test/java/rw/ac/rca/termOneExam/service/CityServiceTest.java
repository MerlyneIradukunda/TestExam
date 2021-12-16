package rw.ac.rca.termOneExam.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    @Mock
    private ICityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

//    tests for save(success&&fail)
    @Test
    public void saved_success_test() {
        when(cityRepository.save(any(City.class))).thenReturn(new City(1L, "Kaisa", 20,20));

        assertEquals("Kaisa", cityService.save(new CreateCityDTO()).getName());
    }
    // test for get by id (success & fail)
    @Test
    public void findById_success_test() {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(new City(1L, "Kaisa", 20,20)));
        assertEquals("Kaisa", cityService.getById(1L).get().getName());
    }


    // test for getall(success)
    @Test
    public void get_all_success_test() {
        when(cityRepository.findAll()).thenReturn(Arrays.asList(new City(1L, "Kaisa", 20,20)));

        assertEquals("Kaisa", cityService.getAll().get(0).getName());
    }

    // test exist by name
    @Test
    public void existByName_success_test () {
        City record= new City(101,"Kigali",24,20);

        when(cityRepository.existsByName(record.getName())).thenReturn(Boolean.TRUE);
        assertEquals(Boolean.TRUE, cityService.existsByName(record.getName()));
    }
}
