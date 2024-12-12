package dev.spreaming.storage.cache.websocket;

import lombok.ToString;

@ToString
public class ChatMessage {
	private String username;
	private String message;
	private String time; // "HH:mm" 형태 등

	public ChatMessage(String username, String message, String time) {
		this.username = username;
		this.message = message;
		this.time = time;
	}

	public String getUsername() {
		return username;
	}

	public String getMessage() {
		return message;
	}

	public String getTime() {
		return time;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
