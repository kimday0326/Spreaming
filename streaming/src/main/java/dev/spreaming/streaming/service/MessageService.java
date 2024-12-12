package dev.spreaming.streaming.service;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.spreaming.storage.cache.websocket.ChatMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MessageService {
	private final SimpMessageSendingOperations messageSendingOperations;
	private final ObjectMapper objectMapper;

	public void publishMessage(String streamKey, ChatMessage message) {
		String channel = "/topic/room/" + streamKey;
		try {
			String json = objectMapper.writeValueAsString(message);
			messageSendingOperations.convertAndSend(channel, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
