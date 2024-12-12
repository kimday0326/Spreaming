package dev.spreaming.storage.cache;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@RedisHash(value = "Stream")
public class Stream {
	@Id
	private String streamKey;
	private String title;
	private long viewers;
	private String thumbnail;
	private String description;

	private StreamStatus status; // 추가된 상태 필드

	public Stream(String streamKey, long viewers) {
		this.streamKey = streamKey;
		this.viewers = viewers;
		this.status = StreamStatus.PENDING; // 기본은 PENDING 상태
	}

	public void incrementViewers() {
		this.viewers++;
	}

	public void decrementViewers() {
		this.viewers--;
	}
}
