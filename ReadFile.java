package com.gmail.s12348.evgen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile implements Runnable {
	private Action action;
	private File fileStart;
	private byte[] readArray;
	private Thread thrRead;

	public ReadFile() {
		super();
	}

	public ReadFile(Action action, File fileStart) {
		super();
		this.action = action;
		this.fileStart = fileStart;
		readArray = new byte[1048576];
		thrRead = new Thread(this);
		thrRead.start();
	}

	@Override
	public void run() {
		action.setFileLength(fileStart.length());
		try (FileInputStream fis = new FileInputStream(fileStart)) {
			int n = 0;
			for (; (n = fis.read(readArray)) > 0;) {
				synchronized (action) {
					action.setBuffer(readArray, n);
					action.wait();
				}
			}
		} catch (InterruptedException | IOException e) {
			System.out.println(e.getMessage());
		}
		action.setBuffer(new byte[0], 0);
	}
}