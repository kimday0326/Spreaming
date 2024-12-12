package dev.spreaming.storage.cache.websocket;

public class ViewerCountMessage {
	private int count;

	public ViewerCountMessage() {
	}

	public ViewerCountMessage(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
