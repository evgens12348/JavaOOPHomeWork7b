package com.gmail.s12348.evgen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile implements Runnable {
	private Action action;
	private File fileFinish;
	private Thread thrWrite;

	public WriteFile() {
		super();
	}

	public WriteFile(Action action, File fileFinish) {
		super();
		this.action = action;
		this.fileFinish = fileFinish;
		thrWrite = new Thread(this);
		thrWrite.start();
	}

	@Override
	public void run() {
		try (FileOutputStream fos = new FileOutputStream(fileFinish)) {
			while (action.getBufferSize() != 0) {
				synchronized (action) {
					fos.write(action.getBuffer(), 0, action.getNumber());
					action.wait();
				}
			}
		} catch (InterruptedException | IOException e) {
			System.out.println(e);
		}
		System.out.println("Copying completed");
	}
}
