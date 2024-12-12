package dev.spreaming.storage.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableCaching
@EnableRedisRepositories(basePackages = {"dev.spreaming.storage.cache"})
public class RedisConfig {
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory();
	}

	//
	// @Bean
	// MessageListenerAdapter messageListenerAdapter(MessageSubscriber subscriber) {
	// 	return new MessageListenerAdapter(subscriber);
	// }
	//
	// @Bean
	// RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory,
	// 	MessageListenerAdapter listenerAdapter) {
	// 	RedisMessageListenerContainer container = new RedisMessageListenerContainer();
	// 	container.setConnectionFactory(connectionFactory);
	// 	// 모든 streamKey 패턴 구독 (e.g. chatroom:* )
	// 	container.addMessageListener(listenerAdapter, new PatternTopic("chatroom/*"));
	// 	return container;
	// }
}
