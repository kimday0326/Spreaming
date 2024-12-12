package dev.spreaming.storage.cache.websocket;

public class UserJoinLeaveMessage {
	private String username;

	public UserJoinLeaveMessage() {
	}

	public UserJoinLeaveMessage(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
