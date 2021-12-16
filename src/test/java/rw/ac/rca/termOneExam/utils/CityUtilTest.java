package rw.ac.rca.termOneExam.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.repository.ICityRepository;
import rw.ac.rca.termOneExam.service.CityService;

import java.util.Arrays;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityUtilTest {
    @InjectMocks
    private CityService cityService;
    @Mock
    private ICityRepository cityRepository;


    // assert  that one should not be greater than 40 degree
    @Test
    public void getDegree_notMore_than_40() {
        when(cityRepository.findAll()).thenReturn(Arrays.asList(new City(1L, "Kaisa", 20,20)));
        assertTrue(40>cityService.getAll().get(0).getFahrenheit());
    }

    // assert that one should not be less than 10 degree
    @Test
    public void getDegree_notless_than_10() {
        when(cityRepository.findAll()).thenReturn(Arrays.asList(new City(1L, "Kaisa", 20,20)));
        assertTrue(10< cityService.getAll().get(0).getFahrenheit());
    }

    // assert method such that no cities contains "musanze" AND "kigali"
    @Test
    public void getNoCities_Called_Kigali_and_Musanze() {
        when(cityRepository.findAll()).thenReturn(Arrays.asList(new City(1L, "Kaisa", 20,20),new City(2L, "Kaddisa", 20,202)));
        assertNotSame("Kigali",cityService.getAll().get(0).getName());
        assertNotSame("Musanze",cityService.getAll().get(1).getName());
    }

}
