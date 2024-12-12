package dev.spreaming.streaming.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import dev.spreaming.storage.cache.websocket.ChatMessage;
import dev.spreaming.storage.cache.websocket.UserJoinLeaveMessage;
import dev.spreaming.streaming.service.MessageService;
import dev.spreaming.streaming.service.ViewerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController {
	private final ViewerService viewerService;
	private final MessageService messageService;
	private final SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/chat/{streamKey}")
	public void sendMessage(@DestinationVariable String streamKey, @Payload ChatMessage message) {
		log.info("Sending message to stream: " + streamKey);
		log.info("Message: " + message);
		// 타임스탬프 추가 (HH:mm)
		String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
		message.setTime(time);

		// Redis pub/sub로 메세지 전송
		messageService.publishMessage(streamKey, message);
	}

	@MessageMapping("/join/{streamKey}")
	public void join(@DestinationVariable String streamKey, @Payload UserJoinLeaveMessage msg) {
		log.info("Joining stream: " + streamKey);
		viewerService.addViewer(streamKey, msg.getUsername());
	}

	@MessageMapping("/leave/{streamKey}")
	public void leave(@DestinationVariable String streamKey, @Payload UserJoinLeaveMessage msg) {
		log.info("Leaving stream: " + streamKey);
		viewerService.removeViewer(streamKey, msg.getUsername());
	}
}
