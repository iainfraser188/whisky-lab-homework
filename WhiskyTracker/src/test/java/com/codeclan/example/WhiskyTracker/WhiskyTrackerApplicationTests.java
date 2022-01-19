package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canGetWhiskyByYear(){
		List<Whisky> foundWhiskys = whiskyRepository.findWhiskyByYear(2018);
		assertEquals(6,foundWhiskys.size());

	}

	@Test
	public void canGetWhiskyByDistilleryAndYear(){
		List<Whisky> foundWhiskys = whiskyRepository.findWhiskiesByDistilleryIdAndYear(1L,2018);
		assertEquals(2,foundWhiskys.size());
		assertEquals("The Glendronach Revival",foundWhiskys.get(0).getName());
	}

	@Test
	public void canGetWhiskyByRegion(){
		List<Whisky> foundWhiskys = whiskyRepository.findWhiskyByDistilleryRegion("Highlands");
		assertEquals(7,foundWhiskys.size());
	}
}
