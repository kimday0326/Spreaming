package dev.spreaming.streaming.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocket    // 웹소켓 서버 사용
@EnableWebSocketMessageBroker   // STOMP 사용
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws")
			.setAllowedOrigins("http://localhost:63342", "http://localhost:8000", "http://spreaming.xyz:8000")
			.withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 클라이언트가 SUBSCRIBE 할 prefix
		registry.enableSimpleBroker("/topic");
		// 클라이언트가 SEND 할 prefix
		registry.setApplicationDestinationPrefixes("/app");
	}
}
