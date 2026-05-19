package com.knut.vms;

import com.knut.vms.persistence.entity.PermissionEntity;
import com.knut.vms.persistence.entity.RoleEntity;
import com.knut.vms.persistence.entity.RoleEnum;
import com.knut.vms.persistence.entity.UserEntity;
import com.knut.vms.persistence.entity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class VmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VmsApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {

		return args -> {
			/*Create roles */
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();

			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR")
					.build();

			/* Create ROLES */

			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, readPermission))
					.build();

			RoleEntity roleInvited = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionList(Set.of(readPermission))
					.build();

			RoleEntity roleDeveloper = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			/* Create USERS */
			UserEntity userAngel = UserEntity.builder()
					.username("angel")
					.password("$2a$10$Zjhe7WfoNnqY5G2SgrIUuud7MTXifgwLKbKZAOGJZctivBqT3jl3i")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userDaniel = UserEntity.builder()
					.username("daniel")
					.password("$2a$10$Zjhe7WfoNnqY5G2SgrIUuud7MTXifgwLKbKZAOGJZctivBqT3jl3i")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			UserEntity userAndrea = UserEntity.builder()
					.username("andrea")
					.password("$2a$10$Zjhe7WfoNnqY5G2SgrIUuud7MTXifgwLKbKZAOGJZctivBqT3jl3i")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleInvited))
					.build();

			UserEntity userAnyi = UserEntity.builder()
					.username("anyi")
					.password("$2a$10$Zjhe7WfoNnqY5G2SgrIUuud7MTXifgwLKbKZAOGJZctivBqT3jl3i")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			userRepository.saveAll(List.of(userAngel, userDaniel, userAndrea, userAnyi));

		};
	}

}
