package com.example.cv;

import com.example.cv.Model.users;
import com.example.cv.Repository.userRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CvApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private  userRepository userRepository;
	@Test
	public void findByNameMethod()
	{
		List<users> us = userRepository.findAll();
		us.stream().forEach(System.out::println);
	}

}
