package com.g.mds;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class MultipleDatasourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDatasourcesApplication.class, args);
    }

    @Bean
    CommandLineRunner run(SysRolesService sysRolesService) {
        return args -> {
            final Iterable<SysRole> roles = sysRolesService.findAll();
            log.info("Found roles");

            roles.forEach(sysRole -> {
                final SysRole role = sysRolesService.get(sysRole.getId());
                log.info("\t[{}]", role);
            });
        };
    }
}
