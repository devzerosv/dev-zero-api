package dev.devzero.api.config;

import java.util.ArrayList;
import java.util.List;

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

import dev.devzero.api.model.Menu;
import dev.devzero.api.model.Perfil;
import dev.devzero.api.model.Person;
import dev.devzero.api.model.RolMenu;
import dev.devzero.api.model.Role;
import dev.devzero.api.model.User;
import dev.devzero.api.model.UserPerfil;
import dev.devzero.api.repository.MenuRepository;
import dev.devzero.api.repository.PerfilRepository;
import dev.devzero.api.repository.RolMenuRepository;
import dev.devzero.api.repository.RoleRepository;
import dev.devzero.api.repository.UserPerfilRepository;
import dev.devzero.api.repository.UserRepository;

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
	CommandLineRunner initDatabase(UserRepository userRepository, PerfilRepository perfilRepository,
			UserPerfilRepository userPerfilRepository, RoleRepository roleRepository, MenuRepository menuRepository,
			RolMenuRepository rolMenuRepository) {

		List<User> defaultUserList = new ArrayList<>();
		List<Perfil> defaulProfile = new ArrayList<>();
		List<UserPerfil> defaulUserProfile = new ArrayList<>();

		List<Role> defaulRole = new ArrayList<>();
		List<Menu> defaulMenu = new ArrayList<>();
		List<RolMenu> defaulRolMenu = new ArrayList<>();

		Person adminUser = new Person();
		String password = new BCryptPasswordEncoder().encode("admin");
		adminUser.setFirstName("Mauricio");
		adminUser.setLastName("Saca");
		adminUser.setUsername("msaca");
		adminUser.setPassword(password);
		adminUser.setEmail("saca.menendez@gmai.com");
		adminUser.setEnabled(true);

		Role role = new Role();
		role.setRoleName("ROLE_ADMIN");
		role.setDescription("Administrador del sistema");

		Perfil adminPerfil = new Perfil();
		adminPerfil.setProfileName("OP_DO_ADMIN");
		adminPerfil.setCanView(true);
		adminPerfil.setCanAdd(true);
		adminPerfil.setCanEdit(true);
		adminPerfil.setCanDelete(true);
		adminPerfil.setRole(role);
		
		UserPerfil userProfile = new UserPerfil();
		userProfile.setUser(adminUser);
		userProfile.setPerfil(adminPerfil);

		Menu menu = new Menu();
		menu.setDisplayNameOption("test");
		menu.setUrl("test");
		menu.setViewName("test");
		menu.setMenuOrder(1);

		RolMenu rolMenu = new RolMenu();
		rolMenu.setRole(role);
		rolMenu.setMenu(menu);

		defaultUserList.add(adminUser);
		defaulProfile.add(adminPerfil);
		defaulUserProfile.add(userProfile);

		defaulRole.add(role);
		defaulMenu.add(menu);
		defaulRolMenu.add(rolMenu);

		return args -> {
			userRepository.saveAll(defaultUserList);
			roleRepository.saveAll(defaulRole);
			
			perfilRepository.saveAll(defaulProfile);
			userPerfilRepository.saveAll(defaulUserProfile);

			menuRepository.saveAll(defaulMenu);
			rolMenuRepository.saveAll(defaulRolMenu);

			System.out.println("DONE!!!!!!!!!");
		};
	}

}
