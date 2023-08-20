package com.aspire.takehome.miniaspire;

import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import com.aspire.takehome.miniaspire.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class MiniAspireApplication {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void initUsers() {
		List<UserEntity> users = Stream.of(
				new UserEntity(101L, "Sachin", "sachin123", "sachindarade11g@gmail.com", true),
				new UserEntity(102L, "Aspire", "aspire123", "admin@aspire.com", true),
				new UserEntity(103L, "user2", "pwd2", "user2@gmail.com", false),
				new UserEntity(104L, "user3", "pwd3", "user3@gmail.com", false)
		).collect(Collectors.toList());
		userRepository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(MiniAspireApplication.class, args);
	}

}
