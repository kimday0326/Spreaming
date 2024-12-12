package dev.spreaming.streaming.service;

import org.springframework.stereotype.Service;

import dev.spreaming.storage.cache.Stream;
import dev.spreaming.storage.cache.StreamRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ViewerService {
	private final StreamRepository streamRepository;
	// private static final String VIEWER_KEY_PREFIX = "viewers:";

	public void addViewer(String streamKey, String username) {
		Stream stream = streamRepository.findById(streamKey)
			.orElseThrow(() -> new IllegalArgumentException("Invalid stream key: " + streamKey));
		stream.incrementViewers();
		streamRepository.save(stream);
	}

	public void removeViewer(String streamKey, String username) {
		Stream stream = streamRepository.findById(streamKey)
			.orElseThrow(() -> new IllegalArgumentException("Invalid stream key: " + streamKey));
		stream.decrementViewers();
		streamRepository.save(stream);
	}
}
