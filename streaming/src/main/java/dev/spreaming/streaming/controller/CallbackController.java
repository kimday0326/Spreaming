package dev.spreaming.streaming.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.spreaming.storage.cache.Stream;
import dev.spreaming.storage.cache.StreamRepository;
import dev.spreaming.storage.cache.StreamStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CallbackController {
	private final StreamRepository streamRepository;

	@PostMapping("/on_publish")
	public ResponseEntity<String> onPublish(@RequestParam Map<String, String> params) {
		log.info("on_publish: {}", params);
		// 파라미터 추출
		String app = params.get("app");
		String name = params.get("name");     // 스트림 키
		String addr = params.get("addr");
		String clientId = params.get("clientid");

		log.info("Stream started request: app={}, name={}, addr={}, clientid={}", app, name, addr, clientId);

		// 스트림 키 인증 로직
		boolean isValid = validateStreamKey(name);
		if (!isValid) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
		}

		// 스트림 상태를 LIVE로 업데이트
		updateStreamStatus(name, StreamStatus.LIVE);
		log.info("Stream is now LIVE: {}", name);

		// 성공 응답 반환
		return ResponseEntity.ok("OK");
	}

	@PostMapping("/on_publish_done")
	public ResponseEntity<String> onPublishDone(@RequestParam Map<String, String> params) {
		// 파라미터 추출
		String app = params.get("app");
		String name = params.get("name");
		String addr = params.get("addr");
		String clientId = params.get("clientid");

		log.info("Stream stopped: app={}, name={}, addr={}, clientid={}", app, name, addr, clientId);

		// 스트림 상태를 OFFLINE으로 업데이트
		updateStreamStatus(name, StreamStatus.OFFLINE);

		// 성공 응답 반환
		return ResponseEntity.ok("OK");
	}

	// 스트림 키 인증 메서드
	private boolean validateStreamKey(String streamKey) {
		// 스트림 키에 해당하는 스트림 존재 여부 확인
		// PENDING 상태여야 방송 시작(on_publish) 가능
		Stream s = streamRepository.findById(streamKey)
			.orElseThrow(() -> new IllegalArgumentException("Invalid stream key: " + streamKey));
		if (s == null)
			return false;
		return s.getStatus() == StreamStatus.PENDING;
	}

	// 스트림 상태 업데이트 메서드
	private void updateStreamStatus(String streamKey, StreamStatus status) {
		Stream s = streamRepository.findById(streamKey)
			.orElseThrow(() -> new IllegalArgumentException("Invalid stream key: " + streamKey));
		if (s == null) {
			// 없는 스트림 키라면 로그만 찍고 무시할 수도 있고,
			// 필요하다면 예외 처리
			log.warn("Stream not found for key: {}", streamKey);
			return;
		}
		s.setStatus(status);
		streamRepository.save(s);
		log.info("Stream status updated: streamKey={}, status={}", streamKey, status);
	}
}

