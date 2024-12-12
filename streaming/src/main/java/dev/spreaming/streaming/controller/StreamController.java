package dev.spreaming.streaming.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.spreaming.storage.cache.Stream;
import dev.spreaming.storage.cache.StreamRepository;
import dev.spreaming.storage.cache.StreamStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/streams")
public class StreamController {
	private final StreamRepository streamRepository;

	@GetMapping("/list")
	public List<Stream> getAllLiveStreams() {
		Iterable<Stream> allStreams = streamRepository.findAll();
		return StreamSupport.stream(allStreams.spliterator(), false)
			.filter(s -> s.getStatus() == StreamStatus.LIVE)
			.collect(Collectors.toList());
	}

	@PostMapping("/token")
	public String createStreamToken(@RequestBody TokenRequest request) {
		Stream stream = new Stream();
		stream.setStreamKey(request.getStreamKey());
		stream.setTitle(request.getTitle());
		stream.setDescription(request.getDescription());
		stream.setViewers(0);
		stream.setThumbnail("https://via.placeholder.com/80");
		// 방송 시작 요청 시 기본적으로 PENDING 상태로 저장
		stream.setStatus(StreamStatus.PENDING);

		streamRepository.save(stream);
		return "StreamKey [" + request.getStreamKey() + "] 대기 상태로 등록됨(PENDING)";
	}

	public static class TokenRequest {
		private String streamKey;
		private String title;
		private String description;

		public String getStreamKey() {
			return streamKey;
		}

		public void setStreamKey(String streamKey) {
			this.streamKey = streamKey;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}
}
