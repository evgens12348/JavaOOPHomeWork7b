package com.gmail.s12348.evgen;

public class Progressing implements Runnable {

	private Action action;
	private Thread thr;

	public Progressing() {
		super();
	}

	public Progressing(Action action) {
		super();
		this.action = action;
		thr = new Thread(this);
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thr.start();
	}

	@Override
	public void run() {
		if (action.getBufferSize() != 0) {
			for (int i = 1; (i < (action.getFileLength()) / action.getBufferSize()); i++) {
				synchronized (action) {
					try {
						action.wait();
						System.out.printf("Progress of copying  — %2.2f%s\n\n",
								(((double) i * action.getBufferSize()) / action.getFileLength()) * 100, "%");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}