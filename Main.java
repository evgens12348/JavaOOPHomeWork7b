package com.gmail.s12348.evgen;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		Action action = new Action();
		read = new ReadFile(action, new File("a.txt"));
		progress = new Progressing(action);
		write = new WriteFile(action, new File("b.txt"));
		
	}
	public static ReadFile read;
	public static Progressing progress;
	public static WriteFile write;
	
}
