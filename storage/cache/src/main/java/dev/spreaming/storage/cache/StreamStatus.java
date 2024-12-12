package dev.spreaming.storage.cache;

public enum StreamStatus {
	PENDING,  // 방송 시작 요청 후 대기 상태
	LIVE,     // on_publish로 인해 실제 방송 송출 중
	OFFLINE   // 방송 종료(on_publish_done) 후 오프라인 상태
}
