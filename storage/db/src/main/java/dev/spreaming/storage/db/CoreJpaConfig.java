package dev.spreaming.storage.db;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "dev.spreaming.storage.db")
@EnableJpaRepositories(basePackages = "dev.spreaming.storage.db")
@EnableJpaAuditing
class CoreJpaConfig {

}
