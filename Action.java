package com.gmail.s12348.evgen;

public class Action {
	private byte[] buffer;
	private long fileLength;
	private int number;

	public Action() {
		super();
	}

	public synchronized void setBuffer(byte[] buffer, int number) {
		this.buffer = buffer;
		this.number = number;
		notify();
	}

	public synchronized byte[] getBuffer() {
		notifyAll();
		return this.buffer;
	}

	public int getNumber() {
		return number;
	}

	public synchronized int getBufferSize() {
		return buffer.length;
	}

	public void setFileLength(long length) {
		this.fileLength = length;
	}

	public long getFileLength() {
		return fileLength;
	}

}