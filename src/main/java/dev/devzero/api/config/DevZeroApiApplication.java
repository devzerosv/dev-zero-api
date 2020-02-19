package dev.devzero.api.config;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;

import dev.devzero.api.model.Authorities;
import dev.devzero.api.model.QUserPrincipal;
import dev.devzero.api.model.UserPrincipal;
import dev.devzero.api.model.enums.RoleEnum;
import dev.devzero.api.repository.AuthoritiesRepository;
import dev.devzero.api.repository.UserPrincipalRepository;

@SpringBootApplication
@ComponentScan({ "dev.devzero.api*" })
@EntityScan(basePackages = { "dev.devzero.api.model" })
@EnableJpaRepositories("dev.devzero.api.repository")
public class DevZeroApiApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DevZeroApiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DevZeroApiApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserPrincipalRepository userRepository,
			AuthoritiesRepository authoritiesRepository,  EntityManager entityManager) {

		List<UserPrincipal> defaultUserList = new ArrayList<>();
		List<Authorities> defaultAuthoritiesList = new ArrayList<>();

		UserPrincipal adminUser = new UserPrincipal();
		String password = new BCryptPasswordEncoder().encode("admin");
		adminUser.setName("Mauricio");
		adminUser.setLastName("Saca");
		adminUser.setUsername("msaca");
		adminUser.setPassword(password);
		adminUser.setEmail("saca.menendez@gmai.com");
		adminUser.setEnabled(true);

		Authorities auth = new Authorities();
		auth.setUserPrincipal(adminUser);
		auth.setRole(RoleEnum.ADMIN);

		UserPrincipal normalUser = new UserPrincipal();
		String passwordUser = new BCryptPasswordEncoder().encode("user");
		normalUser.setName("Applaudo");
		normalUser.setLastName("Studio");
		normalUser.setUsername("astudio");
		normalUser.setPassword(passwordUser);
		normalUser.setEmail("saca.menendez@gmai.com");
		normalUser.setEnabled(true);

		Authorities authUser = new Authorities();
		authUser.setUserPrincipal(normalUser);
		authUser.setRole(RoleEnum.USER);

		defaultUserList.add(adminUser);
		defaultUserList.add(normalUser);

		defaultAuthoritiesList.add(auth);
		defaultAuthoritiesList.add(authUser);

		return args -> {
			userRepository.saveAll(defaultUserList);
			authoritiesRepository.saveAll(defaultAuthoritiesList);
			
			JPAQuery q = new JPAQuery(entityManager);
			 q.from(QUserPrincipal.userPrincipal).where(QUserPrincipal.userPrincipal.username.eq("msaca"));
			 UserPrincipal test = (UserPrincipal) q.fetchOne();
			
			
			System.out.println(test.getName());
			
		};
	}

}
