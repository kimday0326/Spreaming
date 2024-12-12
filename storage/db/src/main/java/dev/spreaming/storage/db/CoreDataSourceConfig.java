package dev.spreaming.storage.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
class CoreDataSourceConfig {

	@Bean
	@ConfigurationProperties(prefix = "storage.datasource")
	public HikariConfig coreHikariConfig() {
		return new HikariConfig();
	}

	@Bean
	public HikariDataSource coreDataSource(@Qualifier("coreHikariConfig") HikariConfig config) {
		return new HikariDataSource(config);
	}

}
