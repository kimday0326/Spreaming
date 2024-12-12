package dev.spreaming.storage.cache;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StreamRepository extends CrudRepository<Stream, String> {
	List<Stream> findByStatus(StreamStatus status);
}
