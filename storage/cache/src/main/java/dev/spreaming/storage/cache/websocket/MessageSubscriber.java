package dev.spreaming.storage.cache.websocket;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageSubscriber {
	private final SimpMessagingTemplate template;
	private final ObjectMapper objectMapper;

	public MessageSubscriber(SimpMessagingTemplate template) {
		this.template = template;
		this.objectMapper = new ObjectMapper();
	}

	public void handleMessage(String text, String channel) {
		// text: 메시지 본문 (Redis 메시지 바디를 문자열로 변환한 것)
		// channel: 메시지를 수신한 Redis 채널명

		// 채널명 형식: chatroom:{streamKey}
		if (channel.startsWith("chatroom:")) {
			String streamKey = channel.substring("chatroom:".length());

			// text를 ChatMessage로 역직렬화할 필요가 있다면 여기서 처리
			// 가령 JSON 문자열이라고 가정할 경우:
			// ChatMessage chatMessage = objectMapper.readValue(text, ChatMessage.class);
			// template.convertAndSend("/topic/room/" + streamKey, chatMessage);

			// 단순히 받은 text를 그대로 전송
			// 클라이언트 측에서 ChatMessage 형태로 받길 원한다면 위 처럼 역직렬화 필요
			template.convertAndSend("/topic/room/" + streamKey, text);
		}
	}
}
