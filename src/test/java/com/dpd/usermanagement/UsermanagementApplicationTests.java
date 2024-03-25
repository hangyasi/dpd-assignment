package com.dpd.usermanagement;

import com.dpd.usermanagement.dto.AddressDto;
import com.dpd.usermanagement.dto.PhoneNumberDto;
import com.dpd.usermanagement.dto.UserDto;
import com.dpd.usermanagement.entity.Address;
import com.dpd.usermanagement.entity.PhoneNumber;
import com.dpd.usermanagement.entity.User;
import com.dpd.usermanagement.repository.AddressRepository;
import com.dpd.usermanagement.repository.PhoneNumberRepository;
import com.dpd.usermanagement.repository.UserRepository;
import com.dpd.usermanagement.service.UserService;
import lombok.Data;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
		locations = "classpath:application-integrationtest.properties")
class UsermanagementApplicationTests {

	@LocalServerPort
	private int port;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;

	TestRestTemplate restTemplate = new TestRestTemplate();

	@BeforeEach
	void setUp() {
		userRepository.deleteAll();
	}

	@Test
	void testGetUserEndpoint() {
		userRepository.save(getTestUserEntity());
		List<UserDto> users = restTemplate
				.exchange(createURLWithPort("/user"), HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDto>>() {})
				.getBody();

		assertThat(users.size(), is(1));
		assertThat(users.get(0).getName(), is(getTestUserEntity().getName()));
		assertThat(users.get(0).getPhoneNumbers().get(0).getPhoneNumber(), is(getTestUserEntity().getPhoneNumbers().get(0).getPhoneNumber()));
	}

	@Test
	void testPostUserEndpoint() {
		HttpEntity<UserDto> entity = new HttpEntity<>(getTestUserDto());
		restTemplate.exchange(createURLWithPort("/user"), HttpMethod.POST, entity, new ParameterizedTypeReference<List<UserDto>>() {});

		List<UserDto> users = userService.getUsers();

		assertThat(users.size(), is(1));
		assertThat(users.get(0).getName(), is(getTestUserDto().getName()));
		assertThat(users.get(0).getPhoneNumbers().get(0).getPhoneNumber(), is(getTestUserDto().getPhoneNumbers().get(0).getPhoneNumber()));
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	private User getTestUserEntity() {
		Address address = new Address();
		address.setId(1);
		address.setCountry("HUN");
		address.setCity("BUDAPEST");
		address.setStreet("STREET");
		address.setPostalCode("112244");
		address.setNumber("12/f");

		PhoneNumber pn = new PhoneNumber();
		pn.setId(1);

		User user = new User();
		user.setId(1);
		user.setName("TESTUSER");
		user.setBirthdate(LocalDate.now());
		user.setBirthPlace("TESTPLACE");
		user.setEmailAddress("test@example.com");

		address.setUser(user);
		pn.setUser(user);

		user.setAddresses(Arrays.asList(address));
		user.setPhoneNumbers(Arrays.asList(pn));

		return user;
	}

	private UserDto getTestUserDto() {
		AddressDto address = new AddressDto();
		address.setId(1);
		address.setCountry("HUN");
		address.setCity("BUDAPEST");
		address.setStreet("STREET");
		address.setPostalCode("112244");
		address.setNumber("12/f");

		PhoneNumberDto pn = new PhoneNumberDto();
		pn.setId(1);

		UserDto user = new UserDto();
		user.setId(1);
		user.setName("TESTUSER");
		user.setBirthdate(LocalDate.now());
		user.setBirthPlace("TESTPLACE");
		user.setEmailAddress("test@example.com");
		user.setAddresses(Arrays.asList(address));
		user.setPhoneNumbers(Arrays.asList(pn));

		return user;
	}
}
